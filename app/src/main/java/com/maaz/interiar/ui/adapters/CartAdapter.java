package com.maaz.interiar.ui.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maaz.interiar.R;
import com.maaz.interiar.ui.Models.CartItemModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType())
        {
            case 0:
                return CartItemModel.CART_ITEM;

            case 1:
                return CartItemModel.TOTAL_AMOUNT;

                default:
                    return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        switch (viewType){
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_layout,viewGroup,false);
                return new CartItemViewholder(cartItemView);

            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_total_amount_layout,viewGroup,false);
                return new CartTotalAmountViewholder(cartTotalView);

                default:
                    return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position)
    {
        switch (cartItemModelList.get(position).getType())
        {
            case CartItemModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                String productPrice = cartItemModelList.get(position).getProductPrice();
                String cuttedPrice = cartItemModelList.get(position).getCuttedPrice();

                /*int freeCoupens = cartItemModelList.get(position).getFreeCoupens;
                int offerApplied = cartItemModelList.get(position).getOffersApplied;*/
/*              ((CartItemViewholder)viewHolder).setItemDatails(resource,title,freeCoupens,productPrice,cuttedPrice, offersApplied);*/

                ((CartItemViewholder)viewHolder).setItemDatails(resource,title,productPrice,cuttedPrice);
                break;

            case CartItemModel.TOTAL_AMOUNT:
                String totalItems = cartItemModelList.get(position).getTotalItems();
                String totalItemPrice = cartItemModelList.get(position).getTotalItemPrice();
                String deliverPrice = cartItemModelList.get(position).getDeliveryPrice();
                String totalAmount = cartItemModelList.get(position).getTotalAmount();
                String savedAmount = cartItemModelList.get(position).getSavedAmount();
                ((CartTotalAmountViewholder)viewHolder).setTotalAmount(totalItems,totalItemPrice, deliverPrice, totalAmount, savedAmount);
                break;

                default:
                    return;
        }
    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }


    class CartItemViewholder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private TextView productPrice;
        private TextView cuttedPrice;
        private TextView productQuantity;

        private ImageView freeCoupenIcon;
        private TextView freeCoupens;
        private TextView offersApplied;
        private TextView coupensApplied;


        public CartItemViewholder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);
            cuttedPrice = itemView.findViewById(R.id.cutted_price);
            productQuantity = itemView.findViewById(R.id.product_quantity);

            /*freeCoupens = itemView.findViewById(R.id.tv_free_coupen);
            freeCoupenIcon = itemView.findViewById(R.id.free_coupen_icon);
            offersApplied= itemView.findViewById(R.id.offers_applied);
            coupensApplied = itemView.findViewById(R.id.coupens_applied);*/
        }

        /*private void setItemDatails(int resource, String title, int freeCoupensNo, String productPriceText, String cuttedPriceText, int offersAppliedNo)
        {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (freeCoupensNo >0)
            {
                freeCoupenIcon.setVisibility(View.VISIBLE);
                freeCoupens.setVisibility(View.VISIBLE);
                if (freeCoupensNo == 1)
                {
                    freeCoupens.setText("free" + freeCoupensNo + "Coupen");
                }
                else
                {
                    freeCoupens.setText("free" + freeCoupensNo + "Coupens");
                }
            }
            else
            {
                freeCoupenIcon.setVisibility(View.INVISIBLE);
                freeCoupens.setVisibility(View.INVISIBLE);
            }

            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);

            if (offersAppliedNo > 0)
            {
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offersAppliedNo + "Offers applied");
            }
            else
            {
                offersApplied.setVisibility(View.INVISIBLE);
            }

        }*/

        private void setItemDatails(int resource, String title,  String productPriceText, String cuttedPriceText)
        {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            productPrice.setText(productPriceText);
            cuttedPrice.setText(cuttedPriceText);

        }
    }

    class CartTotalAmountViewholder extends RecyclerView.ViewHolder{

        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;


        public CartTotalAmountViewholder(@NonNull View itemView) {
            super(itemView);

            totalItems = itemView.findViewById(R.id.total_items);
            totalItemPrice = itemView.findViewById(R.id.total_items_price);
            deliveryPrice = itemView.findViewById(R.id.delivery_price);
            totalAmount = itemView.findViewById(R.id.total_price);
            savedAmount = itemView.findViewById(R.id.saved_amount);
        }

        private void setTotalAmount(String totalItemText, String totalItemPriceText,String deliverPriceText, String totalAmountText, String savedAmountText )
        {
            totalItems.setText(totalAmountText);
            totalItemPrice.setText(totalItemText);
            deliveryPrice.setText(deliverPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);
        }
    }
}