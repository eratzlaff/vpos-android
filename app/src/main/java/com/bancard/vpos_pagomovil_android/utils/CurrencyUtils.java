package com.bancard.vpos_pagomovil_android.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class CurrencyUtils {

    private static final String PRICE_WITH_DECIMAL_FORMAT = "###,###,###.00";

    public static Double singleParse(String value) throws ParseException {
        if (value != null) {
            DecimalFormat df = applySymbols(new DecimalFormat(PRICE_WITH_DECIMAL_FORMAT));
            return df.parse(value).doubleValue();
        } else {
            return null;
        }
    }

    private static DecimalFormat applySymbols(DecimalFormat formatter) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);

        return formatter;
    }
}
