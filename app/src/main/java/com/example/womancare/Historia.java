package com.example.womancare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Historia extends AppCompatActivity {
Button megusta,nomegusta,btncontador;
Button megusta2,nomegusta2,btncontador2;
Button megusta3,nomegusta3,btncontador3;
Button megusta4,nomegusta4,btncontador4;
Button megusta5,nomegusta5,btncontador5;
TextView tv_valor;
    Button regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);
        megusta = findViewById(R.id.btn_azul);
        nomegusta =  findViewById(R.id.btn_gris);


        megusta2 = findViewById(R.id.btn_azul2);
        nomegusta2 =  findViewById(R.id.btn_gris2);

        megusta3 = findViewById(R.id.btn_azul3);
        nomegusta3 =  findViewById(R.id.btn_gris3);

        megusta4= findViewById(R.id.btn_azul4);
        nomegusta4=  findViewById(R.id.btn_gris4);

        megusta5 = findViewById(R.id.btn_azul5);
        nomegusta5 =  findViewById(R.id.btn_gris5);




        regresar=(Button)findViewById(R.id.regresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent( Historia.this,MainActivity.class);
                startActivity(o );

            }
        });



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("usuario").child("matias").child("megusta");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);

                if(dataSnapshot.exists()){
                    if(val.equals("si")){
                        megusta.setVisibility(View.VISIBLE);
                        nomegusta.setVisibility(View.GONE);

                    }else {
                        megusta.setVisibility(View.GONE);
                        nomegusta.setVisibility(View.VISIBLE);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//----------------------------------------------------------------------------------------------------------------

        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference myref2 = database.getReference("usuario").child("matias").child("megusta");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);

                if(dataSnapshot.exists()){
                    if(val.equals("si")){
                        megusta2.setVisibility(View.VISIBLE);
                        nomegusta2.setVisibility(View.GONE);

                    }else {
                        megusta2.setVisibility(View.GONE);
                        nomegusta2.setVisibility(View.VISIBLE);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//------------------------------------------------------------------------------

        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference myref3 = database.getReference("usuario").child("matias").child("megusta");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);

                if(dataSnapshot.exists()){
                    if(val.equals("si")){
                        megusta3.setVisibility(View.VISIBLE);
                        nomegusta3.setVisibility(View.GONE);

                    }else {
                        megusta3.setVisibility(View.GONE);
                        nomegusta3.setVisibility(View.VISIBLE);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//---------------------------------------------------------------------------------------------------
        FirebaseDatabase database4 = FirebaseDatabase.getInstance();
        DatabaseReference myref4 = database.getReference("usuario").child("matias").child("megusta");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);

                if(dataSnapshot.exists()){
                    if(val.equals("si")){
                        megusta4.setVisibility(View.VISIBLE);
                        nomegusta4.setVisibility(View.GONE);

                    }else {
                        megusta4.setVisibility(View.GONE);
                        nomegusta4.setVisibility(View.VISIBLE);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //-------------------------------------------

        FirebaseDatabase database5 = FirebaseDatabase.getInstance();
        DatabaseReference myref5 = database.getReference("usuario").child("matias").child("megusta");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String val = dataSnapshot.getValue(String.class);

                if(dataSnapshot.exists()){
                    if(val.equals("si")){
                        megusta5.setVisibility(View.VISIBLE);
                        nomegusta5.setVisibility(View.GONE);

                    }else {
                        megusta5.setVisibility(View.GONE);
                        nomegusta5.setVisibility(View.VISIBLE);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        megusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref = database.getReference("usuario").child("matias").child("megusta");
                myref.setValue("no");
                Toast.makeText(Historia.this," me gusta",Toast.LENGTH_SHORT).show();

            }
        });
        nomegusta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myref = database.getReference("usuario").child("matias").child("megusta");
                myref.setValue("si");
                Toast.makeText(Historia.this," no me gusta",Toast.LENGTH_SHORT).show();

            }
        });

        megusta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                DatabaseReference myref2 = database2.getReference("usuario").child("matias").child("megusta");
                myref2.setValue("no");
                Toast.makeText(Historia.this," me gusta",Toast.LENGTH_SHORT).show();

            }
        });
        nomegusta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database2 = FirebaseDatabase.getInstance();
                DatabaseReference myref2 = database2.getReference("usuario").child("matias").child("megusta");
                myref2.setValue("si");
                Toast.makeText(Historia.this," no me gusta",Toast.LENGTH_SHORT).show();

            }
        });


        megusta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database3 = FirebaseDatabase.getInstance();
                DatabaseReference myref3 = database3.getReference("usuario").child("matias").child("megusta");
                myref3.setValue("no");
                Toast.makeText(Historia.this," me gusta",Toast.LENGTH_SHORT).show();

            }
        });
        nomegusta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database3 = FirebaseDatabase.getInstance();
                DatabaseReference myref3 = database3.getReference("usuario").child("matias").child("megusta");
                myref3.setValue("si");
                Toast.makeText(Historia.this," no me gusta",Toast.LENGTH_SHORT).show();

            }
        });


        megusta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database4 = FirebaseDatabase.getInstance();
                DatabaseReference myref4 = database4.getReference("usuario").child("matias").child("megusta");
                myref4.setValue("no");
                Toast.makeText(Historia.this," me gusta",Toast.LENGTH_SHORT).show();

            }
        });
        nomegusta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database4 = FirebaseDatabase.getInstance();
                DatabaseReference myref4 = database4.getReference("usuario").child("matias").child("megusta");
                myref4.setValue("si");
                Toast.makeText(Historia.this," no me gusta",Toast.LENGTH_SHORT).show();

            }
        });


        megusta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database5 = FirebaseDatabase.getInstance();
                DatabaseReference myref5 = database5.getReference("usuario").child("matias").child("megusta");
                myref5.setValue("no");
                Toast.makeText(Historia.this," me gusta",Toast.LENGTH_SHORT).show();

            }
        });
        nomegusta5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database5 = FirebaseDatabase.getInstance();
                DatabaseReference myref5 = database5.getReference("usuario").child("matias").child("megusta");
                myref5.setValue("si");
                Toast.makeText(Historia.this," no me gusta",Toast.LENGTH_SHORT).show();

            }
        });

    }
}