package com.xs.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("live_text")
public class Text {
    @TableId
    private String id;
    private String userId;
    private String text;

    public Text() {
    }

    public Text(String id, String userId, String text) {
        this.id = id;
        this.userId = userId;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Text{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", text='" + text + '\'' +
                '}';
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
