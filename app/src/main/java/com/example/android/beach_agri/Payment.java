package com.example.android.beach_agri;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_payment);
        WebView webView=(WebView)findViewById(R.id.webview);
        webView.loadUrl("https://akhilvenu.github.io/beach3/");

    }
}
