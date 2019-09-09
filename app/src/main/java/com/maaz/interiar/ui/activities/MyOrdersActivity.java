package com.maaz.interiar.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.maaz.interiar.R;
import com.maaz.interiar.ui.Models.MyOrderItemModel;
import com.maaz.interiar.ui.adapters.MyOrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {

    private RecyclerView myOrdersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        myOrdersRecyclerView = findViewById(R.id.my_orders_recyclerview);

        /*LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL);*/
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        myOrdersRecyclerView.setLayoutManager(layoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.sofa, "Sofa", "Delivered on Monday, 15th Jan 2017", 2));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.table, "Table", "Delivered on Monday, 15th Jan 2017", 1));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.chair, "Chair", "Cancelled", 4));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.relaxer, "Relaxer", "Delivered on Monday, 15th Jan 2017", 5));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrdersRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();
    }
}
