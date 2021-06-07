package com.example.womancare.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.womancare.fragments.chatsFragment;
import com.example.womancare.fragments.mis_solicitudes_Fragment;
import com.example.womancare.fragments.solicitudesFragment;
import com.example.womancare.fragments.ususariosFragment;

public class PaginasAdapter extends FragmentStateAdapter {
    public PaginasAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){

            case 0 :
                return new ususariosFragment();
            case 1 :
                return new chatsFragment();

            default:
                return  new ususariosFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
