package com.example.womancare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.webkit.WebView;

public class Paginasuno extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginasuno);

        WebView myWebView = (WebView) findViewById(R.id.webview1);
        myWebView.loadUrl("https://www.fgjcdmx.gob.mx/nuestros-servicios/en-linea/mp-virtual/centros-de-justicia-para-las-mujeres");
    }
}