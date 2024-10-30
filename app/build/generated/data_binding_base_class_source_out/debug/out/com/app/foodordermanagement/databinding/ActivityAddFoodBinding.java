// Generated by view binder compiler. Do not edit!
package com.app.foodordermanagement.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.app.foodordermanagement.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddFoodBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnAddOrEdit;

  @NonNull
  public final CheckBox chbFeatured;

  @NonNull
  public final AutoCompleteTextView edtCategory;

  @NonNull
  public final EditText edtDescription;

  @NonNull
  public final EditText edtDiscount;

  @NonNull
  public final EditText edtImage;

  @NonNull
  public final EditText edtImageBanner;

  @NonNull
  public final EditText edtName;

  @NonNull
  public final EditText edtPrice;

  @NonNull
  public final LayoutToolbarBinding toolbar;

  @NonNull
  public final TextView tvCurrency;

  @NonNull
  public final TextView tvPercent;

  private ActivityAddFoodBinding(@NonNull LinearLayout rootView, @NonNull Button btnAddOrEdit,
      @NonNull CheckBox chbFeatured, @NonNull AutoCompleteTextView edtCategory,
      @NonNull EditText edtDescription, @NonNull EditText edtDiscount, @NonNull EditText edtImage,
      @NonNull EditText edtImageBanner, @NonNull EditText edtName, @NonNull EditText edtPrice,
      @NonNull LayoutToolbarBinding toolbar, @NonNull TextView tvCurrency,
      @NonNull TextView tvPercent) {
    this.rootView = rootView;
    this.btnAddOrEdit = btnAddOrEdit;
    this.chbFeatured = chbFeatured;
    this.edtCategory = edtCategory;
    this.edtDescription = edtDescription;
    this.edtDiscount = edtDiscount;
    this.edtImage = edtImage;
    this.edtImageBanner = edtImageBanner;
    this.edtName = edtName;
    this.edtPrice = edtPrice;
    this.toolbar = toolbar;
    this.tvCurrency = tvCurrency;
    this.tvPercent = tvPercent;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddFoodBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddFoodBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_food, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddFoodBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_add_or_edit;
      Button btnAddOrEdit = rootView.findViewById(id);
      if (btnAddOrEdit == null) {
        break missingId;
      }

      id = R.id.chb_featured;
      CheckBox chbFeatured = rootView.findViewById(id);
      if (chbFeatured == null) {
        break missingId;
      }

      id = R.id.edt_category;
      AutoCompleteTextView edtCategory = rootView.findViewById(id);
      if (edtCategory == null) {
        break missingId;
      }

      id = R.id.edt_description;
      EditText edtDescription = rootView.findViewById(id);
      if (edtDescription == null) {
        break missingId;
      }

      id = R.id.edt_discount;
      EditText edtDiscount = rootView.findViewById(id);
      if (edtDiscount == null) {
        break missingId;
      }

      id = R.id.edt_image;
      EditText edtImage = rootView.findViewById(id);
      if (edtImage == null) {
        break missingId;
      }

      id = R.id.edt_image_Banner;
      EditText edtImageBanner = rootView.findViewById(id);
      if (edtImageBanner == null) {
        break missingId;
      }

      id = R.id.edt_name;
      EditText edtName = rootView.findViewById(id);
      if (edtName == null) {
        break missingId;
      }

      id = R.id.edt_price;
      EditText edtPrice = rootView.findViewById(id);
      if (edtPrice == null) {
        break missingId;
      }

      id = R.id.toolbar;
      View toolbar = rootView.findViewById(id);
      if (toolbar == null) {
        break missingId;
      }
      LayoutToolbarBinding binding_toolbar = LayoutToolbarBinding.bind(toolbar);

      id = R.id.tv_currency;
      TextView tvCurrency = rootView.findViewById(id);
      if (tvCurrency == null) {
        break missingId;
      }

      id = R.id.tv_percent;
      TextView tvPercent = rootView.findViewById(id);
      if (tvPercent == null) {
        break missingId;
      }

      return new ActivityAddFoodBinding((LinearLayout) rootView, btnAddOrEdit, chbFeatured,
          edtCategory, edtDescription, edtDiscount, edtImage, edtImageBanner, edtName, edtPrice,
          binding_toolbar, tvCurrency, tvPercent);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
