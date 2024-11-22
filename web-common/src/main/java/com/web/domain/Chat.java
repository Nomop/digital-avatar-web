package com.web.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("chat_info")
public class Chat {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("chat_uuid")
    private String chatUuid;

    @TableField("sender_identity")
    private Integer senderIdentity;

    @TableField("receiver_identity")
    private Integer receiverIdentity;

    @TableField("sender_id")
    private String senderId;

    @TableField("receiver_id")
    private String receiverId;

    @TableField("chat_title")
    private String chatTitle;

    @TableField("chat_keyword")
    private String chatKeyword;

    @TableField("chat_status")
    private Integer chatStatus;

    @TableField("chat_rounds")
    private Integer chatRounds;

    @TableField("favorite_status")
    private Integer favoriteStatus;

    @TableField("favorite_time")
    private LocalDateTime favoriteTime;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("latest_message_time")
    private LocalDateTime latestMessageTime;

    @TableField("latest_read_time")
    private LocalDateTime latestReadTime;

    @TableField("deleted_users")
    private String deletedUsers;
}