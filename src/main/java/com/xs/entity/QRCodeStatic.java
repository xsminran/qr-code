package com.xs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("qrcode_static")
public class QRCodeStatic {
    @TableId
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("url")
    private String url;
    @TableField("qiniu_hash")
    private String hash;

    public QRCodeStatic(String userId, String url, String hash) {
        this.userId = userId;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
