package com.app.foodordermanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.app.foodordermanagement.fragment.AdminAccountFragment;
import com.app.foodordermanagement.fragment.AdminFeedbackFragment;
import com.app.foodordermanagement.fragment.AdminHomeFragment;
import com.app.foodordermanagement.fragment.AdminOrderFragment;

public class AdminViewPagerAdapter extends FragmentStateAdapter {

    public AdminViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AdminHomeFragment();

            case 1:
                return new AdminFeedbackFragment();

            case 2:
                return new AdminOrderFragment();

            case 3:
                return new AdminAccountFragment();

            default:
                return new AdminHomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
