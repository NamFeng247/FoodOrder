// Generated by view binder compiler. Do not edit!
package com.app.foodordermanagement.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.app.foodordermanagement.R;
import com.app.foodordermanagement.widget.CustomTabLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentHistoryBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CustomTabLayout tabOrder;

  @NonNull
  public final ViewPager2 viewPagerOrder;

  private FragmentHistoryBinding(@NonNull LinearLayout rootView, @NonNull CustomTabLayout tabOrder,
      @NonNull ViewPager2 viewPagerOrder) {
    this.rootView = rootView;
    this.tabOrder = tabOrder;
    this.viewPagerOrder = viewPagerOrder;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentHistoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentHistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_history, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentHistoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.tab_order;
      CustomTabLayout tabOrder = rootView.findViewById(id);
      if (tabOrder == null) {
        break missingId;
      }

      id = R.id.view_pager_order;
      ViewPager2 viewPagerOrder = rootView.findViewById(id);
      if (viewPagerOrder == null) {
        break missingId;
      }

      return new FragmentHistoryBinding((LinearLayout) rootView, tabOrder, viewPagerOrder);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}