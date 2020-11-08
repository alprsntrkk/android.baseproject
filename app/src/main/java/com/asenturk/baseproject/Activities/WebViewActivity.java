package com.asenturk.baseproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.asenturk.baseproject.R;

public class WebViewActivity extends AppCompatActivity {


    private WebView mainWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mainWebView = new WebView(this);
        //when this line of code is not exists the app will open web-site with browser (like chrome...)
        mainWebView.setWebViewClient(new WebViewClient());
        mainWebView.loadUrl("http://www.google.com");
        setContentView(mainWebView);
    }

}