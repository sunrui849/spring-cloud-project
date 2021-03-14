package com.sr.cloud.common.util;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 生产uuid
     * @return
     */
    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
