package com.example.womancare.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.womancare.R;
import com.example.womancare.adapters.AdapterUsuarios;
import com.example.womancare.pojos.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chatsFragment extends Fragment {
    public chatsFragment(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final ProgressBar progressBar;

        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        View view = inflater.inflate(R.layout.fragment_chats,container,false);

        progressBar = view.findViewById(R.id.progessbar);
        assert user!=null;

        final RecyclerView rv;
        final ArrayList<Users>usersArrayList;
        final AdapterUsuarios adapter;
        LinearLayoutManager mLayoutManager;

        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(mLayoutManager);
        usersArrayList = new ArrayList<>();
        adapter = new AdapterUsuarios(usersArrayList,getContext());
        rv.setAdapter(adapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref= database.getReference("Users");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    rv.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    usersArrayList.removeAll(usersArrayList);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Users users = snapshot.getValue(Users.class);
                        usersArrayList.add(users);
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"no existen usuarios", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return  view;

    }

}