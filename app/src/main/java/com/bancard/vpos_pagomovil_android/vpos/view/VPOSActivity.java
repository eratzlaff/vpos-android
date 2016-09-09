package com.bancard.vpos_pagomovil_android.vpos.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bancard.vpos_pagomovil_android.BuildConfig;
import com.bancard.vpos_pagomovil_android.R;
import com.bancard.vpos_pagomovil_android.confirmation.view.ConfirmationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VPOSActivity extends AppCompatActivity {

    public static final String PROCESS_ID_EXTRA = "String:ProcessId:Extra";
    public static final String PROCESS_SHOP_ID_EXTRA = "Long:ProcessShopId:Extra";

    private static final String BANCARD_COMMERCES_URL = "https://comercios.bancard.com.py/";

    @BindView(R.id.webview)
    WebView mWebView;

    private String mProcessId;
    private String mURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpos);
        ButterKnife.bind(this);

        mProcessId = getIntent().getStringExtra(PROCESS_ID_EXTRA);
        mURL = String.format(BuildConfig.WEB_VPOS_URL, mProcessId);

        setupWebView();
    }

    private void setupWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        setUpWebViewClient();
        mWebView.loadUrl(mURL);
    }

    private void setUpWebViewClient() {
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onReceivedError(
                    WebView view, int errorCode, String description, String failingUrl) {
                view.loadUrl(mURL);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                if (url.contentEquals(BANCARD_COMMERCES_URL)) {
                    startConfirmationActivity();
                }
            }
        });
    }

    private void startConfirmationActivity() {
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra(VPOSActivity.PROCESS_ID_EXTRA, mProcessId);
        intent.putExtra(VPOSActivity.PROCESS_SHOP_ID_EXTRA, getIntent().getIntExtra(PROCESS_SHOP_ID_EXTRA, -1));
        startActivity(intent);
    }

}
