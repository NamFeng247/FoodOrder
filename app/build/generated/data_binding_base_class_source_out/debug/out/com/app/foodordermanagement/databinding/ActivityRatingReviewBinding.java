// Generated by view binder compiler. Do not edit!
package com.app.foodordermanagement.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.app.foodordermanagement.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRatingReviewBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final EditText edtReview;

  @NonNull
  public final RatingBar ratingbar;

  @NonNull
  public final TextView tvMessageReview;

  @NonNull
  public final TextView tvSendReview;

  private ActivityRatingReviewBinding(@NonNull LinearLayout rootView, @NonNull EditText edtReview,
      @NonNull RatingBar ratingbar, @NonNull TextView tvMessageReview,
      @NonNull TextView tvSendReview) {
    this.rootView = rootView;
    this.edtReview = edtReview;
    this.ratingbar = ratingbar;
    this.tvMessageReview = tvMessageReview;
    this.tvSendReview = tvSendReview;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRatingReviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRatingReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_rating_review, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRatingReviewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.edt_review;
      EditText edtReview = rootView.findViewById(id);
      if (edtReview == null) {
        break missingId;
      }

      id = R.id.ratingbar;
      RatingBar ratingbar = rootView.findViewById(id);
      if (ratingbar == null) {
        break missingId;
      }

      id = R.id.tv_message_review;
      TextView tvMessageReview = rootView.findViewById(id);
      if (tvMessageReview == null) {
        break missingId;
      }

      id = R.id.tv_send_review;
      TextView tvSendReview = rootView.findViewById(id);
      if (tvSendReview == null) {
        break missingId;
      }

      return new ActivityRatingReviewBinding((LinearLayout) rootView, edtReview, ratingbar,
          tvMessageReview, tvSendReview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
