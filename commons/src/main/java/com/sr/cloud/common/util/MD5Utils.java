package com.sr.cloud.common.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

@Slf4j
public class MD5Utils {

    /**
     * MD5方法
     *
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text) {
        try {
            byte[] bytes = (text).getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                if ((bytes[i] & 0xff) < 0x10) {
                    sb.append("0");
                }

                sb.append(Long.toString(bytes[i] & 0xff, 16));
            }

            return sb.toString().toLowerCase();
        } catch (Exception e) {
            log.error("md5 exception ", e);
        }
        return null;
    }
}
