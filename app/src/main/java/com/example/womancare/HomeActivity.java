package com.example.womancare;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.womancare.adapters.PaginasAdapter;
import com.example.womancare.pojos.Estado;
import com.example.womancare.pojos.Users;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref_user = database.getReference("Users").child(user.getUid());
    DatabaseReference ref_solicitudes_count = database.getReference("Contador").child(user.getUid());
    DatabaseReference ref_estado = database.getReference("Estado").child(user.getUid());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(new PaginasAdapter(this));

        final TabLayout tabLayout = findViewById(R.id.tabLayout);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Users");
                        tab.setIcon(R.drawable.ic_baseline_supervised_user_circle_24);
                        break;
                    case 1:
                        tab.setText("Chats");
                        tab.setIcon(R.drawable.ic_chats);
                        break;




                }
            }
        });
        tabLayoutMediator.attach();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BadgeDrawable badgeDrawable= tabLayout.getTabAt(position).getOrCreateBadge();
                badgeDrawable.setVisible(false);

                if(position==2){
                    countacero();
                }
            }
        });

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userunico();

    }//final

    private void estadousuario(final String estado) {
      ref_estado.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              Estado est = new Estado(estado,"","","");
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

    private void countacero() {
        ref_solicitudes_count.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    ref_solicitudes_count.setValue(0);
                    Toast.makeText(HomeActivity.this,"count badge a 0",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void userunico() {

        ref_user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (!snapshot.exists())
                {
                    Users uu = new Users(
                            user.getUid(),
                            user.getDisplayName(),
                            user.getEmail(),
                            user.getPhotoUrl().toString(),
                            "desconectado","28/04/2021","12:18",0,0
                    );
                    ref_user.setValue(uu);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

  

    private void vamosalogin() {
        Intent i = new Intent (this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}