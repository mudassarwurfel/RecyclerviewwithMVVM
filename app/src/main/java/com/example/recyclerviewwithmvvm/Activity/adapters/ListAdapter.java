package com.example.recyclerviewwithmvvm.Activity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewwithmvvm.Activity.Model.NicePlaces;
import com.example.recyclerviewwithmvvm.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    Context context;
    List<NicePlaces> places;

    public ListAdapter(Context context, List<NicePlaces> places) {
        this.context = context;
        this.places = places;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.tvPlaceName.setText(places.get(position).getPlaceName());
        holder.ivThumb.setImageDrawable(context.getResources().getDrawable(places.get(position).getImgUrl()));

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvPlaceName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivThumb = itemView.findViewById(R.id.ivThumb);
            tvPlaceName = itemView.findViewById(R.id.tvTitle);
        }
    }
}
