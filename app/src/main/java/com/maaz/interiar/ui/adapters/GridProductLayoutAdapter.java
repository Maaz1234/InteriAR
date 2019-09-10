package com.maaz.interiar.ui.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maaz.interiar.R;
import com.maaz.interiar.ui.Models.HorizontalProductScrollModel;

import java.util.List;

// No need to seperate create model class for Grid Product Layout
// as we are using the same layout we have created before (horizontal scroll item layout).
// 1 Image view and 3 text view

public class GridProductLayoutAdapter extends BaseAdapter {

    List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public GridProductLayoutAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    // 4 items returned (1 ImageView and 3 TextView)
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if (convertView == null)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout,null);
            view.setElevation(0);
            view.setBackgroundColor(Color.parseColor("#ffffff"));

            ImageView productImage = view.findViewById(R.id.horizontal_scroll_product_image);
            TextView productTitle = view.findViewById(R.id.horizontal_scroll_product_title);
            TextView productDescription = view.findViewById(R.id.horizontal_scroll_product_description);
            TextView productPrice = view.findViewById(R.id.horizontal_scroll_product_price);

            productImage.setImageResource(horizontalProductScrollModelList.get(position).getProductImage());
            productTitle.setText(horizontalProductScrollModelList.get(position).getProductTitle());
            productDescription.setText(horizontalProductScrollModelList.get(position).getProductDescription());
            productPrice.setText(horizontalProductScrollModelList.get(position).getProductPrice());

        }
        else
        {
            view = convertView;
        }

        return view;
    }
}
