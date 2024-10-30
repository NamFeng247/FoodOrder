package com.app.foodordermanagement.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.foodordermanagement.utils.GlobalFunction;
import com.google.firebase.auth.FirebaseAuth;
import com.app.foodordermanagement.R;
import com.app.foodordermanagement.activity.AdminMainActivity;
import com.app.foodordermanagement.activity.LoginActivity;
import com.app.foodordermanagement.databinding.FragmentAdminAccountBinding;
import com.app.foodordermanagement.prefs.DataStoreManager;

public class AdminAccountFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAdminAccountBinding fragmentAdminAccountBinding = FragmentAdminAccountBinding.inflate(inflater, container, false);

        fragmentAdminAccountBinding.tvEmail.setText(DataStoreManager.getUser().getEmail());
        fragmentAdminAccountBinding.layoutSignOut.setOnClickListener(v -> onClickSignOut());

        return fragmentAdminAccountBinding.getRoot();
    }

    @Override
    protected void initToolbar() {
        if (getActivity() != null) {
            ((AdminMainActivity) getActivity()).setToolBar(getString(R.string.account));
        }
    }


    private void onClickSignOut() {
        if (getActivity() == null) return;

        FirebaseAuth.getInstance().signOut();
        DataStoreManager.setUser(null);
        GlobalFunction.startActivity(getActivity(), LoginActivity.class);
        getActivity().finishAffinity();
    }
}
