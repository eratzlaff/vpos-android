package com.bancard.vpos_pagomovil_android.confirmation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bancard.vpos_pagomovil_android.R;
import com.bancard.vpos_pagomovil_android.confirmation.presenter.ConfirmationPresenter;
import com.bancard.vpos_pagomovil_android.model.OperationResponse;
import com.bancard.vpos_pagomovil_android.vpos.view.VPOSActivity;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ConfirmationActivity extends AppCompatActivity implements ConfirmationView {

    @BindView(R.id.loader) AVLoadingIndicatorView mLoader;
    @BindView(R.id.result_image) ImageView mResultIV;
    @BindView(R.id.result_label) TextView mResultLabel;
    @BindView(R.id.background) RelativeLayout mBackground;
    @BindView(R.id.result_description) TextView mDescription;

    private String mProcessId;
    private ConfirmationPresenter mConfirmationPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        ButterKnife.bind(this);

        mProcessId = getIntent().getStringExtra(VPOSActivity.PROCESS_ID_EXTRA);
        mConfirmationPresenter = new ConfirmationPresenter(this);

        getConfirmation();
    }

    private void getConfirmation() {
        mConfirmationPresenter.getConfirmation(mProcessId);
    }

    @Override
    public void showLoader() {
        mLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mLoader.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        mResultLabel.setVisibility(View.VISIBLE);
        mResultIV.setVisibility(View.VISIBLE);
        mResultLabel.setText(getString(R.string.transaction_denied));
        mBackground.setBackgroundColor(getResources().getColor(R.color.red));
        mResultIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_highlight_off_white_24dp));
    }

    @Override
    public void showErrorViewWithResponse(OperationResponse operationResponse) {
        mResultLabel.setVisibility(View.VISIBLE);
        mResultIV.setVisibility(View.VISIBLE);
        mResultLabel.setText(getString(R.string.transaction_denied));
        mBackground.setBackgroundColor(getResources().getColor(R.color.red));
        mResultIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_highlight_off_white_24dp));
    }

    @Override
    public void showSuccessView(OperationResponse operationResponse) {
        mResultLabel.setVisibility(View.VISIBLE);
        mResultIV.setVisibility(View.VISIBLE);
        mResultLabel.setText(getString(R.string.transaction_approved));
        mBackground.setBackgroundColor(getResources().getColor(R.color.green));
        mResultIV.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_white_80dp));
    }
}
