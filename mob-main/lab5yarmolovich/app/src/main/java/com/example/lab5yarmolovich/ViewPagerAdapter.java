package com.example.lab5yarmolovich;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new com.example.lab5yarmolovich.FragmentShow();
            case 1: return new FragmentAdd();
            case 2: return new com.example.lab5yarmolovich.FragmentDel();
            case 3: return new com.example.lab5yarmolovich.FragmentUpdate();
            default: return new com.example.lab5yarmolovich.FragmentShow();
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Количество фрагментов
    }
}