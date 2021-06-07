package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Paginacinco extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginacinco);
        WebView myWebView = (WebView) findViewById(R.id.webview5);
        myWebView.loadUrl("https://www.betulum.com/?gclid=EAIaIQobChMI1ZOfuq6-6QIVR__jBx0HkwAbEAAYASAAEgLDHPD_BwE");
    }
}