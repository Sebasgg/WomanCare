package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Paginacuatro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginacuatro);

        WebView myWebView = (WebView) findViewById(R.id.webview4);
        myWebView.loadUrl("https://www.elsoldelcentro.com.mx/local/historica-marcha-feminista-se-vivio-en-aguascalientes-6456003.html");
    }

}