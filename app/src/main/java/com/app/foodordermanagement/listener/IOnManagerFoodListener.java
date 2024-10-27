package com.app.foodordermanagement.listener;

import com.app.foodordermanagement.model.Food;

public interface IOnManagerFoodListener {
    void onClickUpdateFood(Food food);
    void onClickDeleteFood(Food food);
}
