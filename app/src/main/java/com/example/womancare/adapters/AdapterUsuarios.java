package com.example.womancare.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.womancare.MensajesActivity;
import com.example.womancare.R;
import com.example.womancare.pojos.Solicitudes;
import com.example.womancare.pojos.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


    public class AdapterUsuarios  extends RecyclerView.Adapter<AdapterUsuarios.viewHolderAdapter>{
        List<Users> usersList;
        Context context;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        SharedPreferences mPref;
        public AdapterUsuarios(List<Users> usersList, Context context) {
            this.usersList = usersList;
            this.context = context;

        }

        @NonNull
        @Override
        public viewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_usuarios,parent,false);
            viewHolderAdapter holder = new viewHolderAdapter(v);

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull final viewHolderAdapter holder, final int position) {
            final Users userss = usersList.get(position);
            final Vibrator vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);

            Glide.with(context).load(userss.getFoto()).into(holder.img_2);
            holder.tv_user2.setText(userss.getNombre());

            if(userss.getId().equals(user.getUid())){

                holder.cardView.setVisibility(View.GONE);

            }else{
                holder.cardView.setVisibility(View.VISIBLE);
            }

            final DatabaseReference ref_mis_botones = database.getReference("Solicitudes").child(user.getUid());
            ref_mis_botones.child(userss.getId()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String estado = dataSnapshot.child("estado").getValue(String.class);
                    if (dataSnapshot.exists()){
                        if(estado.equals("enviado")){
                            holder.send.setVisibility(View.VISIBLE);
                            holder.amigos.setVisibility(View.GONE);
                            holder.tengosolicitudes.setVisibility(View.GONE);
                            holder.add.setVisibility(View.GONE);
                            holder.progressBar.setVisibility(View.GONE);

                        } if(estado.equals("amigos")){
                            holder.send.setVisibility(View.GONE);
                            holder.amigos.setVisibility(View.VISIBLE);
                            holder.tengosolicitudes.setVisibility(View.GONE);
                            holder.add.setVisibility(View.GONE);
                            holder.progressBar.setVisibility(View.GONE);

                        }
                        if(estado.equals("solicitud")){
                            holder.send.setVisibility(View.GONE);
                            holder.amigos.setVisibility(View.GONE);
                            holder.tengosolicitudes.setVisibility(View.VISIBLE);
                            holder.add.setVisibility(View.GONE);
                            holder.progressBar.setVisibility(View.GONE);
                        }
                    }else {
                        holder.send.setVisibility(View.GONE);
                        holder.amigos.setVisibility(View.GONE);
                        holder.tengosolicitudes.setVisibility(View.GONE);
                        holder.add.setVisibility(View.VISIBLE);
                        holder.progressBar.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final DatabaseReference A = database.getReference("Solicitudes").child(user.getUid());
                    A.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Solicitudes sol = new Solicitudes("enviado","");

                            A.child(userss.getId()).setValue(sol);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    final DatabaseReference B = database.getReference("Solicitudes").child(userss.getId());
                    B.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Solicitudes sol = new Solicitudes("solicitud","");

                            B.child(user.getUid()).setValue(sol);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    final DatabaseReference count = database.getReference("Contador").child(userss.getId());
                    count.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()){
                                Integer val = dataSnapshot.getValue(Integer.class);
                                if (val==0){
                                    count.setValue(1);

                                }
                                else {count.setValue(val+1);
                                }
                            }else {count.setValue(1);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }// fin d
            });













                    holder.tengosolicitudes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final  String idchat= ref_mis_botones.push().getKey();
                            final DatabaseReference A = database.getReference("Solicitudes").child(userss.getId()).child(user.getUid());
                            A.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Solicitudes sol = new Solicitudes("amigos",idchat);
                                    A.setValue(sol);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            final DatabaseReference B = database.getReference("Solicitudes").child(user.getUid()).child(userss.getId());
                            B.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Solicitudes sol = new Solicitudes("amigos",idchat);
                                    B.setValue(sol);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            //vi
                        }
                    });
                    holder.amigos.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View v) {
                            mPref = v.getContext().getSharedPreferences("usuario_sp",Context.MODE_PRIVATE);
                            final SharedPreferences.Editor editor = mPref.edit();
                            final DatabaseReference ref = database.getReference("Solicitudes").child(user.getUid()).child(userss.getId()).child("idchat");
                  ref.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String id_unico = dataSnapshot.getValue(String.class);
        if(dataSnapshot.exists()){
            Intent intent = new Intent(v.getContext(), MensajesActivity.class);
            intent.putExtra("nombre",userss.getNombre());
            intent.putExtra("img_2",userss.getFoto());
            intent.putExtra("id_user",userss.getId());
            intent.putExtra("id_unico",id_unico);
            editor.putString("usuario_sp",userss.getId());
            editor.apply();
                   v.getContext().startActivity(intent);

        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
                        }
                    });
                }// final

        @Override
        public int getItemCount() {
            return usersList.size();
        }



    public class viewHolderAdapter extends RecyclerView.ViewHolder {
        TextView tv_user2;
        ImageView img_2;
        CardView cardView;
        Button add,send,amigos,tengosolicitudes;
        ProgressBar progressBar;

        public viewHolderAdapter(@NonNull View itemView) {

            super(itemView);
            tv_user2 = itemView.findViewById(R.id.tv_user);
             img_2 = itemView.findViewById(R.id.img_user);
             cardView= itemView.findViewById(R.id.cardview);
             add= itemView.findViewById(R.id.btn_add);
            send= itemView.findViewById(R.id.btn_send);
            amigos= itemView.findViewById(R.id.btn_amigos);
            tengosolicitudes= itemView.findViewById(R.id.btn_tengosolicitud);
            progressBar= itemView.findViewById(R.id.progessbar);
        }
    }
}
