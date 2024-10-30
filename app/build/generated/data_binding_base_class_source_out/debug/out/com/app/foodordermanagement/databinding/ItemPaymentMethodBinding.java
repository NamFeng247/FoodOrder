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

public final class ItemPaymentMethodBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView imgPaymentMethod;

  @NonNull
  public final ImageView imgStatus;

  @NonNull
  public final LinearLayout layoutItem;

  @NonNull
  public final TextView tvDescription;

  @NonNull
  public final TextView tvName;

  private ItemPaymentMethodBinding(@NonNull LinearLayout rootView,
      @NonNull ImageView imgPaymentMethod, @NonNull ImageView imgStatus,
      @NonNull LinearLayout layoutItem, @NonNull TextView tvDescription, @NonNull TextView tvName) {
    this.rootView = rootView;
    this.imgPaymentMethod = imgPaymentMethod;
    this.imgStatus = imgStatus;
    this.layoutItem = layoutItem;
    this.tvDescription = tvDescription;
    this.tvName = tvName;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemPaymentMethodBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemPaymentMethodBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_payment_method, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemPaymentMethodBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_payment_method;
      ImageView imgPaymentMethod = rootView.findViewById(id);
      if (imgPaymentMethod == null) {
        break missingId;
      }

      id = R.id.img_status;
      ImageView imgStatus = rootView.findViewById(id);
      if (imgStatus == null) {
        break missingId;
      }

      LinearLayout layoutItem = (LinearLayout) rootView;

      id = R.id.tv_description;
      TextView tvDescription = rootView.findViewById(id);
      if (tvDescription == null) {
        break missingId;
      }

      id = R.id.tv_name;
      TextView tvName = rootView.findViewById(id);
      if (tvName == null) {
        break missingId;
      }

      return new ItemPaymentMethodBinding((LinearLayout) rootView, imgPaymentMethod, imgStatus,
          layoutItem, tvDescription, tvName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}