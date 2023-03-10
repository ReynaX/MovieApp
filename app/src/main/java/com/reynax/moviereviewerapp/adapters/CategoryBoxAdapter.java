package com.reynax.moviereviewerapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.reynax.moviereviewerapp.fragments.MoviesFragment;
import com.reynax.moviereviewerapp.fragments.SeriesFragment;

public class CategoryBoxAdapter extends FragmentStateAdapter {

    public CategoryBoxAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public CategoryBoxAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public CategoryBoxAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new MoviesFragment();
            case 1:
                return new SeriesFragment();
        }
        return new MoviesFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
