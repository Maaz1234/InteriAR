package com.maaz.interiar.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maaz.interiar.R;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NotificationListViewHolder> {

    private String[] data;
    public NotificationListAdapter(String[] data){
        this.data= data;
    }

    @NonNull
    @Override
    public NotificationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notlist_item_layout,parent,false);
        return new NotificationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationListViewHolder holder, int position) {
     String title = data[position];
     holder.txttitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class NotificationListViewHolder extends RecyclerView.ViewHolder{
        ImageView imgicon;
        TextView  txttitle;
        public NotificationListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgicon = (ImageView)itemView.findViewById(R.id.imgicon);
            txttitle = (TextView) itemView.findViewById(R.id.txtTitle);
        }
    }
}
