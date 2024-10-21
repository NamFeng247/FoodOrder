package com.app.foodordermanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodordermanagement.Api.CreateOrder;
import com.app.foodordermanagement.R;
import com.app.foodordermanagement.adapter.CartAdapter;
import com.app.foodordermanagement.database.FoodDatabase;
import com.app.foodordermanagement.event.AddressSelectedEvent;
import com.app.foodordermanagement.event.DisplayCartEvent;
import com.app.foodordermanagement.event.OrderSuccessEvent;
import com.app.foodordermanagement.event.PaymentMethodSelectedEvent;
import com.app.foodordermanagement.model.Address;
import com.app.foodordermanagement.model.Food;
import com.app.foodordermanagement.model.FoodOrder;
import com.app.foodordermanagement.model.Order;
import com.app.foodordermanagement.model.PaymentMethod;
import com.app.foodordermanagement.prefs.DataStoreManager;
import com.app.foodordermanagement.utils.Constant;
import com.app.foodordermanagement.utils.GlobalFunction;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class CartActivity extends BaseActivity {

    private RecyclerView rcvCart;
    private LinearLayout layoutAddOrder;
    private RelativeLayout layoutPaymentMethod;
    private TextView tvPaymentMethod;

    private RelativeLayout layoutAddress;
    private TextView tvAddress;
    private RelativeLayout layoutVoucher;
//    private TextView tvVoucher;
//    private TextView tvNameVoucher;
    private TextView tvPriceDrink;
    private TextView tvCountItem;
    private TextView tvAmount;
    private TextView tvPriceVoucher;
    private TextView tvCheckout;

    private List<Food> listFoodCart;
    private CartAdapter cartAdapter;
    private int priceDrink;
    private int mAmount;
    private PaymentMethod paymentMethodSelected;
    private Address addressSelected;
//    private Voucher voucherSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        initToolbar();
        initUi();
        initListener();
        initData();
    }

    private void initToolbar() {
        ImageView imgToolbarBack = findViewById(R.id.img_toolbar_back);
        TextView tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        imgToolbarBack.setOnClickListener(view -> finish());
        tvToolbarTitle.setText(getString(R.string.label_cart));
    }

    private void initUi() {
        rcvCart = findViewById(R.id.rcv_cart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvCart.setLayoutManager(linearLayoutManager);
        layoutAddOrder = findViewById(R.id.layout_add_order);
        layoutPaymentMethod = findViewById(R.id.layout_payment_method);
        tvPaymentMethod = findViewById(R.id.tv_payment_method);
        layoutAddress = findViewById(R.id.layout_address);
        tvAddress = findViewById(R.id.tv_address);
//        layoutVoucher = findViewById(R.id.layout_voucher);
//        tvVoucher = findViewById(R.id.tv_voucher);
//        tvNameVoucher = findViewById(R.id.tv_name_voucher);
        tvCountItem = findViewById(R.id.tv_count_item);
        tvPriceDrink = findViewById(R.id.tv_price_drink);
        tvAmount = findViewById(R.id.tv_amount);
//        tvPriceVoucher = findViewById(R.id.tv_price_voucher);
        tvCheckout = findViewById(R.id.tv_checkout);
    }

    private void initListener() {
        layoutAddOrder.setOnClickListener(v -> finish());
        layoutPaymentMethod.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            if (paymentMethodSelected != null) {
                bundle.putInt(Constant.PAYMENT_METHOD_ID, paymentMethodSelected.getId());
            }
            GlobalFunction.startActivity(CartActivity.this, PaymentMethodActivity.class, bundle);
        });

        layoutAddress.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            if (addressSelected != null) {
                bundle.putLong(Constant.ADDRESS_ID, addressSelected.getId());
            }
            GlobalFunction.startActivity(CartActivity.this, AddressActivity.class, bundle);
        });

//        layoutVoucher.setOnClickListener(view -> {
//            Bundle bundle = new Bundle();
//            bundle.putInt(Constant.AMOUNT_VALUE, priceDrink);
//            if (voucherSelected != null) {
//                bundle.putInt(Constant.VOUCHER_ID, voucherSelected.getId());
//            }
//            GlobalFunction.startActivity(CartActivity.this, VoucherActivity.class, bundle);
//        });

        tvCheckout.setOnClickListener(view -> {
            if (listFoodCart == null || listFoodCart.isEmpty()) return;
            if (paymentMethodSelected == null) {
                showToastMessage(getString(R.string.label_choose_payment_method));
                return;
            }
            if (addressSelected == null) {
                showToastMessage(getString(R.string.label_choose_address));
                return;
            }

            // Create an order using your existing logic
            Order orderBooking = new Order();
            orderBooking.setId(System.currentTimeMillis());
            orderBooking.setUserEmail(DataStoreManager.getUser().getEmail());
            orderBooking.setDateTime(String.valueOf(System.currentTimeMillis()));
            List<FoodOrder> drinks = new ArrayList<>();
            for (Food food : listFoodCart) {
                drinks.add(new FoodOrder(food.getName(), food.getOption(), food.getCount(),
                        food.getPriceOneDrink(), food.getImage()));
            }
            orderBooking.setFoods(drinks);
            orderBooking.setPrice(priceDrink);
//            if (voucherSelected != null) {
//                orderBooking.setVoucher(voucherSelected.getPriceDiscount(priceDrink));
//            }
            orderBooking.setTotal(mAmount);
            orderBooking.setPaymentMethod(paymentMethodSelected.getName());
            orderBooking.setAddress(addressSelected);
            orderBooking.setStatus(Order.STATUS_NEW);


            StrictMode.ThreadPolicy policy = new
            StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            ZaloPaySDK.init(2553, Environment.SANDBOX);

            Intent intent = getIntent();

            // ZaloPay payment integration
            CreateOrder orderApi = new CreateOrder();
            try {
                JSONObject data = orderApi.createOrder(String.valueOf(mAmount * 1000));
                String code = data.getString("return_code");
                if (code.equals("1")) {
                    String token = data.getString("zp_trans_token");
                    ZaloPaySDK.getInstance().payOrder(CartActivity.this, token, "demozpdk://app", new PayOrderListener() {
                        @Override
                        public void onPaymentSucceeded(String transactionId, String transToken, String appTransID) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(Constant.ORDER_OBJECT, orderBooking);
                            GlobalFunction.startActivity(CartActivity.this, PaymentActivity.class, bundle);
                        }

                        @Override
                        public void onPaymentCanceled(String zpTransToken, String appTransID) {
                            showToastMessage("Hủy thanh toán");
                            // Handle cancel logic here
                        }

                        @Override
                        public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                            showToastMessage("Lỗi thanh toán");
                            // Handle error logic here
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private void initData() {
        listFoodCart = new ArrayList<>();
        listFoodCart = FoodDatabase.getInstance(this).foodDAO().getListFoodCart();
        if (listFoodCart == null || listFoodCart.isEmpty()) {
            return;
        }
        cartAdapter = new CartAdapter(listFoodCart, new CartAdapter.IClickCartListener() {
            @Override
            public void onClickDeleteItem(Food food, int position) {
                FoodDatabase.getInstance(CartActivity.this).foodDAO().deleteFood(food);
                listFoodCart.remove(position);
                cartAdapter.notifyItemRemoved(position);

                displayCountItemCart();
                calculateTotalPrice();
                EventBus.getDefault().post(new DisplayCartEvent());
            }

            @Override
            public void onClickUpdateItem(Food food, int position) {
                FoodDatabase.getInstance(CartActivity.this).foodDAO().updateFood(food);
                cartAdapter.notifyItemChanged(position);

                calculateTotalPrice();
                EventBus.getDefault().post(new DisplayCartEvent());
            }

            @Override
            public void onClickEditItem(Food food) {
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.FOOD_ID, food.getId());
                bundle.putSerializable(Constant.FOOD_OBJECT, food);
                GlobalFunction.startActivity(CartActivity.this, FoodDetailActivity.class, bundle);
            }
        });
        rcvCart.setAdapter(cartAdapter);
        calculateTotalPrice();
        displayCountItemCart();
    }

    private void displayCountItemCart() {
        String strCountItem = "(" + listFoodCart.size() + " " + getString(R.string.label_item) + ")";
        tvCountItem.setText(strCountItem);
    }

    private void calculateTotalPrice() {
        if (listFoodCart == null || listFoodCart.isEmpty()) {
            String strZero = 0 + Constant.CURRENCY;
            priceDrink = 0;
            tvPriceDrink.setText(strZero);

            mAmount = 0;
            tvAmount.setText(strZero);
            return;
        }

        int totalPrice = 0;
        for (Food food : listFoodCart) {
            totalPrice = totalPrice + food.getTotalPrice();
        }

        priceDrink = totalPrice;
        String strPriceDrink = priceDrink + Constant.CURRENCY;
        tvPriceDrink.setText(strPriceDrink);

        mAmount = totalPrice;

        String strAmount = mAmount + Constant.CURRENCY;
        tvAmount.setText(strAmount);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPaymentMethodSelectedEvent(PaymentMethodSelectedEvent event) {
        if (event.getPaymentMethod() != null) {
            paymentMethodSelected = event.getPaymentMethod();
            tvPaymentMethod.setText(paymentMethodSelected.getName());
        } else {
            tvPaymentMethod.setText(getString(R.string.label_no_payment_method));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddressSelectedEvent(AddressSelectedEvent event) {
        if (event.getAddress() != null) {
            addressSelected = event.getAddress();
            tvAddress.setText(addressSelected.getAddress());
        } else {
            tvAddress.setText(getString(R.string.label_no_address));
        }
    }



    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOrderSuccessEvent(OrderSuccessEvent event) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


}
