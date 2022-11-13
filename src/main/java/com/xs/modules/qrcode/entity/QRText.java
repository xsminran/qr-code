package com.xs.modules.qrcode.entity;

import lombok.Data;

import java.util.Date;

@Data
public class QRText {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Date createTime;
    private Date updateTime;
    private String accountId;
    private String qiniuHash;
    private String content;
}
