// Generated by view binder compiler. Do not edit!
package com.app.foodordermanagement.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.app.foodordermanagement.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFoodDetailBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText edtNotes;

  @NonNull
  public final ImageView imgFood;

  @NonNull
  public final ImageView imgNext;

  @NonNull
  public final ImageView imgStar;

  @NonNull
  public final RelativeLayout layoutBottom;

  @NonNull
  public final LinearLayout layoutCount;

  @NonNull
  public final LinearLayout layoutInfor;

  @NonNull
  public final RelativeLayout layoutRatingAndReview;

  @NonNull
  public final LayoutToolbarBinding toolbar;

  @NonNull
  public final TextView tvAdd;

  @NonNull
  public final TextView tvAddOrder;

  @NonNull
  public final TextView tvCount;

  @NonNull
  public final TextView tvCountReview;

  @NonNull
  public final TextView tvDescription;

  @NonNull
  public final TextView tvName;

  @NonNull
  public final TextView tvPriceSale;

  @NonNull
  public final TextView tvRate;

  @NonNull
  public final TextView tvSub;

  @NonNull
  public final TextView tvTotal;

  @NonNull
  public final TextView viewPoint;

  private ActivityFoodDetailBinding(@NonNull RelativeLayout rootView, @NonNull EditText edtNotes,
      @NonNull ImageView imgFood, @NonNull ImageView imgNext, @NonNull ImageView imgStar,
      @NonNull RelativeLayout layoutBottom, @NonNull LinearLayout layoutCount,
      @NonNull LinearLayout layoutInfor, @NonNull RelativeLayout layoutRatingAndReview,
      @NonNull LayoutToolbarBinding toolbar, @NonNull TextView tvAdd, @NonNull TextView tvAddOrder,
      @NonNull TextView tvCount, @NonNull TextView tvCountReview, @NonNull TextView tvDescription,
      @NonNull TextView tvName, @NonNull TextView tvPriceSale, @NonNull TextView tvRate,
      @NonNull TextView tvSub, @NonNull TextView tvTotal, @NonNull TextView viewPoint) {
    this.rootView = rootView;
    this.edtNotes = edtNotes;
    this.imgFood = imgFood;
    this.imgNext = imgNext;
    this.imgStar = imgStar;
    this.layoutBottom = layoutBottom;
    this.layoutCount = layoutCount;
    this.layoutInfor = layoutInfor;
    this.layoutRatingAndReview = layoutRatingAndReview;
    this.toolbar = toolbar;
    this.tvAdd = tvAdd;
    this.tvAddOrder = tvAddOrder;
    this.tvCount = tvCount;
    this.tvCountReview = tvCountReview;
    this.tvDescription = tvDescription;
    this.tvName = tvName;
    this.tvPriceSale = tvPriceSale;
    this.tvRate = tvRate;
    this.tvSub = tvSub;
    this.tvTotal = tvTotal;
    this.viewPoint = viewPoint;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFoodDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFoodDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_food_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFoodDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edt_notes;
      EditText edtNotes = rootView.findViewById(id);
      if (edtNotes == null) {
        break missingId;
      }

      id = R.id.img_food;
      ImageView imgFood = rootView.findViewById(id);
      if (imgFood == null) {
        break missingId;
      }

      id = R.id.img_next;
      ImageView imgNext = rootView.findViewById(id);
      if (imgNext == null) {
        break missingId;
      }

      id = R.id.img_star;
      ImageView imgStar = rootView.findViewById(id);
      if (imgStar == null) {
        break missingId;
      }

      id = R.id.layout_bottom;
      RelativeLayout layoutBottom = rootView.findViewById(id);
      if (layoutBottom == null) {
        break missingId;
      }

      id = R.id.layout_count;
      LinearLayout layoutCount = rootView.findViewById(id);
      if (layoutCount == null) {
        break missingId;
      }

      id = R.id.layout_infor;
      LinearLayout layoutInfor = rootView.findViewById(id);
      if (layoutInfor == null) {
        break missingId;
      }

      id = R.id.layout_rating_and_review;
      RelativeLayout layoutRatingAndReview = rootView.findViewById(id);
      if (layoutRatingAndReview == null) {
        break missingId;
      }

      id = R.id.toolbar;
      View toolbar = rootView.findViewById(id);
      if (toolbar == null) {
        break missingId;
      }
      LayoutToolbarBinding binding_toolbar = LayoutToolbarBinding.bind(toolbar);

      id = R.id.tv_add;
      TextView tvAdd = rootView.findViewById(id);
      if (tvAdd == null) {
        break missingId;
      }

      id = R.id.tv_add_order;
      TextView tvAddOrder = rootView.findViewById(id);
      if (tvAddOrder == null) {
        break missingId;
      }

      id = R.id.tv_count;
      TextView tvCount = rootView.findViewById(id);
      if (tvCount == null) {
        break missingId;
      }

      id = R.id.tv_count_review;
      TextView tvCountReview = rootView.findViewById(id);
      if (tvCountReview == null) {
        break missingId;
      }

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

      id = R.id.tv_sub;
      TextView tvSub = rootView.findViewById(id);
      if (tvSub == null) {
        break missingId;
      }

      id = R.id.tv_total;
      TextView tvTotal = rootView.findViewById(id);
      if (tvTotal == null) {
        break missingId;
      }

      id = R.id.view_point;
      TextView viewPoint = rootView.findViewById(id);
      if (viewPoint == null) {
        break missingId;
      }

      return new ActivityFoodDetailBinding((RelativeLayout) rootView, edtNotes, imgFood, imgNext,
          imgStar, layoutBottom, layoutCount, layoutInfor, layoutRatingAndReview, binding_toolbar,
          tvAdd, tvAddOrder, tvCount, tvCountReview, tvDescription, tvName, tvPriceSale, tvRate,
          tvSub, tvTotal, viewPoint);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
