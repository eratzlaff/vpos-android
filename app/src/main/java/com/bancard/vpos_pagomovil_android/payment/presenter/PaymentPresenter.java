package com.bancard.vpos_pagomovil_android.payment.presenter;

import com.bancard.vpos_pagomovil_android.BuildConfig;
import com.bancard.vpos_pagomovil_android.model.Operation;
import com.bancard.vpos_pagomovil_android.model.Payment;
import com.bancard.vpos_pagomovil_android.model.PaymentResponse;
import com.bancard.vpos_pagomovil_android.networking.RetrofitBuilder;
import com.bancard.vpos_pagomovil_android.payment.view.PaymentView;
import com.bancard.vpos_pagomovil_android.utils.CurrencyUtils;
import com.bancard.vpos_pagomovil_android.utils.SecurityUtils;

import java.text.ParseException;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bancard.vpos_pagomovil_android.utils.ProcessIdGenerator.getShopProcessId;

public class PaymentPresenter {

    private static final String CURRENCY = "PYG";
    private static final String ZERO = "0";
    private static final String SUCCESS_KEY = "success";

    private String mAmount;
    private String mDescription;
    private int mShopProcessID;
    private PaymentView mView;

    public PaymentPresenter(PaymentView view) {
        mView = view;
    }

    public boolean areFieldsValid(String amount, String description) {
        Double amountDouble;
        try {
            amount = amount.replace("Gs. ", "");
            amountDouble = CurrencyUtils.singleParse(amount);
        } catch (ParseException e) {
            return false;
        }

        mAmount = String.valueOf(amountDouble).concat(ZERO);
        mDescription = description;

        return !mAmount.isEmpty() && !mDescription.isEmpty();
    }

    public void pay() {
        mView.showLoader();
        Call<PaymentResponse> call = RetrofitBuilder.getInstance().pay(generatePayment());
        call.enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                if (response.body() != null && response.body().getStatus().equals(SUCCESS_KEY)) {
                    mView.onSuccessPayment(response.body().getProcessId(), mShopProcessID);
                } else {
                    mView.onFailure();
                }

                mView.hideLoader();
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
                mView.onFailure();
                mView.hideLoader();
            }
        });
    }

    private Payment generatePayment() {
        Payment payment = new Payment();

        payment.setPublicKey(BuildConfig.PUBLIC_KEY);
        payment.setOperation(createOperation());
        payment.setTestClient(true);

        return payment;
    }

    private Operation createOperation() {
        mShopProcessID = getShopProcessId();

        String url = String.format(BuildConfig.VPOS_URL, String.valueOf(mShopProcessID));
        Operation operation = new Operation();

        operation.setShopProcessId(mShopProcessID);
        operation.setAmount(mAmount);
        operation.setDescription(mDescription);
        operation.setCurrency(CURRENCY);
        operation.setAdditionalData("");
        operation.setReturnUrl(url);
        operation.setCancelUrl(url);
        operation.setToken(
                SecurityUtils.generateToken(
                        String.valueOf(mShopProcessID),
                        mAmount,
                        CURRENCY
                )
        );

        return operation;
    }
}
