package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class CuatroActivity extends AppCompatActivity {
    GridLayout mainGriddddtt;
    Button regresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_menu);

        mainGriddddtt = (GridLayout) findViewById(R.id.mainGriddddtt);
        //Set Event
        setSingleEvent(mainGriddddtt);
        //setToggleEvent(mainGrid);
        regresar=(Button)findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent( CuatroActivity.this,MainActivity.class);
                startActivity(o );

            }
        });
    }

    private void setToggleEvent(GridLayout mainGriddddtt) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGriddddtt.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGriddddtt.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor( Color.parseColor("#FF6F00"));
                        Toast.makeText( CuatroActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText( CuatroActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGriddddtt) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGriddddtt.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGriddddtt.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if ( finalI ==  0 )
                    {

                        Intent intent = new Intent( CuatroActivity.this,MapsVerdee.class);
                        startActivity( intent );
                    }
                    else
                    if ( finalI ==  1 )
                    {

                        Intent intent = new Intent( CuatroActivity.this,MapsAmarilloo.class);
                        startActivity( intent );
                    }
                    else
                    if ( finalI ==  2)
                    {

                        Intent intent = new Intent( CuatroActivity.this,MapsRojoo.class);
                        startActivity( intent );
                    }



                }
            });

        }}}