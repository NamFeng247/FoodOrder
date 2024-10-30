package com.app.foodordermanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodordermanagement.R;
import com.app.foodordermanagement.listener.IClickFoodListener;
import com.app.foodordermanagement.model.Food;
import com.app.foodordermanagement.utils.GlideUtils;

import java.util.List;

public class BannerViewPagerAdapter extends RecyclerView.Adapter<BannerViewPagerAdapter.BannerViewHolder> {

    private final List<Food> mListFood;
    private final IClickFoodListener IClickFoodListener;

    public BannerViewPagerAdapter(List<Food> list, IClickFoodListener listener) {
        this.mListFood = list;
        this.IClickFoodListener = listener;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Food food = mListFood.get(position);
        if (food == null) return;
        GlideUtils.loadUrlBanner(food.getBanner(), holder.imgBanner);
        holder.imgBanner.setOnClickListener(view
                -> IClickFoodListener.onClickFoodItem(food));
    }

    @Override
    public int getItemCount() {
        if (mListFood != null) {
            return mListFood.size();
        }
        return 0;
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgBanner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner = itemView.findViewById(R.id.img_Banner);
        }
    }
}
