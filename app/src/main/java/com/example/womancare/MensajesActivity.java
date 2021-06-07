package com.example.womancare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.womancare.adapters.AdapterChatLista;
import com.example.womancare.adapters.AdapterChats;
import com.example.womancare.pojos.Chats;
import com.example.womancare.pojos.Estado;
import com.google.android.gms.maps.model.Circle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class MensajesActivity extends AppCompatActivity {
    CircleImageView img_user;
    TextView username;
    ImageView ic_conectado,ic_desconetado;
    SharedPreferences mPref;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref_estado = database.getReference("Estado").child(user.getUid());
    DatabaseReference ref_chat = database.getReference("Chats");

    EditText et_mensaje_text;
    ImageButton  btn_enviar_msj;

    //id chat global
    String id_chat_global;
    Boolean amigooline = false;

    RecyclerView rv_chats;
    AdapterChats adapter;
    ArrayList<Chats> chatlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);


        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);///bolver hacia atras
        mPref = getApplicationContext().getSharedPreferences("usuario_sp",MODE_PRIVATE);
        img_user = findViewById(R.id.img_user);
        username  = findViewById(R.id.tv_user);
        ic_conectado  = findViewById(R.id.icon_conectado);
        ic_desconetado = findViewById(R.id.icon_desconectado);


        String usuario = getIntent().getExtras().getString("nombre");
        String foto = getIntent().getExtras().getString("img_2");
        final String id_user= getIntent().getExtras().getString("id_user");
        id_chat_global = getIntent().getExtras().getString("id_unico");
        colocarenvisto();


        et_mensaje_text = findViewById(R.id.et_tex_mensaje);
        btn_enviar_msj = findViewById(R.id.btn_enviar_msj);
        btn_enviar_msj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
   String msj = et_mensaje_text.getText().toString();
                if(!msj.isEmpty()){
                    final Calendar c = Calendar.getInstance();
                    final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    final SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String idpush = ref_chat.push().getKey();
                    if(amigooline){
                        Chats chatsmsj = new Chats(idpush,user.getUid(),id_user,msj,"si",dataFormat.format(c.getTime()),timeFormat.format(c.getTime()));
                        ref_chat.child(id_chat_global).child(idpush).setValue(chatsmsj);
                        Toast.makeText(MensajesActivity.this,"Mensaje enviado", Toast.LENGTH_SHORT).show();
                        et_mensaje_text.setText("");

                    }else {
                        Chats chatsmsj = new Chats(idpush,user.getUid(),id_user,msj,"no",dataFormat.format(c.getTime()),timeFormat.format(c.getTime()));
                        ref_chat.child(id_chat_global).child(idpush).setValue(chatsmsj);
                        Toast.makeText(MensajesActivity.this,"Mensaje enviado", Toast.LENGTH_SHORT).show();
                        et_mensaje_text.setText("");



                    }
                }else{

                    Toast.makeText(MensajesActivity.this,"El mensaje esta vacio",Toast.LENGTH_SHORT).show();
                }

            }
        });


        final String id_user_sp = mPref.getString("usuario_sp","");

        username.setText(usuario);
        Glide.with(this).load(foto).into(img_user);
        final DatabaseReference ref = database.getReference("Estado").child(id_user_sp).child("chatcon");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              String chatcon = dataSnapshot.getValue(String.class);
              if (chatcon.equals(user.getUid())){
                  amigooline=true;
                ic_conectado.setVisibility(View.VISIBLE);
                ic_desconetado.setVisibility(View.GONE);
              }else {
                  amigooline=false;
                  ic_conectado.setVisibility(View.GONE);
                  ic_desconetado.setVisibility(View.VISIBLE);
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
   // rv-----------------
        rv_chats = findViewById(R.id.rv);
        rv_chats.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        rv_chats.setLayoutManager(linearLayoutManager);

        chatlist = new ArrayList<>();
        adapter = new AdapterChats(chatlist,this);
        rv_chats.setAdapter(adapter);
        
        Leermenajes();



    }// fin del on

    private void colocarenvisto() {
        ref_chat.child(id_chat_global).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chats chats = snapshot.getValue(Chats.class);
                    if(chats.getRecibe().equals(user.getUid())){
                        ref_chat.child(id_chat_global).child(chats.getId()).child("visto").setValue("si");
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void Leermenajes() {
        ref_chat.child(id_chat_global).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    chatlist.removeAll(chatlist);
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Chats chats = snapshot.getValue(Chats.class);
                        chatlist.add(chats);
                        setScroll();
                        
                    }
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setScroll() {
        rv_chats.scrollToPosition(adapter.getItemCount()-1);

    }

    private void estadousuario(final String estado) {
        ref_estado.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String id_user_sp = mPref.getString("usuario_sp","");
                Estado est = new Estado(estado,"","",id_user_sp);
                ref_estado.setValue(est);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        estadousuario("conectadoo");
    }

    @Override
    protected void onPause() {
        super.onPause();
        estadousuario("desconectado");
        dameultimafecha();
    }

    private void dameultimafecha() {
        final Calendar c = Calendar.getInstance();
        final SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
        final SimpleDateFormat deteFormat = new SimpleDateFormat("dd/MM/yyyy");
        ref_estado.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ref_estado.child("fecha").setValue(deteFormat.format(c.getTime())) ;
                ref_estado.child("hora").setValue(timeformat.format(c.getTime())) ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}