package com.viona.mobile.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viona.mobile.R;
import com.viona.mobile.model.DealsModel;

import java.util.ArrayList;
import java.util.List;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.ViewHolder> {
    private final Context context;
    private List<DealsModel> listData = new ArrayList<>();
    private final OnClickListener onClickListener;
    private int layoutId;

    public DealsAdapter(Context context, List<DealsModel> listData, int layoutId, OnClickListener onClickListener) {
        this.context = context;
        this.listData = listData;
        this.layoutId = layoutId;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(this.layoutId, parent, false);
        return new ViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DealsModel item = listData.get(position);
        holder.name.setText(item.getName());
        holder.price.setText(String.format("%,.0f", item.getPrice()));
        holder.owner.setText(item.getOwner());
        if (item.getDiscount().equals("0")) {
            holder.discount.setVisibility(View.GONE);
            holder.priceOld.setVisibility(View.GONE);
        } else {
            holder.discount.setText("Discount " + item.getDiscount() + "%");
            holder.priceOld.setText("Rp " + String.format("%,.0f", item.getPriceOld()));
        }
        holder.period.setText(item.getPeriod());
        holder.priceOld.setPaintFlags(holder.priceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView name, price, priceOld, owner, period, discount;
        OnClickListener onClickListener;

        public ViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            priceOld = itemView.findViewById(R.id.price_old);
            owner = itemView.findViewById(R.id.owner);
            discount = itemView.findViewById(R.id.discount);
            period = itemView.findViewById(R.id.expired);
            this.onClickListener = onClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClickListener(getAdapterPosition());
        }
    }

    public interface OnClickListener {
        void onClickListener(int position);
    }
}