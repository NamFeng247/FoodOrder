// Generated by view binder compiler. Do not edit!
package com.app.foodordermanagement.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnRegister;

  @NonNull
  public final TextView dangky;

  @NonNull
  public final EditText edtEmail;

  @NonNull
  public final EditText edtPassword;

  @NonNull
  public final LinearLayout layoutLogin;

  @NonNull
  public final LinearLayout orLinearLayout;

  @NonNull
  public final TextView orText;

  @NonNull
  public final EditText reEdtPassword;

  private ActivityRegisterBinding(@NonNull RelativeLayout rootView, @NonNull Button btnRegister,
      @NonNull TextView dangky, @NonNull EditText edtEmail, @NonNull EditText edtPassword,
      @NonNull LinearLayout layoutLogin, @NonNull LinearLayout orLinearLayout,
      @NonNull TextView orText, @NonNull EditText reEdtPassword) {
    this.rootView = rootView;
    this.btnRegister = btnRegister;
    this.dangky = dangky;
    this.edtEmail = edtEmail;
    this.edtPassword = edtPassword;
    this.layoutLogin = layoutLogin;
    this.orLinearLayout = orLinearLayout;
    this.orText = orText;
    this.reEdtPassword = reEdtPassword;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_register;
      Button btnRegister = rootView.findViewById(id);
      if (btnRegister == null) {
        break missingId;
      }

      id = R.id.dangky;
      TextView dangky = rootView.findViewById(id);
      if (dangky == null) {
        break missingId;
      }

      id = R.id.edt_email;
      EditText edtEmail = rootView.findViewById(id);
      if (edtEmail == null) {
        break missingId;
      }

      id = R.id.edt_password;
      EditText edtPassword = rootView.findViewById(id);
      if (edtPassword == null) {
        break missingId;
      }

      id = R.id.layout_login;
      LinearLayout layoutLogin = rootView.findViewById(id);
      if (layoutLogin == null) {
        break missingId;
      }

      id = R.id.or_linear_layout;
      LinearLayout orLinearLayout = rootView.findViewById(id);
      if (orLinearLayout == null) {
        break missingId;
      }

      id = R.id.or_text;
      TextView orText = rootView.findViewById(id);
      if (orText == null) {
        break missingId;
      }

      id = R.id.re_edt_password;
      EditText reEdtPassword = rootView.findViewById(id);
      if (reEdtPassword == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((RelativeLayout) rootView, btnRegister, dangky, edtEmail,
          edtPassword, layoutLogin, orLinearLayout, orText, reEdtPassword);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}