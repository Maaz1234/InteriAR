package com.maaz.interiar.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.maaz.interiar.R;
import com.maaz.interiar.ui.Models.CartItemModel;
import com.maaz.interiar.ui.adapters.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    public RecyclerView cartItemsRecyclerView;

    public CartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        cartItemsRecyclerView = view.findViewById(R.id.cart_items_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.sofa, "Sofa", "Rs.49999/-", "Rs.59999/-", 1));
        cartItemModelList.add(new CartItemModel(0,R.drawable.table, "Table", "Rs.19999/-", "Rs.29999/-", 2));
        cartItemModelList.add(new CartItemModel(0,R.drawable.sofa, "Sofa", "Rs.49999/-", "Rs.59999/-", 1));
        cartItemModelList.add(new CartItemModel(1,"Price (3 items)", "Rs.169999/-", "Free", "Rs.169999/-", "Rs.5999/-"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        return view;
    }

}
