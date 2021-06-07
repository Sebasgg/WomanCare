package com.example.womancare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.maps.GoogleMap;

public class SosActivity extends AppCompatActivity {
   EditText etMsj,etCel;
   FloatingActionButton btnSelec;
   Button btnEnviar,btnw;
    TextView mensaje1;
    TextView mensaje2;
    String mesaje22,mesaje23;
    EditText nombresos,telefonosos;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        //preba


        telefonosos= findViewById(R.id.telefonosos);
        nombresos= findViewById(R.id.nombresos);

        etMsj = findViewById(R.id.editTextTextPersonName);
        etCel = findViewById(R.id.editTextTextPersonNumber);
        btnSelec = findViewById(R.id.btnSelec);
        mensaje1 = (TextView) findViewById(R.id.mensaje_id);
        mensaje2 = (TextView) findViewById(R.id.mensaje_id2);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

        btnSelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent, 1);
            }
        });


        // espacio
        btnEnviar = findViewById(R.id.buttonsos);
        btnw = findViewById(R.id.btnwsos);

        if (ActivityCompat.checkSelfPermission(SosActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SosActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(telefonosos.getText().toString(), null, "Ayuda "+mensaje1.getText().toString()+ mensaje2.getText().toString(), null, null);
                Toast.makeText(SosActivity.this, "Sms Enviado", Toast.LENGTH_LONG).show();
            }
        });

       btnw.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (etCel.getText().toString().isEmpty()){
                   Intent sendIntent = new Intent();
                   sendIntent.setAction(Intent.ACTION_SEND);
                   sendIntent.putExtra(Intent.EXTRA_TEXT,etCel.getText().toString());
                   sendIntent.setType("text/plain");
                   sendIntent.setPackage("com.whatsapp");
                   startActivity(sendIntent);

               }else {
                   Intent sendIntent = new Intent();
                   sendIntent.setAction(Intent.ACTION_VIEW);
                   String uri = "whatsapp://send?phone="+telefonosos.getText().toString()+"&text="+etMsj.getText().toString();
                   sendIntent.setData(Uri.parse(uri));
                   startActivity(sendIntent);

               }
           }//duda
       });
    }

    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
        mensaje1.setText("Localizacion agregada");
        mensaje2.setText("");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1  && resultCode == RESULT_OK){

            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri,null,null,null,null);
            if(cursor != null && cursor.moveToFirst()){
                int indiceName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int idiceNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String nombre = cursor.getString(indiceName);
                String numero = cursor.getString(idiceNumber);
                etMsj.setText(nombre);
                etCel.setText(numero);

            }

        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }
    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    mensaje2.setText("Mi direccion es: \n"
                            + DirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        SosActivity sosActivity;
        public SosActivity  getMainActivity() {
            return sosActivity ;
        }
        public void setMainActivity(SosActivity mainActivity) {
            this.sosActivity = mainActivity;
        }
        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();
            String Text = "Mis cordenadas son: : " + "\n Lat = "
                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
            mensaje1.setText(Text);
            this.sosActivity .setLocation(loc);
        }
        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            mensaje1.setText("GPS Desactivado");
        }
        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            mensaje1.setText("GPS Activado");
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }
    public  void Guardar (View view){
        String nombre= nombresos.getText().toString();
        String telefono = telefonosos.getText().toString();
        SharedPreferences preferences = getSharedPreferences( "agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString(nombre,telefono);
        obj_editor.commit();
        Toast.makeText(SosActivity.this, "CONTACTO GUARDADO", Toast.LENGTH_LONG).show();
    }
}