package com.app.foodordermanagement.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import androidx.biometric.BiometricManager;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class BiometricStorage {
    private static final String SHARED_PREFS_FILENAME = "biometric_prefs";
    private static final String ENCRYPTED_DATA_KEY = "encrypted_credentials";
    private static final String KEY_ALIAS = "biometric_key";
    private final SharedPreferences prefs;
    private final Context context;

    public BiometricStorage(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(SHARED_PREFS_FILENAME, Context.MODE_PRIVATE);
        
        int authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG | 
                           BiometricManager.Authenticators.DEVICE_CREDENTIAL;

        BiometricManager biometricManager = BiometricManager.from(context);
        if (biometricManager.canAuthenticate(authenticators) == BiometricManager.BIOMETRIC_SUCCESS) {
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                if (!keyStore.containsAlias(KEY_ALIAS)) {
                    System.out.println("Tạo key mới cho biometric");
                    generateSecretKey();
                } else {
                    System.out.println("Đã có key biometric");
                }
            } catch (Exception e) {
                System.out.println("Lỗi khởi tạo BiometricStorage: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Chưa thiết lập sinh trắc học hoặc mã PIN");
        }
    }

    private void generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                .setUserAuthenticationRequired(true)
                .setUserAuthenticationParameters(60, KeyProperties.AUTH_BIOMETRIC_STRONG | 
                        KeyProperties.AUTH_DEVICE_CREDENTIAL)
                .build();
        keyGenerator.init(keyGenParameterSpec);
        keyGenerator.generateKey();
        System.out.println("Đã tạo key mới thành công");
    }

    public void saveCredentials(String email, String password) {
        int authenticators = BiometricManager.Authenticators.BIOMETRIC_STRONG | 
                           BiometricManager.Authenticators.DEVICE_CREDENTIAL;

        BiometricManager biometricManager = BiometricManager.from(context);
        if (biometricManager.canAuthenticate(authenticators) != BiometricManager.BIOMETRIC_SUCCESS) {
            System.out.println("Không thể lưu credentials - chưa thiết lập sinh trắc học hoặc mã PIN");
            return;
        }

        String combined = email + ":" + password;
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            
            SecretKey secretKey = (SecretKey) keyStore.getKey(KEY_ALIAS, null);
            if (secretKey == null) {
                System.out.println("Tạo key mới vì chưa có");
                generateSecretKey();
                secretKey = (SecretKey) keyStore.getKey(KEY_ALIAS, null);
            }
            
            if (secretKey == null) {
                System.out.println("Không thể tạo key");
                return;
            }

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(combined.getBytes());
            byte[] iv = cipher.getIV();

            byte[] combinedData = new byte[iv.length + encryptedData.length];
            System.arraycopy(iv, 0, combinedData, 0, iv.length);
            System.arraycopy(encryptedData, 0, combinedData, iv.length, encryptedData.length);

            String encryptedString = Base64.encodeToString(combinedData, Base64.DEFAULT);
            prefs.edit().putString(ENCRYPTED_DATA_KEY, encryptedString).apply();
            System.out.println("Đã lưu credentials thành công cho email: " + email);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu credentials: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String[] getCredentials() {
        try {
            String encryptedData = prefs.getString(ENCRYPTED_DATA_KEY, null);
            if (encryptedData == null) {
                System.out.println("Không tìm thấy credentials đã lưu");
                return null;
            }
            System.out.println("Đã tìm thấy encrypted data");

            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            SecretKey secretKey = (SecretKey) keyStore.getKey(KEY_ALIAS, null);

            if (secretKey == null) {
                System.out.println("Không tìm thấy secret key, tạo key mới");
                generateSecretKey();
                secretKey = (SecretKey) keyStore.getKey(KEY_ALIAS, null);
            }

            if (secretKey == null) {
                System.out.println("Vẫn không thể tạo được key");
                return null;
            }

            byte[] combined = Base64.decode(encryptedData, Base64.DEFAULT);
            byte[] iv = new byte[16];
            byte[] encrypted = new byte[combined.length - 16];

            System.arraycopy(combined, 0, iv, 0, iv.length);
            System.arraycopy(combined, iv.length, encrypted, 0, encrypted.length);

            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretKey, new javax.crypto.spec.IvParameterSpec(iv));

                byte[] decryptedBytes = cipher.doFinal(encrypted);
                String decrypted = new String(decryptedBytes);
                String[] credentials = decrypted.split(":");
                System.out.println("Giải mã credentials thành công: " + credentials[0]);
                return credentials;
            } catch (Exception e) {
                System.out.println("Lỗi khi giải mã: " + e.getMessage());
                // Nếu giải mã thất bại, có thể key đã bị thay đổi, thử tạo key mới và lưu lại
                generateSecretKey();
                return null;
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy credentials: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void clearCredentials() {
        try {
            prefs.edit().remove(ENCRYPTED_DATA_KEY).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}