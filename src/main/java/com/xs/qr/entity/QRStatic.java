package com.xs.qr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRStatic {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Date createTime;
    private Date updateTime;
    private String accountId;
    private String codeId;
    private String dirId;
}
