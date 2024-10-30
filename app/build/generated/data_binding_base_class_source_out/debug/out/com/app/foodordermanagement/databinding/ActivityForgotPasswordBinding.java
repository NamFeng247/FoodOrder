// Generated by view binder compiler. Do not edit!
package com.app.foodordermanagement.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.app.foodordermanagement.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityForgotPasswordBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnResetPassword;

  @NonNull
  public final EditText edtEmail;

  private ActivityForgotPasswordBinding(@NonNull RelativeLayout rootView,
      @NonNull Button btnResetPassword, @NonNull EditText edtEmail) {
    this.rootView = rootView;
    this.btnResetPassword = btnResetPassword;
    this.edtEmail = edtEmail;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityForgotPasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityForgotPasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_forgot_password, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityForgotPasswordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_reset_password;
      Button btnResetPassword = rootView.findViewById(id);
      if (btnResetPassword == null) {
        break missingId;
      }

      id = R.id.edt_email;
      EditText edtEmail = rootView.findViewById(id);
      if (edtEmail == null) {
        break missingId;
      }

      return new ActivityForgotPasswordBinding((RelativeLayout) rootView, btnResetPassword,
          edtEmail);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
