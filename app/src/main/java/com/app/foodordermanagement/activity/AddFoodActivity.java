package com.app.foodordermanagement.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.app.foodordermanagement.MyApplication;
import com.app.foodordermanagement.R;
import com.app.foodordermanagement.constant.Constant;
import com.app.foodordermanagement.databinding.ActivityAddFoodBinding;
import com.app.foodordermanagement.utils.StringUtil;
import com.app.foodordermanagement.model.Food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddFoodActivity extends BaseActivity {

    private ActivityAddFoodBinding mActivityAddFoodBinding;
    private boolean isUpdate;
    private Food mFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddFoodBinding = ActivityAddFoodBinding.inflate(getLayoutInflater());
        setContentView(mActivityAddFoodBinding.getRoot());

//        getDataIntent();
        initToolbar();
        initView();

        mActivityAddFoodBinding.btnAddOrEdit.setOnClickListener(v -> addOrEditFood());
    }

    private void getDataIntent() {
        Bundle bundleReceived = getIntent().getExtras();
        if (bundleReceived != null) {
            isUpdate = true;
            mFood = (Food) bundleReceived.get(String.valueOf(mFood.getId()));
        }
    }

    private void initToolbar() {
        mActivityAddFoodBinding.toolbar.imgToolbarBack.setVisibility(View.VISIBLE);

        mActivityAddFoodBinding.toolbar.imgToolbarBack.setOnClickListener(v -> onBackPressed());
    }

    private void initView() {
        if (isUpdate) {
            mActivityAddFoodBinding.toolbar.tvToolbarTitle.setText(getString(R.string.edit_food));
            mActivityAddFoodBinding.btnAddOrEdit.setText(getString(R.string.action_edit));

            mActivityAddFoodBinding.edtName.setText(mFood.getName());
            mActivityAddFoodBinding.edtDescription.setText(mFood.getDescription());
            mActivityAddFoodBinding.edtPrice.setText(String.valueOf(mFood.getPrice()));
            mActivityAddFoodBinding.edtDiscount.setText(String.valueOf(mFood.getSale()));
            mActivityAddFoodBinding.edtImage.setText(mFood.getImage());
            mActivityAddFoodBinding.edtImageBanner.setText(mFood.getBanner());
//            mActivityAddFoodBinding.edtOtherImage.setText(getTextOtherImages());
        } else {
            mActivityAddFoodBinding.toolbar.tvToolbarTitle.setText(getString(R.string.add_food));
            mActivityAddFoodBinding.btnAddOrEdit.setText(getString(R.string.action_add));
        }
    }

    private String getSelectCategory() {
        String result = "";
        AutoCompleteTextView autoCompleteCategory =  mActivityAddFoodBinding.edtCategory;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                Arrays.asList("cơm", "Món Nước", "Đồ Uống", "Đồ ăn vặt"));
        autoCompleteCategory.setAdapter(adapter);

        return result;
    }

    private void addOrEditFood() {
        String strName = mActivityAddFoodBinding.edtName.getText().toString().trim();
        String strDescription = mActivityAddFoodBinding.edtDescription.getText().toString().trim();
        String strPrice = mActivityAddFoodBinding.edtPrice.getText().toString().trim();
        String strDiscount = mActivityAddFoodBinding.edtDiscount.getText().toString().trim();
        String strCategoryId = mActivityAddFoodBinding.edtCategory.getText().toString().trim();
        String strImage = mActivityAddFoodBinding.edtImage.getText().toString().trim();
        String strImageBanner = mActivityAddFoodBinding.edtImageBanner.getText().toString().trim();
        boolean isFeatured = mActivityAddFoodBinding.chbFeatured.isChecked();
//        List<Image> listImages = new ArrayList<>();
//        if (!StringUtil.isEmpty(strOtherImages)) {
//            String[] temp = strOtherImages.split(";");
//            for (String strUrl : temp) {
//                Image image = new Image(strUrl);
//                listImages.add(image);
//            }
//        }

        if (StringUtil.isEmpty(strName)) {
            Toast.makeText(this, getString(R.string.msg_name_food_require), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtil.isEmpty(strDescription)) {
            Toast.makeText(this, getString(R.string.msg_description_food_require), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtil.isEmpty(strPrice)) {
            Toast.makeText(this, getString(R.string.msg_price_food_require), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtil.isEmpty(strImage)) {
            Toast.makeText(this, getString(R.string.msg_image_food_require), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtil.isEmpty(strImageBanner)) {
            Toast.makeText(this, getString(R.string.msg_image_Banner_food_require), Toast.LENGTH_SHORT).show();
            return;
        }

        // Update food
        if (isUpdate) {
            showProgressDialog(true);
            Map<String, Object> map = new HashMap<>();
            map.put("name", strName);
            map.put("description", strDescription);
            map.put("price", Integer.parseInt(strPrice));
            map.put("image", strImage );
            map.put("Banner", strImageBanner);
            map.put("sale", Integer.parseInt(strDiscount));
            map.put("CategoryId", strCategoryId);
            map.put("featured" , isFeatured);

            MyApplication.get(this).getFoodDatabaseReference()
                    .child(String.valueOf(mFood.getId())).updateChildren(map, (error, ref) -> {
                showProgressDialog(false);
                Toast.makeText(AddFoodActivity.this,
                        getString(R.string.msg_edit_food_success), Toast.LENGTH_SHORT).show();
            });
            return;
        }

        // Add food
        showProgressDialog(true);
        int foodId = Long.valueOf(System.currentTimeMillis()).hashCode();
        Food food = new Food(foodId, strName, strDescription, Integer.parseInt(strPrice),
                strImage, strImageBanner,Integer.parseInt(strCategoryId),Integer.parseInt(strDiscount), isFeatured);

        MyApplication.get(this).getFoodDatabaseReference()
                .child(String.valueOf(foodId)).setValue(food, (error, ref) -> {
            showProgressDialog(false);
            mActivityAddFoodBinding.edtName.setText("");
            mActivityAddFoodBinding.edtDescription.setText("");
            mActivityAddFoodBinding.edtCategory.setText("");
            mActivityAddFoodBinding.edtPrice.setText("");
            mActivityAddFoodBinding.edtDiscount.setText("");
            mActivityAddFoodBinding.edtImage.setText("");
            mActivityAddFoodBinding.edtImageBanner.setText("");
            mActivityAddFoodBinding.chbFeatured.setChecked(false);
            Toast.makeText(this, getString(R.string.msg_add_food_success), Toast.LENGTH_SHORT).show();
        });
    }
}