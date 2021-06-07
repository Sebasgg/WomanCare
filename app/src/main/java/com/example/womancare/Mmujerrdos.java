package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Mmujerrdos extends AppCompatActivity {
    Button regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmujerrdos);
        regresar=(Button)findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent(  Mmujerrdos.this,MainActivity.class);
                startActivity(o );

            }
        });
    }
}