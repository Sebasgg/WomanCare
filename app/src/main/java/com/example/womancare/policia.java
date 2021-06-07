package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class policia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policia);

        WebView myWebView = (WebView) findViewById(R.id.webview6);
        myWebView.loadUrl("https://www.aguascalientes.gob.mx/estado/emergencias.html");
    }
}