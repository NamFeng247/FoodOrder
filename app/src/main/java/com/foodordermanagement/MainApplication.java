package com.foodordermanagement;

import android.app.Application;
import android.content.Context;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainApplication extends Application {

    private static final String FIREBASE_URL = "https://foodordermanagement-7dd4b-default-rtdb.firebaseio.com";
    private FirebaseDatabase database;

    public static MainApplication get(Context context) {
        return (MainApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance(FIREBASE_URL);
//        DataStoreManager.init(getApplicationContext());
    }

    public DatabaseReference getVoucherDatabaseReference() {
        return database.getReference("voucher");
    }

    public DatabaseReference getAddressDatabaseReference() {
        return database.getReference("address");
    }

    public DatabaseReference getPaymentMethodDatabaseReference() {
        return database.getReference("payment");
    }

    public DatabaseReference getCategoryDatabaseReference() {
        return database.getReference("category");
    }

    public DatabaseReference getDrinkDatabaseReference() {
        return database.getReference("food");
    }

    public DatabaseReference getDrinkDetailDatabaseReference(int foodId) {
        return database.getReference("food/" + foodId);
    }

    public DatabaseReference getToppingDatabaseReference() {
        return database.getReference("topping");
    }

    public DatabaseReference getFeedbackDatabaseReference() {
        return database.getReference("/feedback");
    }

    public DatabaseReference getBookingDatabaseReference() {
        return database.getReference("/booking");
    }

    public DatabaseReference getRatingDrinkDatabaseReference(String foodId) {
        return database.getReference("/food/" + foodId + "/rating");
    }

    public DatabaseReference getOrderDetailDatabaseReference(long orderId) {
        return database.getReference("order/" + orderId);
    }
}