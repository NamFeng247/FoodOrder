// Generated by view binder compiler. Do not edit!
package com.app.foodordermanagement.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.app.foodordermanagement.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemOrderBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CircleImageView imgFood;

  @NonNull
  public final LinearLayout layoutAction;

  @NonNull
  public final LinearLayout layoutReview;

  @NonNull
  public final TextView tvAction;

  @NonNull
  public final TextView tvFoodsName;

  @NonNull
  public final TextView tvOrderId;

  @NonNull
  public final TextView tvQuantity;

  @NonNull
  public final TextView tvRate;

  @NonNull
  public final TextView tvReview;

  @NonNull
  public final TextView tvSuccess;

  @NonNull
  public final TextView tvTotal;

  private ItemOrderBinding(@NonNull LinearLayout rootView, @NonNull CircleImageView imgFood,
      @NonNull LinearLayout layoutAction, @NonNull LinearLayout layoutReview,
      @NonNull TextView tvAction, @NonNull TextView tvFoodsName, @NonNull TextView tvOrderId,
      @NonNull TextView tvQuantity, @NonNull TextView tvRate, @NonNull TextView tvReview,
      @NonNull TextView tvSuccess, @NonNull TextView tvTotal) {
    this.rootView = rootView;
    this.imgFood = imgFood;
    this.layoutAction = layoutAction;
    this.layoutReview = layoutReview;
    this.tvAction = tvAction;
    this.tvFoodsName = tvFoodsName;
    this.tvOrderId = tvOrderId;
    this.tvQuantity = tvQuantity;
    this.tvRate = tvRate;
    this.tvReview = tvReview;
    this.tvSuccess = tvSuccess;
    this.tvTotal = tvTotal;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemOrderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemOrderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_order, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemOrderBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_food;
      CircleImageView imgFood = rootView.findViewById(id);
      if (imgFood == null) {
        break missingId;
      }

      id = R.id.layout_action;
      LinearLayout layoutAction = rootView.findViewById(id);
      if (layoutAction == null) {
        break missingId;
      }

      id = R.id.layout_review;
      LinearLayout layoutReview = rootView.findViewById(id);
      if (layoutReview == null) {
        break missingId;
      }

      id = R.id.tv_action;
      TextView tvAction = rootView.findViewById(id);
      if (tvAction == null) {
        break missingId;
      }

      id = R.id.tv_foods_name;
      TextView tvFoodsName = rootView.findViewById(id);
      if (tvFoodsName == null) {
        break missingId;
      }

      id = R.id.tv_order_id;
      TextView tvOrderId = rootView.findViewById(id);
      if (tvOrderId == null) {
        break missingId;
      }

      id = R.id.tv_quantity;
      TextView tvQuantity = rootView.findViewById(id);
      if (tvQuantity == null) {
        break missingId;
      }

      id = R.id.tv_rate;
      TextView tvRate = rootView.findViewById(id);
      if (tvRate == null) {
        break missingId;
      }

      id = R.id.tv_review;
      TextView tvReview = rootView.findViewById(id);
      if (tvReview == null) {
        break missingId;
      }

      id = R.id.tv_success;
      TextView tvSuccess = rootView.findViewById(id);
      if (tvSuccess == null) {
        break missingId;
      }

      id = R.id.tv_total;
      TextView tvTotal = rootView.findViewById(id);
      if (tvTotal == null) {
        break missingId;
      }

      return new ItemOrderBinding((LinearLayout) rootView, imgFood, layoutAction, layoutReview,
          tvAction, tvFoodsName, tvOrderId, tvQuantity, tvRate, tvReview, tvSuccess, tvTotal);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}