package com.example.womancare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGriddd ;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainGriddd = (GridLayout) findViewById(R.id.mainGriddd);

        //Set Event
        setSingleEvent(mainGriddd);
        //setToggleEvent(mainGrid);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Intent.ACTION_CALL, Uri.parse("tel:909"));
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(i);

            }
        });


// qui enpisa lo del menu


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery,R.id.mujeres,R.id.nav_slideshow,R.id.mapsVerdee,R.id.mapsAmarilloo,R.id.mapsRojoo,R.id.gestosverdes,R.id.fotos2)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    // aqui acaba lo del menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.informacion:
                Intent intent = new Intent(MainActivity.this,Informacion.class);
                startActivity( intent );
                break;

            case R.id.fotos:
                intent = new Intent( MainActivity.this, Imagenes.class );
                startActivity( intent );
                break;

            case R.id.action_settings:
                intent = new Intent( MainActivity.this, Historia.class );
                startActivity( intent );
                break;

        }

        return super.onOptionsItemSelected(item);
    }

// tine relacion con lo del menu
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    private void setToggleEvent(GridLayout mainGridd) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGridd.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGridd.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor( Color.parseColor("#FF6F00"));
                        Toast.makeText(MainActivity.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(MainActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGriddd) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGriddd.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGriddd.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if ( finalI ==  0 )
                    {

                        Intent intent = new Intent(MainActivity.this,UnoActivity.class);
                        startActivity( intent );
                    } else
                    if ( finalI ==  1 )
                    {

                        Intent intent = new Intent(MainActivity.this,DosActivity.class);
                        startActivity( intent );
                    }
                    else
                    if ( finalI ==  2 )
                    {

                        Intent intent = new Intent(MainActivity.this,TresActivity .class);
                        startActivity( intent );
                    }
                    else
                    if ( finalI ==  3)
                    {

                        Intent intent = new Intent(MainActivity.this,CuatroActivity.class);
                        startActivity( intent );
                    }





                }
            });

        }}}