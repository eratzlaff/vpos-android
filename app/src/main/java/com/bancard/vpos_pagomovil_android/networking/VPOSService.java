package com.bancard.vpos_pagomovil_android.networking;

import com.bancard.vpos_pagomovil_android.model.BaseConfirmationResponse;
import com.bancard.vpos_pagomovil_android.model.Payment;
import com.bancard.vpos_pagomovil_android.model.PaymentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface VPOSService {

    @POST("single_buy")
    Call<PaymentResponse> pay(@Body Payment payment);

    @GET("confirmations/{process_id}")
    Call<BaseConfirmationResponse> getPaymentConfirmation(
            @Path("process_id") String processId
    );
}
