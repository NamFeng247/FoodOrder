// Generated by view binder compiler. Do not edit!
package com.app.foodordermanagement.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.app.foodordermanagement.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentAdminHomeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final FloatingActionButton btnAddFood;

  @NonNull
  public final EditText edtSearchName;

  @NonNull
  public final ImageView imgSearch;

  @NonNull
  public final RelativeLayout layoutSearch;

  @NonNull
  public final RecyclerView rcvFood;

  private FragmentAdminHomeBinding(@NonNull RelativeLayout rootView,
      @NonNull FloatingActionButton btnAddFood, @NonNull EditText edtSearchName,
      @NonNull ImageView imgSearch, @NonNull RelativeLayout layoutSearch,
      @NonNull RecyclerView rcvFood) {
    this.rootView = rootView;
    this.btnAddFood = btnAddFood;
    this.edtSearchName = edtSearchName;
    this.imgSearch = imgSearch;
    this.layoutSearch = layoutSearch;
    this.rcvFood = rcvFood;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentAdminHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentAdminHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_admin_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentAdminHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_add_food;
      FloatingActionButton btnAddFood = rootView.findViewById(id);
      if (btnAddFood == null) {
        break missingId;
      }

      id = R.id.edt_search_name;
      EditText edtSearchName = rootView.findViewById(id);
      if (edtSearchName == null) {
        break missingId;
      }

      id = R.id.img_search;
      ImageView imgSearch = rootView.findViewById(id);
      if (imgSearch == null) {
        break missingId;
      }

      id = R.id.layout_search;
      RelativeLayout layoutSearch = rootView.findViewById(id);
      if (layoutSearch == null) {
        break missingId;
      }

      id = R.id.rcv_food;
      RecyclerView rcvFood = rootView.findViewById(id);
      if (rcvFood == null) {
        break missingId;
      }

      return new FragmentAdminHomeBinding((RelativeLayout) rootView, btnAddFood, edtSearchName,
          imgSearch, layoutSearch, rcvFood);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
