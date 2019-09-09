package com.maaz.interiar.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maaz.interiar.R;
import com.maaz.interiar.ui.Models.WishlistModel;
import com.maaz.interiar.ui.adapters.WishlistAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishListFragment extends Fragment {

    private RecyclerView wishlistRecyclerView;


    public MyWishListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_wish_list, container, false);

        wishlistRecyclerView = view.findViewById(R.id.my_wishlist_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishlistRecyclerView.setLayoutManager(linearLayoutManager);

        List<WishlistModel> wishlistModelList = new ArrayList<>();
        wishlistModelList.add(new WishlistModel(R.drawable.relaxer, "Relaxer", "3",145, "Rs.49999/-","Rs.59999/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.sofa, "Sofa", "5",15, "Rs.49999/-","Rs.59999/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.relaxer, "Relaxer", "4",14, "Rs.49999/-","Rs.59999/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.chair, "Chair", "2",125, "Rs.49999/-","Rs.59999/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.relaxer, "Relaxer", "1",100, "Rs.49999/-","Rs.59999/-","Cash on delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.table, "Table", "0",5, "Rs.49999/-","Rs.59999/-","Cash on delivery"));

        WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList);
        wishlistRecyclerView.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();

        return  view;
    }

}
