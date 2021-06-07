package com.example.womancare;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MensajeActivity extends AppCompatActivity {
    EditText etMsj, etCel;
    Button btnEnviar;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        etMsj = findViewById(R.id.editTextTextPersonName);
        etCel = findViewById(R.id.editTextTextPersonName2);
        regresar=(Button)findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent( MensajeActivity.this,UnoActivity.class);
                startActivity(o );

            }
        });
        btnEnviar = findViewById(R.id.button);

        if (ActivityCompat.checkSelfPermission(MensajeActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MensajeActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(etCel.getText().toString(), null, etMsj.getText().toString(), null, null);

                Toast.makeText(MensajeActivity.this, "Msj Enviado", Toast.LENGTH_LONG).show();
            }

        });

    }
}