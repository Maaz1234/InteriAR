package com.maaz.interiar.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maaz.interiar.R;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageListViewHolder>{

    private String[] data;
    public MessageListAdapter(String[] data){
        this.data= data;
    }

    @NonNull
    @Override
    public MessageListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.msglist_item_layout,parent,false);
        return new MessageListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageListViewHolder holder, int position) {
        String title = data[position];
        holder.texttitle.setText(title);
    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MessageListViewHolder extends RecyclerView.ViewHolder{
        ImageView imgicons;
        TextView texttitle;

        public MessageListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgicons = (ImageView)itemView.findViewById(R.id.imgicon);
            texttitle = (TextView) itemView.findViewById(R.id.txtTitle);
        }
    }
}
