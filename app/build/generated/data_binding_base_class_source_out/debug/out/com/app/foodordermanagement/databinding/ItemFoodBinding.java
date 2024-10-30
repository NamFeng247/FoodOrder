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

public final class ItemFoodBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final CircleImageView imgFood;

  @NonNull
  public final LinearLayout layoutImage;

  @NonNull
  public final LinearLayout layoutItem;

  @NonNull
  public final TextView tvDescription;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvPrice;

  @NonNull
  public final TextView tvPriceSale;

  @NonNull
  public final TextView tvRate;

  private ItemFoodBinding(@NonNull LinearLayout rootView, @NonNull CircleImageView imgFood,
      @NonNull LinearLayout layoutImage, @NonNull LinearLayout layoutItem,
      @NonNull TextView tvDescription, @NonNull TextView tvName, @NonNull TextView tvPrice,
      @NonNull TextView tvPriceSale, @NonNull TextView tvRate) {
    this.rootView = rootView;
    this.imgFood = imgFood;
    this.layoutImage = layoutImage;
    this.layoutItem = layoutItem;
    this.tvDescription = tvDescription;
    this.tvName = tvName;
    this.tvPrice = tvPrice;
    this.tvPriceSale = tvPriceSale;
    this.tvRate = tvRate;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemFoodBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemFoodBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_food, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemFoodBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.img_food;
      CircleImageView imgFood = rootView.findViewById(id);
      if (imgFood == null) {
        break missingId;
      }

      id = R.id.layout_image;
      LinearLayout layoutImage = rootView.findViewById(id);
      if (layoutImage == null) {
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

      id = R.id.tv_price;
      TextView tvPrice = rootView.findViewById(id);
      if (tvPrice == null) {
        break missingId;
      }

      id = R.id.tv_price_sale;
      TextView tvPriceSale = rootView.findViewById(id);
      if (tvPriceSale == null) {
        break missingId;
      }

      id = R.id.tv_rate;
      TextView tvRate = rootView.findViewById(id);
      if (tvRate == null) {
        break missingId;
      }

      return new ItemFoodBinding((LinearLayout) rootView, imgFood, layoutImage, layoutItem,
          tvDescription, tvName, tvPrice, tvPriceSale, tvRate);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
