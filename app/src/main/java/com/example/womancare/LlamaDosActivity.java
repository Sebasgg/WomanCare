package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LlamaDosActivity extends AppCompatActivity {
    Button regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llama_dos);
        regresar=(Button)findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent( LlamaDosActivity.this,UnoActivity.class);
                startActivity(o );

            }
        });

        Button mDialButton = (Button) findViewById(R.id.btn_dial);
        final EditText mPhoneNoEt = (EditText) findViewById(R.id.et_phone_no);

        mDialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = mPhoneNoEt.getText().toString();
                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                }else {
                    Toast.makeText(LlamaDosActivity.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
