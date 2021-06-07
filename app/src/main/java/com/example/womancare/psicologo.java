package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class psicologo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psicologo);
        WebView myWebView = (WebView) findViewById(R.id.webview7);
        myWebView.loadUrl("https://www.doctoralia.com.mx/psicologo/aguascalientes");
    }
}