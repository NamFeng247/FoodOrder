package com.app.foodordermanagement.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodordermanagement.MyApplication;
import com.app.foodordermanagement.R;
import com.app.foodordermanagement.adapter.DrinkOrderAdapter;
import com.app.foodordermanagement.model.Order;
import com.app.foodordermanagement.model.RatingReview;
import com.app.foodordermanagement.utils.Constant;
import com.app.foodordermanagement.utils.GlobalFunction;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TrackingOrderActivity extends BaseActivity {

    private RecyclerView rcvDrinks;
    private LinearLayout layoutReceiptOrder;
    private View dividerStep1, dividerStep2;
    private ImageView imgStep1, imgStep2, imgStep3;
    private TextView tvTakeOrder, tvTakeOrderMessage;

    private long orderId;
    private Order mOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_order);

        getDataIntent();
        initToolbar();
        initUi();
        initListener();
        getOrderDetailFromFirebase();
    }

    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;
        orderId = bundle.getLong(Constant.ORDER_ID);
    }

    private void initToolbar() {
        ImageView imgToolbarBack = findViewById(R.id.img_toolbar_back);
        TextView tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        imgToolbarBack.setOnClickListener(view -> finish());
        tvToolbarTitle.setText(getString(R.string.label_tracking_order));
    }

    private void initUi() {
        rcvDrinks = findViewById(R.id.rcv_drinks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvDrinks.setLayoutManager(linearLayoutManager);
        layoutReceiptOrder = findViewById(R.id.layout_receipt_order);
        dividerStep1 = findViewById(R.id.divider_step_1);
        dividerStep2 = findViewById(R.id.divider_step_2);
        imgStep1 = findViewById(R.id.img_step_1);
        imgStep2 = findViewById(R.id.img_step_2);
        imgStep3 = findViewById(R.id.img_step_3);
        tvTakeOrder = findViewById(R.id.tv_take_order);
        tvTakeOrderMessage = findViewById(R.id.tv_take_order_message);
    }

    private void initListener() {
        layoutReceiptOrder.setOnClickListener(view -> {
            if (mOrder == null) return;
            Bundle bundle = new Bundle();
            bundle.putLong(Constant.ORDER_ID, mOrder.getId());
            GlobalFunction.startActivity(TrackingOrderActivity.this,
                    ReceiptOrderActivity.class, bundle);
            finish();
        });

        imgStep1.setOnClickListener(view -> updateStatusOrder(Order.STATUS_NEW));
        imgStep2.setOnClickListener(view -> updateStatusOrder(Order.STATUS_DOING));
        imgStep3.setOnClickListener(view -> updateStatusOrder(Order.STATUS_ARRIVED));
        tvTakeOrder.setOnClickListener(view -> updateStatusOrder(Order.STATUS_COMPLETE));
    }

    private void getOrderDetailFromFirebase() {
        showProgressDialog(true);
        MyApplication.get(this).getOrderDetailDatabaseReference(orderId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        showProgressDialog(false);
                        mOrder = snapshot.getValue(Order.class);
                        if (mOrder == null) return;

                        initData();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        showProgressDialog(false);
                        showToastMessage(getString(R.string.msg_get_date_error));
                    }
                });
    }

    private void initData() {
        DrinkOrderAdapter adapter = new DrinkOrderAdapter(mOrder.getDrinks());
        rcvDrinks.setAdapter(adapter);
//        tvTakeOrder.setBackgroundResource(R.drawable.bg_button_enable_corner_16);
    }

    private void updateStatusOrder(int status) {
        if (mOrder == null) return;
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);

        MyApplication.get(this).getOrderDatabaseReference()
                .child(String.valueOf(mOrder.getId()))
                .updateChildren(map, (error, ref) -> {
                    if (Order.STATUS_COMPLETE == status) {
                        Bundle bundle = new Bundle();
                        RatingReview ratingReview = new RatingReview(RatingReview.TYPE_RATING_REVIEW_ORDER,
                                String.valueOf(mOrder.getId()));
                        bundle.putSerializable(Constant.RATING_REVIEW_OBJECT, ratingReview);
                        GlobalFunction.startActivity(TrackingOrderActivity.this,
                                RatingReviewActivity.class, bundle);
                        finish();
                    }
        });
    }
}
