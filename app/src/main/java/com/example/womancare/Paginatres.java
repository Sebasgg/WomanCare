package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Paginatres extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginatres);

        WebView myWebView = (WebView) findViewById(R.id.webview3);
        myWebView.loadUrl("https://www.psicologia-online.com/tipos-de-violencia-4936.html");
    }
}