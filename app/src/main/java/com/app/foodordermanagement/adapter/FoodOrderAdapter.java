package com.app.foodordermanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodordermanagement.R;
import com.app.foodordermanagement.model.FoodOrder;
import com.app.foodordermanagement.utils.Constant;
import com.app.foodordermanagement.utils.GlideUtils;

import java.util.List;

public class FoodOrderAdapter extends RecyclerView.Adapter<FoodOrderAdapter.DrinkOrderViewHolder> {

    private final List<FoodOrder> listFoodOrder;

    public FoodOrderAdapter(List<FoodOrder> list) {
        this.listFoodOrder = list;
    }

    @NonNull
    @Override
    public DrinkOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_food_order, parent, false);
        return new DrinkOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkOrderViewHolder holder, int position) {
        FoodOrder foodOrder = listFoodOrder.get(position);
        if (foodOrder == null) return;

        GlideUtils.loadUrl(foodOrder.getImage(), holder.imgDrink);
        holder.tvName.setText(foodOrder.getName());
        String strPrice = foodOrder.getPrice() + Constant.CURRENCY;
        holder.tvPrice.setText(strPrice);
        holder.tvOption.setText(foodOrder.getOption());
        String strCount = "x" + foodOrder.getCount();
        holder.tvCount.setText(strCount);
    }

    @Override
    public int getItemCount() {
        if (listFoodOrder != null) {
            return listFoodOrder.size();
        }
        return 0;
    }

    public static class DrinkOrderViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgDrink;
        private final TextView tvName;
        private final TextView tvPrice;
        private final TextView tvCount;
        private final TextView tvOption;

        public DrinkOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDrink = itemView.findViewById(R.id.img_drink);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvCount = itemView.findViewById(R.id.tv_count);
            tvOption = itemView.findViewById(R.id.tv_option);
        }
    }
}
