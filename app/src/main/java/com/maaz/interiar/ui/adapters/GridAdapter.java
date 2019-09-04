package com.maaz.interiar.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maaz.interiar.R;

public class GridAdapter extends BaseAdapter {

    Context context;
    private final String[] categoriesname;
    private final int[] categoriesimages;
    View view;
    LayoutInflater layoutInflater;

    public GridAdapter(Context context, String[] categoriesname, int[] categoriesimages) {
        this.context = context;
        this.categoriesname = categoriesname;
        this.categoriesimages = categoriesimages;
    }

    @Override
    public int getCount() {
        return categoriesname.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
        {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.img_categories);
            TextView textView = (TextView) view. findViewById(R.id.tv_categories);
            imageView.setImageResource(categoriesimages[position]);
            textView.setText(categoriesname[position]);

        }

        return view;
    }
}
