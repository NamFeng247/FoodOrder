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

public class FoodOrderAdapter extends RecyclerView.Adapter<FoodOrderAdapter.FoodOrderViewHolder> {

    private final List<FoodOrder> listFoodOrder;

    public FoodOrderAdapter(List<FoodOrder> list) {
        this.listFoodOrder = list;
    }

    @NonNull
    @Override
    public FoodOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_Food_order, parent, false);
        return new FoodOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodOrderViewHolder holder, int position) {
        FoodOrder FoodOrder = listFoodOrder.get(position);
        if (FoodOrder == null) return;

        GlideUtils.loadUrl(FoodOrder.getImage(), holder.imgFood);
        holder.tvName.setText(FoodOrder.getName());
        String strPrice = FoodOrder.getPrice() + Constant.CURRENCY;
        holder.tvPrice.setText(strPrice);
        holder.tvOption.setText(FoodOrder.getOption());
        String strCount = "x" + FoodOrder.getCount();
        holder.tvCount.setText(strCount);
    }

    @Override
    public int getItemCount() {
        if (listFoodOrder != null) {
            return listFoodOrder.size();
        }
        return 0;
    }

    public static class FoodOrderViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgFood;
        private final TextView tvName;
        private final TextView tvPrice;
        private final TextView tvCount;
        private final TextView tvOption;

        public FoodOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_Food);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvCount = itemView.findViewById(R.id.tv_count);
            tvOption = itemView.findViewById(R.id.tv_option);
        }
    }
}
