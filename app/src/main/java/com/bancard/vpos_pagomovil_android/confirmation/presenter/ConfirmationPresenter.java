package com.bancard.vpos_pagomovil_android.confirmation.presenter;

import com.bancard.vpos_pagomovil_android.confirmation.view.ConfirmationView;
import com.bancard.vpos_pagomovil_android.model.BaseConfirmationResponse;
import com.bancard.vpos_pagomovil_android.model.OperationResponseWrapper;
import com.bancard.vpos_pagomovil_android.networking.RetrofitBuilder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmationPresenter {

    private static final String DENIED_TRANSACTION = "Denegada";
    private ConfirmationView mView;

    public ConfirmationPresenter(ConfirmationView confirmationView) {
        mView = confirmationView;
    }

    public void getConfirmation(String processId) {
        mView.showLoader();
        Call<BaseConfirmationResponse> call = RetrofitBuilder.getInstance().getPaymentConfirmation(processId);
        call.enqueue(new Callback<BaseConfirmationResponse>() {
            @Override
            public void onResponse(Call<BaseConfirmationResponse> call, Response<BaseConfirmationResponse> response) {
                if (response.body() != null) {
                    analiseRequestBody(response.body());
                } else {
                    mView.showErrorView();
                }

                mView.hideLoader();
            }

            @Override
            public void onFailure(Call<BaseConfirmationResponse> call, Throwable t) {
                mView.showErrorView();
                mView.hideLoader();
            }
        });
    }

    private void analiseRequestBody(BaseConfirmationResponse baseConfirmationResponse) {
        String operation = baseConfirmationResponse.getConfirmation().getSentData();
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        try {
            OperationResponseWrapper operationResponse = objectMapper.readValue(operation, OperationResponseWrapper.class);

            if (operationResponse.getOperation().getResponseDescription().toLowerCase()
                    .contains(DENIED_TRANSACTION.toLowerCase())) {
                mView.showErrorViewWithResponse(operationResponse.getOperation());
            } else {
                mView.showSuccessView(operationResponse.getOperation());
            }
        } catch (IOException e) {
            mView.showErrorView();
        }
    }
}
