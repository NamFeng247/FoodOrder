package com.app.foodordermanagement.utils;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class BiometricPromptManager {
    private final AppCompatActivity activity;
    private final BiometricAuthCallback callback;
    private final Executor executor;

    public interface BiometricAuthCallback {
        void onSuccess();
        void onError(String error);
        void onAuthenticationFailed();
        void onBiometricNotSet();
    }

    public BiometricPromptManager(AppCompatActivity activity, BiometricAuthCallback callback) {
        this.activity = activity;
        this.callback = callback;
        this.executor = ContextCompat.getMainExecutor(activity);
    }

    public void showBiometricPrompt(String title, String description) {
        BiometricManager biometricManager = BiometricManager.from(activity);
        int authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG | 
                           BiometricManager.Authenticators.DEVICE_CREDENTIAL;

        switch (biometricManager.canAuthenticate(authenticators)) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                callback.onError("Thiết bị không hỗ trợ sinh trắc học");
                return;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                callback.onError("Tính năng sinh trắc học không khả dụng");
                return;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                callback.onBiometricNotSet();
                return;
        }

        BiometricPrompt.PromptInfo.Builder promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(title)
                .setDescription(description)
                .setAllowedAuthenticators(authenticators);

        BiometricPrompt biometricPrompt = new BiometricPrompt(activity, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        callback.onSuccess();
                    }

                    @Override
                    public void onAuthenticationError(int errorCode, CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        callback.onError(errString.toString());
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        callback.onAuthenticationFailed();
                    }
                });

        biometricPrompt.authenticate(promptInfo.build());
    }

    public void openBiometricSettings() {
        Intent enrollIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
            enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BiometricManager.Authenticators.BIOMETRIC_STRONG | 
                    BiometricManager.Authenticators.DEVICE_CREDENTIAL);
        } else {
            enrollIntent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        }
        activity.startActivity(enrollIntent);
    }
}