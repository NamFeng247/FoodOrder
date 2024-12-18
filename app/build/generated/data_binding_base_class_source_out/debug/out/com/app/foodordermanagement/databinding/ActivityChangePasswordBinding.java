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

public final class ActivityChangePasswordBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnChangePassword;

  @NonNull
  public final EditText edtConfirmPassword;

  @NonNull
  public final EditText edtNewPassword;

  @NonNull
  public final EditText edtOldPassword;

  private ActivityChangePasswordBinding(@NonNull RelativeLayout rootView,
      @NonNull Button btnChangePassword, @NonNull EditText edtConfirmPassword,
      @NonNull EditText edtNewPassword, @NonNull EditText edtOldPassword) {
    this.rootView = rootView;
    this.btnChangePassword = btnChangePassword;
    this.edtConfirmPassword = edtConfirmPassword;
    this.edtNewPassword = edtNewPassword;
    this.edtOldPassword = edtOldPassword;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChangePasswordBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChangePasswordBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_change_password, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChangePasswordBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_change_password;
      Button btnChangePassword = rootView.findViewById(id);
      if (btnChangePassword == null) {
        break missingId;
      }

      id = R.id.edt_confirm_password;
      EditText edtConfirmPassword = rootView.findViewById(id);
      if (edtConfirmPassword == null) {
        break missingId;
      }

      id = R.id.edt_new_password;
      EditText edtNewPassword = rootView.findViewById(id);
      if (edtNewPassword == null) {
        break missingId;
      }

      id = R.id.edt_old_password;
      EditText edtOldPassword = rootView.findViewById(id);
      if (edtOldPassword == null) {
        break missingId;
      }

      return new ActivityChangePasswordBinding((RelativeLayout) rootView, btnChangePassword,
          edtConfirmPassword, edtNewPassword, edtOldPassword);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
