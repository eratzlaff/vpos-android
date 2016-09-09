package com.bancard.vpos_pagomovil_android.utils;

import java.util.Random;

public class ProcessIdGenerator {

    private static final Random RANDOM = new Random();

    /* This method provides a random process id, when creating a new payment request each
        process id should be different. */
    public static int getShopProcessId() {
        return RANDOM.nextInt(3000 - 1) + 10000;
    }
}
