package com.bancard.vpos_pagomovil_android.utils;

import com.bancard.vpos_pagomovil_android.BuildConfig;

import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

    public static String generateToken(String shopProcessId, String amount, String currency) {
        String tokenString = BuildConfig.PRIVATE_KEY + shopProcessId + amount + currency;

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(tokenString.getBytes());
            StringBuffer stringBuffer = new StringBuffer();

            for (int i = 0; i < array.length; ++i) {
                stringBuffer.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }

            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
