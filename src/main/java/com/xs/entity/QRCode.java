package com.xs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("qr_code")
public class QRCode {
    @TableId
    private String id;
    @TableField("url")
    private String url;
    @TableField("qiniu_hash")
    private String qiniuhash;
    @TableField("user_id")
    private String userId;
    @TableField("text_id")
    private String textId;

    public QRCode(String url, String qiniuhash, String userId, String textId) {
        this.url = url;
        this.qiniuhash = qiniuhash;
        this.userId = userId;
        this.textId = textId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQiniuhash() {
        return qiniuhash;
    }

    public void setQiniuhash(String qiniuhash) {
        this.qiniuhash = qiniuhash;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }
}
