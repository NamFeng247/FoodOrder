// Generated by view binder compiler. Do not edit!
package com.app.foodordermanagement.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.app.foodordermanagement.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LayoutToolbarBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView imgToolbarBack;

  @NonNull
  public final LinearLayout layoutToolbar;

  @NonNull
  public final TextView tvToolbarTitle;

  private LayoutToolbarBinding(@NonNull LinearLayout rootView, @NonNull ImageView imgToolbarBack,
      @NonNull LinearLayout layoutToolbar, @NonNull TextView tvToolbarTitle) {
    this.rootView = rootView;
    this.imgToolbarBack = imgToolbarBack;
    this.layoutToolbar = layoutToolbar;
    this.tvToolbarTitle = tvToolbarTitle;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LayoutToolbarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LayoutToolbarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.layout_toolbar, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LayoutToolbarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_toolbar_back;
      ImageView imgToolbarBack = rootView.findViewById(id);
      if (imgToolbarBack == null) {
        break missingId;
      }

      LinearLayout layoutToolbar = (LinearLayout) rootView;

      id = R.id.tv_toolbar_title;
      TextView tvToolbarTitle = rootView.findViewById(id);
      if (tvToolbarTitle == null) {
        break missingId;
      }

      return new LayoutToolbarBinding((LinearLayout) rootView, imgToolbarBack, layoutToolbar,
          tvToolbarTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
