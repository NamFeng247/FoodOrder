package com.app.foodordermanagement.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;

import com.app.foodordermanagement.R;
import com.app.foodordermanagement.constant.Constant;
import com.app.foodordermanagement.model.User;
import com.app.foodordermanagement.prefs.DataStoreManager;
import com.app.foodordermanagement.utils.BiometricPromptManager;
import com.app.foodordermanagement.utils.BiometricStorage;
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
    private BiometricPromptManager biometricPromptManager;
    private BiometricStorage biometricStorage;

    private ActivityLogInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        biometricStorage = new BiometricStorage(this);
        initBiometricPrompt();
        initUi();
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
        if (isFinishing() || isDestroyed()) return;

        showProgressDialog(true);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (isFinishing() || isDestroyed()) return;

                    showProgressDialog(false);
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            try {
                                // Lưu user vào DataStoreManager
                                User userObject = new User(user.getEmail(), password);
                                DataStoreManager.setUser(userObject);

                                // Kiểm tra xem credentials hiện tại có trùng với credentials đã lưu không
                                String[] savedCredentials = biometricStorage.getCredentials();
                                if (savedCredentials != null &&
                                    savedCredentials.length == 2 &&
                                    savedCredentials[0].equals(email) &&
                                    savedCredentials[1].equals(password)) {
                                    // Nếu trùng thì chuyển thẳng vào main
                                    System.out.println("Đã có credentials này, chuyển thẳng vào main");
                                    navigateToMain();
                                    return;
                                }

                                // Nếu không trùng thì hiện dialog hỏi có muốn lưu không
                                new AlertDialog.Builder(this)
                                    .setTitle("Đăng nhập sinh trắc học")
                                    .setMessage("Bạn có muốn thiết lập xác thực sinh trắc học cho lần đăng nhập sau?")
                                    .setPositiveButton("Đồng ý", (dialog, which) -> {
                                        // Kiểm tra xem đã thiết lập sinh trắc học chưa
                                        BiometricManager biometricManager = BiometricManager.from(LoginActivity.this);
                                        int authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG |
                                                           BiometricManager.Authenticators.DEVICE_CREDENTIAL;

                                        if (biometricManager.canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {
                                            // Nếu chưa thiết lập, hiện dialog hỏi có muốn thiết lập không
                                            new AlertDialog.Builder(this)
                                                .setTitle("Thiết lập xác thực")
                                                .setMessage("Bạn cần thiết lập mã PIN, mật khẩu hoặc vân tay trên thiết bị để sử dụng tính năng này. Bạn có muốn đi đến phần thiết lập không?")
                                                .setPositiveButton("Đi đến thiết lập", (dialogSetup, whichSetup) -> {
                                                    // Lưu thông tin tạm thời
                                                    final String tempEmail = email;
                                                    final String tempPassword = password;

                                                    // Mở màn hình cài đặt
                                                    biometricPromptManager.openBiometricSettings();

                                                    // Hiện thông báo hướng dẫn
                                                    showToastMessage("Sau khi thiết lập xong, hãy quay lại app để hoàn tất");

                                                    // Đăng ký callback khi activity resume
                                                    getLifecycle().addObserver(new LifecycleEventObserver() {
                                                        @Override
                                                        public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                                                            if (event == Lifecycle.Event.ON_RESUME) {
                                                                // Kiểm tra lại sau khi quay về
                                                                if (biometricManager.canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_SUCCESS) {
                                                                    // Đã thiết lập xong, tiến hành xác thực và lưu
                                                                    BiometricPromptManager promptManager = new BiometricPromptManager(LoginActivity.this,
                                                                        new BiometricPromptManager.BiometricAuthCallback() {
                                                                            @Override
                                                                            public void onSuccess() {
                                                                                try {
                                                                                    biometricStorage.saveCredentials(tempEmail, tempPassword);
                                                                                    System.out.println("Xác thực thành công, đã lưu xác thực sinh trắc học");
                                                                                    showToastMessage("Đã thiết lập xác thực sinh trắc học thành công");
                                                                                } catch (Exception e) {
                                                                                    System.out.println("Lỗi khi lưu sau xác thực: " + e.getMessage());
                                                                                }
                                                                                navigateToMain();
                                                                            }
                                                                            @Override
                                                                            public void onError(String error) {
                                                                                System.out.println("Xác thực thất bại: " + error);
                                                                                showToastMessage(error);
                                                                                navigateToMain();
                                                                            }
                                                                            @Override
                                                                            public void onAuthenticationFailed() {
                                                                                System.out.println("Xác thực thất bại");
                                                                                showToastMessage("Xác thực sinh trắc học thất bại");
                                                                                navigateToMain();
                                                                            }
                                                                            @Override
                                                                            public void onBiometricNotSet() {
                                                                                System.out.println("Chưa thiết lập sinh trắc học");
                                                                                showToastMessage("Bạn cần thiết lập sinh trắc học trước");
                                                                                navigateToMain();
                                                                            }
                                                                        });
                                                                    promptManager.showBiometricPrompt(
                                                                        "Xác thực sinh trắc học",
                                                                        "Xác thực để hoàn tất thiết lập"
                                                                    );

                                                                    // Xóa observer sau khi xử lý xong
                                                                    getLifecycle().removeObserver(this);
                                                                }
                                                            }
                                                        }
                                                    });
                                                })
                                                .setNegativeButton("Để sau", (dialogSetup, whichSetup) -> {
                                                    navigateToMain();
                                                })
                                                .show();
                                        } else {
                                            // Nếu đã thiết lập rồi thì tiến hành xác thực và lưu như bình thường
                                            BiometricPromptManager promptManager = new BiometricPromptManager(this,
                                                new BiometricPromptManager.BiometricAuthCallback() {
                                                    @Override
                                                    public void onSuccess() {
                                                        try {
                                                            biometricStorage.saveCredentials(email, password);
                                                            System.out.println("Xác thực thành công, đã lưu xác thực sinh trắc học");
                                                            showToastMessage("Đã thiết lập xác thực sinh trắc học thành công");
                                                        } catch (Exception e) {
                                                            System.out.println("Lỗi khi lưu sau xác thực: " + e.getMessage());
                                                        }
                                                        navigateToMain();
                                                    }

                                                    @Override
                                                    public void onError(String error) {
                                                        System.out.println("Xác thực thất bại: " + error);
                                                        showToastMessage(error);
                                                        navigateToMain();
                                                    }

                                                    @Override
                                                    public void onAuthenticationFailed() {
                                                        System.out.println("Xác thực thất bại");
                                                        showToastMessage("Xác thực sinh trắc học thất bại");
                                                        navigateToMain();
                                                    }

                                                    @Override
                                                    public void onBiometricNotSet() {
                                                        System.out.println("Chưa thiết lập sinh trắc học");
                                                        showToastMessage("Bạn cần thiết lập sinh trắc học trước");
                                                        navigateToMain();
                                                    }
                                                });
                                            promptManager.showBiometricPrompt(
                                                "Xác thực sinh trắc học",
                                                "Xác thực để lưu thông tin đăng nhập"
                                            );
                                        }
                                    })
                                    .setNegativeButton("Không", (dialog, which) -> {
                                        navigateToMain();
                                    })
                                    .setCancelable(false)
                                    .show();

                            } catch (Exception e) {
                                e.printStackTrace();
                                showToastMessage("Lỗi khi lưu thông tin đăng nhập: " + e.getMessage());
                                navigateToMain();
                            }
                        }
                    } else {
                        showToastMessage(getString(R.string.msg_login_error));
                    }
                });
    }

    // Tách logic chuyển màn hình thành method riêng
    private void navigateToMain() {
        GlobalFunction.startActivity(LoginActivity.this, MainActivity.class);
        finishAffinity();
    }

    private void initBiometricPrompt() {
        biometricPromptManager = new BiometricPromptManager(this, new BiometricPromptManager.BiometricAuthCallback() {
            @Override
            public void onSuccess() {
                String[] credentials = biometricStorage.getCredentials();
                if (credentials != null && credentials.length == 2) {
                    System.out.println("Xác thực thành công, đang đăng nhập với email: " + credentials[0]);
                    // Hiển thị thông tin lên UI trước khi login
                    edtEmail.setText(credentials[0]);
                    edtPassword.setText(credentials[1]);
                    // Login ngay lập tức
                    loginUserFirebase(credentials[0], credentials[1]);
                } else {
                    showToastMessage("Chưa có thông tin đăng nhập được lưu");
                }
            }

            @Override
            public void onError(String error) {
                showToastMessage(error);
            }

            @Override
            public void onAuthenticationFailed() {
                showToastMessage("Xác thực sinh trắc học thất bại");
            }

            @Override
            public void onBiometricNotSet() {
                // Hiển thị dialog xác nhận
                new AlertDialog.Builder(LoginActivity.this)
                    .setTitle("Thiết lập xác thực")
                    .setMessage("Bạn cần thiết lập mã PIN, mật khẩu hoặc vân tay trên thiết bị để sử dụng tính năng này. Bạn có muốn đi đến phần thiết lập không?")
                    .setPositiveButton("Đi đến thiết lập", (dialog, which) -> {
                        biometricPromptManager.openBiometricSettings();
                    })
                    .setNegativeButton("Để sau", null)
                    .show();
            }
        });
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

    public void onBiometricClick(View view) {
        // Bỏ kiểm tra credentials ở đây
        biometricPromptManager.showBiometricPrompt(
            "Đăng nhập sinh trắc học",
            "Sử dụng sinh trắc học để đăng nhập"
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cleanup resources
        biometricStorage = null;
        biometricPromptManager = null;
    }
}