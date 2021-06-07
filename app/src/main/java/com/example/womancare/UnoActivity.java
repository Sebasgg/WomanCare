package com.example.womancare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class UnoActivity extends AppCompatActivity {
    GridLayout mainGriddddt;
    Button regresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uno);

        mainGriddddt = (GridLayout) findViewById(R.id.mainGriddddt);

        //Set Event
        setSingleEvent(mainGriddddt);
        //setToggleEvent(mainGrid);
        regresar=(Button)findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent( UnoActivity.this,MainActivity.class);
                startActivity(o );

            }
        });

    }

    private void setToggleEvent(GridLayout mainGriddddt) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGriddddt.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGriddddt.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor( Color.parseColor("#FF6F00"));
                        Toast.makeText( UnoActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText( UnoActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGriddddt) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGriddddt.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGriddddt.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if ( finalI ==  0 )
                    {

                        Intent intent = new Intent( UnoActivity.this,Pizarra.class);
                        startActivity( intent );
                    }
                    else
                    if ( finalI ==  1 )
                    {

                        Intent intent = new Intent( UnoActivity.this,Gestosverdes.class);
                        startActivity( intent );
                    }
                    else
                    if ( finalI ==  2)
                    {

                        Intent intent = new Intent( UnoActivity.this,GestosAmarillos.class);
                        startActivity( intent );
                    }
                    else
                    if ( finalI ==  3)
                    {

                        Intent intent = new Intent( UnoActivity.this,GestosRojos.class);
                        startActivity( intent );
                    }




                }
            });

        }}}