package com.example.womancare.adapters;

import android.content.Context;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.womancare.R;
import com.example.womancare.pojos.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdapterChatLista extends RecyclerView.Adapter<AdapterChatLista.viewHolderAdapterchatlist>{
    List<Users> usersList;
    Context context;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public AdapterChatLista(List<Users> usersList, Context context) {
        this.usersList = usersList;
        this.context = context;

    }

    @NonNull
    @Override
    public viewHolderAdapterchatlist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chatlista,parent,false);
        viewHolderAdapterchatlist holder = new viewHolderAdapterchatlist(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolderAdapterchatlist holder, final int position) {
        final Users userss = usersList.get(position);
        final Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        holder.tv_user2.setText(userss.getNombre());
        Glide.with(context).load(userss.getFoto()).into(holder.img_2);
        DatabaseReference ref_mis_solicitudes = database.getReference("Solicitudes").child(user.getUid());
        ref_mis_solicitudes.child(userss.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String estado = dataSnapshot.child("Estado").getValue(String.class);
                if (dataSnapshot.exists()){
                 if (estado.equals("amigos")){
                     holder.cardView.setVisibility(View.VISIBLE);
                 } else {
                     holder.cardView.setVisibility(View.GONE);
                 }

                }else {
                    holder.cardView.setVisibility(View.GONE);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final Calendar c = Calendar.getInstance();
        final SimpleDateFormat deteFormat = new SimpleDateFormat("dd/MM/yyyy");
        DatabaseReference ref_Estado = database.getReference("Estado").child(userss.getId());
        ref_Estado.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String estado = dataSnapshot.child("estado").getValue(String.class);
                String fecha = dataSnapshot.child("fecha").getValue(String.class);
                String hora = dataSnapshot.child("hora").getValue(String.class);
                if (dataSnapshot.exists()){
                    if(estado.equals("conectado")){
                        holder.tv_conectado.setVisibility(View.VISIBLE);
                        holder.icon_conectado.setVisibility(View.VISIBLE);
                        holder.tv_desconectado.setVisibility(View.GONE);
                        holder.icon_desconectado.setVisibility(View.GONE);

                    }else {
                        holder.tv_desconectado.setVisibility(View.VISIBLE);
                        holder.icon_desconectado.setVisibility(View.VISIBLE);
                        holder.tv_conectado.setVisibility(View.GONE);
                        holder.icon_conectado.setVisibility(View.GONE);
                        if(fecha.equals(deteFormat.format(c.getTime()))){
                            holder.tv_desconectado.setText("ult.vez hoy a las "+ hora);
                        }else {
                            holder.tv_desconectado.setText("ult.vez  "+ fecha +"a las "+ hora);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }//final

    @Override
    public int getItemCount() {
        return usersList.size();
    }



public class viewHolderAdapterchatlist extends RecyclerView.ViewHolder {
    TextView tv_user2;
    ImageView img_2;
    CardView cardView;
    TextView tv_conectado,tv_desconectado;
    ImageView icon_conectado,icon_desconectado;

    public viewHolderAdapterchatlist(@NonNull View itemView) {

        super(itemView);
        tv_user2 = itemView.findViewById(R.id.tv_user);
         img_2 = itemView.findViewById(R.id.img_user);
         cardView= itemView.findViewById(R.id.cardview);
         tv_conectado = itemView.findViewById(R.id.tv_conectado);
         tv_desconectado = itemView.findViewById(R.id.tv_desconectado);
         icon_conectado = itemView.findViewById(R.id.icon_conectado);
         icon_desconectado = itemView.findViewById(R.id.tv_desconectado);




    }
}
}
