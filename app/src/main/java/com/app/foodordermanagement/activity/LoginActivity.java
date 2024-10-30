package com.app.foodordermanagement.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.foodordermanagement.R;
import com.app.foodordermanagement.constant.Constant;
import com.app.foodordermanagement.model.User;
import com.app.foodordermanagement.prefs.DataStoreManager;
import com.app.foodordermanagement.utils.GlobalFunction;
import com.app.foodordermanagement.utils.StringUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.app.foodordermanagement.databinding.ActivityLogInBinding;
public class LoginActivity extends BaseActivity {

//    private EditText edtEmail;
//    private EditText edtPassword;
//    private Button btnLogin;
//    private RadioButton btnAdmin;
//    private LinearLayout layoutRegister;
//    private TextView tvForgotPassword;
    private boolean isEnableButtonLogin;

    private ActivityLogInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        etContentView(R.layout.activity_log_in);
        binding.rdbUser.setChecked(true);
        initListener();
    }

    private void initListener() {
        binding.edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtil.isEmpty(s.toString())) {
                    binding.edtEmail.setBackgroundResource(R.drawable.bg_white_corner_16_border_main);
                } else {
                    binding.edtEmail.setBackgroundResource(R.drawable.bg_white_corner_16_border_gray);
                }

                String strPassword = binding.edtPassword.getText().toString().trim();
                if (!StringUtil.isEmpty(s.toString()) && !StringUtil.isEmpty(strPassword)) {
                    isEnableButtonLogin = true;
                    binding.btnLogin.setBackgroundResource(R.drawable.bg_button_enable_corner_16);
                } else {
                    isEnableButtonLogin = false;
                    binding.btnLogin.setBackgroundResource(R.drawable.bg_button_disable_corner_16);
                }
            }
        });

        binding.edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtil.isEmpty(s.toString())) {
                    binding.edtPassword.setBackgroundResource(R.drawable.bg_white_corner_16_border_main);
                } else {
                    binding.edtPassword.setBackgroundResource(R.drawable.bg_white_corner_16_border_gray);
                }

                String strEmail = binding.edtEmail.getText().toString().trim();
                if (!StringUtil.isEmpty(s.toString()) && !StringUtil.isEmpty(strEmail)) {
                    isEnableButtonLogin = true;
                    binding.btnLogin.setBackgroundResource(R.drawable.bg_button_enable_corner_16);
                } else {
                    isEnableButtonLogin = false;
                    binding.btnLogin.setBackgroundResource(R.drawable.bg_button_disable_corner_16);
                }
            }
        });

        binding.layoutRegister.setOnClickListener(
                v -> GlobalFunction.startActivity(this, RegisterActivity.class));

        binding.btnLogin.setOnClickListener(v -> onClickValidateLogin());
//        tvForgotPassword.setOnClickListener(
//                v -> GlobalFunction.startActivity(this, ForgotPasswordActivity.class));
    }

    private void onClickValidateLogin() {
        if (!isEnableButtonLogin) return;

        String strEmail = binding.edtEmail.getText().toString().trim();
        String strPassword = binding.edtPassword.getText().toString().trim();
        if (StringUtil.isEmpty(strEmail)) {
            showToastMessage(getString(R.string.msg_email_require));
        } else if (StringUtil.isEmpty(strPassword)) {
            showToastMessage(getString(R.string.msg_password_require));
        } else if (!StringUtil.isValidEmail(strEmail)) {
            showToastMessage(getString(R.string.msg_email_invalid));
        } else {
            loginUserFirebase(strEmail, strPassword);
            if (binding.rdbAdmin.isChecked()) {
                if (!strEmail.contains(Constant.ADMIN_EMAIL_FORMAT)) {
                    Toast.makeText(LoginActivity.this, getString(R.string.msg_email_invalid_admin), Toast.LENGTH_SHORT).show();
                } else {
                    loginUserFirebase(strEmail, strPassword);
                }
                return;
            } else {
                if (strEmail.contains(Constant.ADMIN_EMAIL_FORMAT)) {
                    Toast.makeText(LoginActivity.this, getString(R.string.msg_email_invalid_user), Toast.LENGTH_SHORT).show();
                } else {
                    loginUserFirebase(strEmail, strPassword);
                }
            }
        }
    }

    private void loginUserFirebase(String email, String password) {
        showProgressDialog(true);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    showProgressDialog(false);
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            User userObject = new User(user.getEmail(), password);
                            if (user.getEmail() != null && user.getEmail().contains(Constant.ADMIN_EMAIL_FORMAT)) {
                                userObject.setAdmin(true);
                            }
                            DataStoreManager.setUser(userObject);
                            GlobalFunction.gotoMainActivity(this);
                            finishAffinity();
                        }
                    } else {
                        showToastMessage(getString(R.string.msg_login_error));
                    }
                });
    }
}