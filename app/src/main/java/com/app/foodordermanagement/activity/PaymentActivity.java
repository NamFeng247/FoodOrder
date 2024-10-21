package com.app.foodordermanagement.activity;

import android.os.Bundle;
import android.os.Handler;

import com.app.foodordermanagement.MyApplication;
import com.app.foodordermanagement.R;
import com.app.foodordermanagement.database.FoodDatabase;
import com.app.foodordermanagement.event.DisplayCartEvent;
import com.app.foodordermanagement.event.OrderSuccessEvent;
import com.app.foodordermanagement.model.Order;
import com.app.foodordermanagement.utils.Constant;
import com.app.foodordermanagement.utils.GlobalFunction;

import org.greenrobot.eventbus.EventBus;

public class PaymentActivity extends BaseActivity {

    private Order mOrderBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        getDataIntent();

        Handler handler = new Handler();
        handler.postDelayed(this::createOrderFirebase, 1);
    }

    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        mOrderBooking = (Order) bundle.get(Constant.ORDER_OBJECT);
    }

    private void createOrderFirebase() {
        MyApplication.get(this).getOrderDatabaseReference()
                .child(String.valueOf(mOrderBooking.getId()))
                .setValue(mOrderBooking, (error1, ref1) -> {

                    FoodDatabase.getInstance(this).foodDAO().deleteAllFood();
                    EventBus.getDefault().post(new DisplayCartEvent());
                    EventBus.getDefault().post(new OrderSuccessEvent());

                    Bundle bundle = new Bundle();
                    bundle.putLong(Constant.ORDER_ID, mOrderBooking.getId());
                    GlobalFunction.startActivity(PaymentActivity.this,
                            ReceiptOrderActivity.class, bundle);

                    finish();
                });
    }
}
