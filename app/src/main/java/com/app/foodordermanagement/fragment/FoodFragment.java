package com.app.foodordermanagement.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.foodordermanagement.MyApplication;
import com.app.foodordermanagement.R;
import com.app.foodordermanagement.activity.FoodDetailActivity;
import com.app.foodordermanagement.adapter.FoodAdapter;
import com.app.foodordermanagement.adapter.FilterAdapter;
import com.app.foodordermanagement.event.SearchKeywordEvent;
import com.app.foodordermanagement.model.Food;
import com.app.foodordermanagement.model.Filter;
import com.app.foodordermanagement.utils.Constant;
import com.app.foodordermanagement.utils.GlobalFunction;
import com.app.foodordermanagement.utils.StringUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class FoodFragment extends Fragment {

    private View mView;
    private RecyclerView rcvFilter;
    private RecyclerView rcvDrink;

    private List<Food> listFood;
    private List<Food> listFoodDisplay;
    private List<Food> listFoodKeyWord;
    private List<Filter> listFilter;
    private FoodAdapter foodAdapter;
    private FilterAdapter filterAdapter;
    private int categoryId;
    private Filter currentFilter;
    private String keyword = "";

    public static FoodFragment newInstance(int categoryId) {
        FoodFragment foodFragment = new FoodFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.CATEGORY_ID, categoryId);
        foodFragment.setArguments(bundle);
        return foodFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_drink, container, false);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        getDataArguments();
        initUi();
        initListener();

        /*getListFilter();*/
        getListFood();

        return mView;
    }

    private void getDataArguments() {
        Bundle bundle = getArguments();
        if (bundle == null) return;
        categoryId = bundle.getInt(Constant.CATEGORY_ID);
    }

    private void initUi() {
        rcvFilter = mView.findViewById(R.id.rcv_filter);
        rcvDrink = mView.findViewById(R.id.rcv_drink);
        displayListDrink();
    }

    private void initListener() {
    }

    /*private void getListFilter() {
        listFilter = new ArrayList<>();
        listFilter.add(new Filter(Filter.TYPE_FILTER_ALL, getString(R.string.filter_all)));
        listFilter.add(new Filter(Filter.TYPE_FILTER_RATE, getString(R.string.filter_rate)));
        listFilter.add(new Filter(Filter.TYPE_FILTER_PRICE, getString(R.string.filter_price)));
        listFilter.add(new Filter(Filter.TYPE_FILTER_PROMOTION, getString(R.string.filter_promotion)));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false);
        rcvFilter.setLayoutManager(linearLayoutManager);
        currentFilter = listFilter.get(0);
        currentFilter.setSelected(true);
        filterAdapter = new FilterAdapter(getActivity(), listFilter, this::handleClickFilter);
        rcvFilter.setAdapter(filterAdapter);
    }*/

    /*@SuppressLint("NotifyDataSetChanged")
    private void handleClickFilter(Filter filter) {
        for (Filter filterEntity : listFilter) {
            if (filterEntity.getId() == filter.getId()) {
                filterEntity.setSelected(true);
                setListDrinkDisplay(filterEntity, keyword);
                currentFilter = filterEntity;
            } else {
                filterEntity.setSelected(false);
            }
        }
        if (filterAdapter != null) filterAdapter.notifyDataSetChanged();
    }*/

    private void getListFood() {
        if (getActivity() == null) return;
        MyApplication.get(getActivity()).getFoodDatabaseReference()
                .orderByChild(Constant.CATEGORY_ID).equalTo(categoryId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (listFood != null) {
                            listFood.clear();
                        } else {
                            listFood = new ArrayList<>();
                        }
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Food food = dataSnapshot.getValue(Food.class);
                            if (food != null) {
                                listFood.add(0, food);
                            }
                        }
                        setListDrinkDisplay(new Filter(Filter.TYPE_FILTER_ALL, getString(R.string.filter_all)), keyword);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
    }

    private void displayListDrink() {
        if (getActivity() == null) return;
        listFoodDisplay = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvDrink.setLayoutManager(linearLayoutManager);
        foodAdapter = new FoodAdapter(listFoodDisplay, food -> {
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.FOOD_ID, food.getId());
            GlobalFunction.startActivity(getActivity(), FoodDetailActivity.class, bundle);
        });
        rcvDrink.setAdapter(foodAdapter);
    }

    private void setListDrinkDisplay(@NonNull Filter filter,@Nullable String keyword) {
        if (listFood == null || listFood.isEmpty()) return;

        if (listFoodKeyWord != null) {
            listFoodKeyWord.clear();
        } else {
            listFoodKeyWord = new ArrayList<>();
        }

        if (listFoodDisplay != null) {
            listFoodDisplay.clear();
        } else {
            listFoodDisplay = new ArrayList<>();
        }

        if (!StringUtil.isEmpty(keyword)) {
            for (Food food : listFood) {
                if (getTextSearch(food.getName()).toLowerCase().trim()
                        .contains(getTextSearch(keyword).toLowerCase().trim())) {
                    listFoodKeyWord.add(food);
                }
            }
            switch (filter.getId()) {
                case Filter.TYPE_FILTER_ALL:
                    listFoodDisplay.addAll(listFoodKeyWord);
                    break;

                case Filter.TYPE_FILTER_RATE:
                    listFoodDisplay.addAll(listFoodKeyWord);
                    Collections.sort(listFoodDisplay,
                            (drink1, drink2) -> Double.compare(drink2.getRate(), drink1.getRate()));
                    break;

                case Filter.TYPE_FILTER_PRICE:
                    listFoodDisplay.addAll(listFoodKeyWord);
                    Collections.sort(listFoodDisplay,
                            (drink1, drink2) -> Integer.compare(drink1.getRealPrice(), drink2.getRealPrice()));
                    break;

                case Filter.TYPE_FILTER_PROMOTION:
                    for (Food food : listFoodKeyWord) {
                        if (food.getSale() > 0) listFoodDisplay.add(food);
                    }
                    break;
            }
        } else {
            switch (filter.getId()) {
                case Filter.TYPE_FILTER_ALL:
                    listFoodDisplay.addAll(listFood);
                    break;

                case Filter.TYPE_FILTER_RATE:
                    listFoodDisplay.addAll(listFood);
                    Collections.sort(listFoodDisplay,
                            (drink1, drink2) -> Double.compare(drink2.getRate(), drink1.getRate()));
                    break;

                case Filter.TYPE_FILTER_PRICE:
                    listFoodDisplay.addAll(listFood);
                    Collections.sort(listFoodDisplay,
                            (drink1, drink2) -> Integer.compare(drink1.getRealPrice(), drink2.getRealPrice()));
                    break;

                case Filter.TYPE_FILTER_PROMOTION:
                    for (Food food : listFood) {
                        if (food.getSale() > 0) listFoodDisplay.add(food);
                    }
                    break;
            }
        }
        reloadListDrink();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void reloadListDrink() {
        if (foodAdapter != null) foodAdapter.notifyDataSetChanged();
    }

    public String getTextSearch(String input) {
        String nfdNormalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchKeywordEvent(SearchKeywordEvent event) {
        keyword = event.getKeyword();
        setListDrinkDisplay(currentFilter, keyword);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (filterAdapter != null) filterAdapter.release();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
