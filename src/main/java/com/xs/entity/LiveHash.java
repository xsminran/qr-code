package com.xs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("live_hash")
public class LiveHash {
    @TableId
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("hash")
    private String hash;

    public LiveHash() {
    }

    public LiveHash(String userId, String hash) {
        this.userId = userId;
        this.hash = hash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
