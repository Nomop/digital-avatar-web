package com.web.util;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.web.constant.ResultCode;
import com.web.domain.GovUser;
import com.web.dto.JwtUser;
import com.web.exception.TokenException;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Jwt工具类
 */
@Data
@Component
// 引入配置类中jwt相关配置
@ConfigurationProperties("jwt")
public class JwtUtil {

    private String requestHeader;
    private String secret;
    private int expiration;
    private String tokenStartWith;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取用户名
     * @return
     */
    public String getAccountByToken(String token) {
        Claims claims = getClaimsByToken(token);
        return claims != null ? claims.getSubject() : null;
    }

    /**
     * 获取过期时间
     * @param token
     * @return Date
     */
    public Date getExpiredByToken(String token) {
        Claims claims = getClaimsByToken(token);
        return claims != null ? claims.getExpiration() : null;
    }

    /**
     * 获取Claims
     * @param token
     * @return
     */
    private Claims getClaimsByToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            // 签名不一致异常
            if (e instanceof SignatureException) {
                throw new TokenException(ResultCode.TOKEN_INVALID);
            }
            // token过期异常
            if (e instanceof ExpiredJwtException) {
                throw new TokenException(ResultCode.TOKEN_TIMEOUT);
            }
            // 如果都不是上面的则弹出token无效异常
            throw new TokenException(ResultCode.TOKEN_INVALID);
        }
        return claims;
    }

    /**
     * 计算过期时间
     * @return
     */
    private Date generateExpired() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 判断 Token 是否过期
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        Date expirationDate = getExpiredByToken(token);
        return expirationDate.before(new Date());
    }

    /**
     * 生成 Token
     * @param user 用户信息
     * @return
     */
    public String generateToken(GovUser user) {
        String token = Jwts.builder()
                .setSubject(user.getUserPhone())
                .setExpiration(generateExpired())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        String key = "login:" + user.getUserPhone() + ":" + token;
        redisUtil.set(key, token, expiration);
        return token;
    }

    /**
     * 验证 Token
     * @param token
     * @return
     */
    public Boolean validateToken(String token) {
        String account = getAccountByToken(token);
        String key = "login:" + account+ ":" + token;
        Object data = redisUtil.get(key);
        String redisToken = data == null ? null : data.toString();
        return StrUtil.isNotEmpty(token) && !isTokenExpired(token) && token.equals(redisToken);
    }

    /**
     * 移除 Token
     * @param token
     */
    public void removeToken(String token) {
        String account = getAccountByToken(token);
        String key = "login:" + account+ ":" + token;
        redisUtil.del(key);
        delUserDetail(account);
    }

    /**
     * 获取token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        String auth = request.getHeader(requestHeader);
        if (StrUtil.isBlank(auth)) {
            return null;
        }
        String token = auth.replace(tokenStartWith, "");
        return token;
    }

    /**
     * 退出登录
     */
    public void logout(HttpServletRequest request) {
        String token = getToken(request);
        removeToken(token);
    }

    /**
     * 获取userDetail
     * @param token
     * @return
     */
    public JwtUser getUserDetail(String token) {
        String account = getAccountByToken(token);
        String s = (String) redisUtil.get("user:userDetail:" + account);
        JwtUser jwtUser = JSON.parseObject(s, JwtUser.class);
        return jwtUser;
    }

    /**
     * 删除userDetail
     * @param account
     */
    public void delUserDetail(String account) {
        redisUtil.del("user:userDetail:" + account);
    }
}