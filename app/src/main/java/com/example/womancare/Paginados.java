package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Paginados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paginados);

        WebView myWebView = (WebView) findViewById(R.id.webview2);
        myWebView.loadUrl("https://www.ags.gob.mx/cont.aspx?p=4829");
    }
}