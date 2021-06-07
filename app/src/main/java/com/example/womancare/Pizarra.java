package com.example.womancare;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class Pizarra extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    Button regresar;
    private TextView mTextView;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizarra);
        regresar=(Button)findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent( Pizarra.this,UnoActivity.class);
                startActivity(o );

            }
        });
        mTextView = (TextView) findViewById(R.id.myTextView);
        this.gestureDetector = new GestureDetector(this,this);
        gestureDetector.setOnDoubleTapListener(this);
    }

    @Override
    // dibujar en pizarra
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    @Override
    //un solo toque
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Intent intent = new Intent(Pizarra.this, MapsVerdee.class);
        startActivity( intent );
        mTextView.setText("Zona Segura");
        return false;
    }
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        //doble toque
        Intent intent = new Intent(Pizarra.this, MensajeActivity.class);
        startActivity( intent );
        mTextView.setText("Mesaje");
        return false;
    }
    @Override
    // pendiente
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        mTextView.setText("");
        return false;
    }
    @Override
    // un toque
    public boolean onDown(MotionEvent motionEvent) {
        mTextView.setText("");
        return false;
    }
    @Override
    public void onShowPress(MotionEvent motionEvent) {
        mTextView.setText("");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        mTextView.setText("");
        return false;
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        Intent intent = new Intent(Pizarra.this,LlamaDosActivity.class);
        startActivity( intent );
        mTextView.setText("Llamada");

        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        // manten precionado
        Intent i= new Intent(Intent.ACTION_CALL, Uri.parse("tel:918 25 50 "));
        if(ActivityCompat.checkSelfPermission(Pizarra.this, Manifest.permission.CALL_PHONE)!=
                PackageManager.PERMISSION_GRANTED)
            return;
        startActivity(i);
        mTextView.setText("líneas de atención");

    }

    @Override
    // hacia abajo
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Intent intent = new Intent(Pizarra.this, CorreoActivity.class);
        startActivity( intent );
        mTextView.setText("Correo");
        return false;
    }
}