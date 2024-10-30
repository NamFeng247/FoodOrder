// Generated by view binder compiler. Do not edit!
package com.app.foodordermanagement.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.app.foodordermanagement.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddressBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnAddAddress;

  @NonNull
  public final RecyclerView rcvAddress;

  @NonNull
  public final LayoutToolbarBinding toolbar;

  private ActivityAddressBinding(@NonNull RelativeLayout rootView, @NonNull Button btnAddAddress,
      @NonNull RecyclerView rcvAddress, @NonNull LayoutToolbarBinding toolbar) {
    this.rootView = rootView;
    this.btnAddAddress = btnAddAddress;
    this.rcvAddress = rcvAddress;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddressBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_address, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddressBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_add_address;
      Button btnAddAddress = rootView.findViewById(id);
      if (btnAddAddress == null) {
        break missingId;
      }

      id = R.id.rcv_address;
      RecyclerView rcvAddress = rootView.findViewById(id);
      if (rcvAddress == null) {
        break missingId;
      }

      id = R.id.toolbar;
      View toolbar = rootView.findViewById(id);
      if (toolbar == null) {
        break missingId;
      }
      LayoutToolbarBinding binding_toolbar = LayoutToolbarBinding.bind(toolbar);

      return new ActivityAddressBinding((RelativeLayout) rootView, btnAddAddress, rcvAddress,
          binding_toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
