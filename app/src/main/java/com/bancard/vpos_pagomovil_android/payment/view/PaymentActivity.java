package com.bancard.vpos_pagomovil_android.payment.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bancard.vpos_pagomovil_android.R;
import com.bancard.vpos_pagomovil_android.vpos.view.VPOSActivity;
import com.bancard.vpos_pagomovil_android.payment.presenter.PaymentPresenter;
import com.bancard.vpos_pagomovil_android.viewutils.CurrencyFormatTextWatcher;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class PaymentActivity extends AppCompatActivity implements PaymentView {

    @BindView(R.id.amount) EditText mAmountET;
    @BindView(R.id.description) EditText mDescriptionET;
    @BindView(R.id.loader) AVLoadingIndicatorView mLoader;

    private PaymentPresenter mPaymentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPaymentPresenter = new PaymentPresenter(this);

        setupAmountField();
    }

    private void setupAmountField() {
        mAmountET.addTextChangedListener(new CurrencyFormatTextWatcher(mAmountET));
        mAmountET.setRawInputType(InputType.TYPE_CLASS_PHONE);
    }

    @OnClick(R.id.pay)
    public void onPayClick() {
        String description = mDescriptionET.getText().toString();
        String amount = mAmountET.getText().toString();

        if (mPaymentPresenter.areFieldsValid(amount, description)) {
            mPaymentPresenter.pay();
        } else {
            Toast.makeText(this, getString(R.string.invalid_info), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccessPayment(String processId, int processShopId) {
        Intent intent = new Intent(this, VPOSActivity.class);
        intent.putExtra(VPOSActivity.PROCESS_ID_EXTRA, processId);
        intent.putExtra(VPOSActivity.PROCESS_SHOP_ID_EXTRA, processShopId);
        startActivity(intent);
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, getString(R.string.payment_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoader() {
        mLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mLoader.setVisibility(View.GONE);
    }
}
