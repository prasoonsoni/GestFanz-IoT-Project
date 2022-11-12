package com.prasoon.gestfanz.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.prasoon.gestfanz.fragments.DefaultFragment;
import com.prasoon.gestfanz.fragments.GesturesFragment;
import com.prasoon.gestfanz.fragments.SpeedFragment;

public class PagerAdapter extends FragmentStateAdapter {
    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new SpeedFragment();
            case 1: return new GesturesFragment();
            case 2: return new DefaultFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
