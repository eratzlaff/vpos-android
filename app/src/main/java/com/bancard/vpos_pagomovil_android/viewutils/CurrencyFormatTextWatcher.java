package com.bancard.vpos_pagomovil_android.viewutils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CurrencyFormatTextWatcher implements TextWatcher {

    private final static Integer DEFAULT_MAX_LENGTH = 12;

    private final Integer maxLength;
    private final EditText editText;
    private Double lastValidNumber;

    public CurrencyFormatTextWatcher(EditText et, Integer maxLength) {
        this.editText = et;
        this.maxLength = maxLength;
    }

    public CurrencyFormatTextWatcher(EditText et) {
        this(et, DEFAULT_MAX_LENGTH);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        this.editText.removeTextChangedListener(this);
        String prefix = "Gs. ";

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setGroupingSeparator('.');
        decimalFormatSymbols.setDecimalSeparator('.');

        DecimalFormat formatter = new DecimalFormat("###,###", decimalFormatSymbols);

        String number = editable.toString().replaceAll("\\.", "").replace("Gs ", "");
        String text = "";

        if (!number.isEmpty()) {
            if (maxLength != null) {
                if (number.length() >= maxLength) {
                    number = number.substring(0, maxLength);
                }
            }

            try {
                lastValidNumber = Double.parseDouble(number);
            } catch (NumberFormatException exception) {

            }

            if (lastValidNumber != null) {
                text = formatter.format(lastValidNumber);
                text = prefix.concat(text);
            }
        } else {
            lastValidNumber = null;
        }

        editable.replace(0, editable.length(), text);

        this.editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
