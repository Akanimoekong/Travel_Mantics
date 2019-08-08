package com.example.Travelmantics;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {
    ArrayList<TravelDeal> deals;
    private ImageView imageDeal;

    public DealAdapter() {

    }

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_row, parent, false);
        return new DealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {
        TravelDeal deal = deals.get(position);
        Log.d("TAG", "bind:->" + deal.getPrice());
        Log.d("TAG", "bind:->" + deal.getPrice());
        holder.bind(deal);
    }

    @Override
    public int getItemCount() {
        if (deals == null) {
            return 0;
        } else {
            return deals.size();
        }
    }

    public void setDealsList(ArrayList<TravelDeal> dealsList) {
        this.deals = dealsList;
        Log.d("TAG", "setDealsList:-> " + this.deals.get(0).getPrice());
        notifyDataSetChanged();
        //notifyItemInserted(dealsList.size()-1);
    }

    public class DealViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener{

        AppCompatTextView tvTitle;
        AppCompatTextView tvDescription;
        AppCompatTextView tvPrice;

        public DealViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imageDeal = itemView.findViewById(R.id.imageDeal);
            itemView.setOnClickListener(this);
        }

        public void bind(TravelDeal deal) {
            Log.d("TAG", "bind:->" + deal.getPrice());
            tvTitle.setText(deal.getTitle());
            tvDescription.setText(deal.getDescription());
            tvPrice.setText(deal.getPrice());
            showImage(deal.getImageUrl());


        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.d("Click", String.valueOf(position));
            TravelDeal selectedDeal = deals.get(position);
            Intent intent = new Intent(view.getContext(),DealActivity.class);
            intent.putExtra("Deal", selectedDeal);
            view.getContext().startActivity(intent);
        }

        private void showImage(String url){
            if(url != null && url.isEmpty() == false){
                Picasso.get()
                        .load(url)
                        .resize(180,180)
                        .centerCrop()
                        .into(imageDeal);
            }
        }

    }
}
