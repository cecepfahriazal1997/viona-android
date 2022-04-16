package com.viona.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viona.mobile.R;
import com.viona.mobile.model.FollowerModel;

import java.util.ArrayList;
import java.util.List;

public class ManageFollowerAdapter extends RecyclerView.Adapter<ManageFollowerAdapter.ViewHolder> {
    private final Context context;
    private List<FollowerModel> listData = new ArrayList<>();
    private final OnClickListener onClickListener;

    public ManageFollowerAdapter(Context context, List<FollowerModel> listData, OnClickListener onClickListener) {
        this.context = context;
        this.listData = listData;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_manage_follower, parent, false);
        return new ViewHolder(view, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FollowerModel item = listData.get(position);
        holder.name.setText(item.getName());
        holder.date.setText("Following since " + item.getJoined());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, date;
        RelativeLayout content;
        OnClickListener onClickListener;

        public ViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);

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