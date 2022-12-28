package com.xs.common.util;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 把String自带的UUID去除下划线返回
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }
}
