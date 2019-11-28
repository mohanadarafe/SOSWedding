package com.example.soswedding.ui.all_offers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.soswedding.Interface.OnOfferItemClickListener;
import com.example.soswedding.R;
import com.example.soswedding.model.Offer;



import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class OffersAdapter  extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {
    public static Context context;
    public static Activity myActivity;
    private List<Offer> offersList;
    private static OnOfferItemClickListener itemListener;

    public static class OffersViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView title;
        public TextView status;
        public TextView currentBid;
        public ImageView auctionLogo;

        public OffersViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
            currentBid = itemView.findViewById(R.id.currentBidTv);
            status = itemView.findViewById(R.id.statusTv);
            auctionLogo = itemView.findViewById(R.id.auctionLogoTv);
        }

        public void bind(final Offer item, final OnOfferItemClickListener listener) {
            title.setText(item.getRequestTitle());
            currentBid.setText("Current Bid: $ "+String.valueOf(item.getAmount()));
            status.setText("Bid Status: "+item.getStatus());

            this.setStatusLabelStyle(this, item.getStatus());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

        public void setStatusLabelStyle(OffersViewHolder holder, String status) {

            switch (status){
                case "PENDING":
                    holder.status.setTextAppearance(R.style.statusPending);
                    holder.auctionLogo.setImageResource(R.drawable.speech_bubble);
                    break;
                case "DECLINED":
                    holder.status.setTextAppearance(R.style.statusDeclined);
                    auctionLogo.setImageResource(R.drawable.cancel);

                    break;
                case "ACCEPTED":
                    holder.status.setTextAppearance(R.style.statusAccepted);
                    auctionLogo.setImageResource(R.drawable.accept);
                    break;
            }
        }


    }

    public OffersAdapter(Context context, Activity activity, List<Offer> offersList, OnOfferItemClickListener itemListener) {
        this.context = context;
        this.myActivity = activity;
        this.offersList = offersList;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public OffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.offer, null);
        OffersViewHolder holder = new OffersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OffersViewHolder holder, int position) {

        holder.bind(offersList.get(position), itemListener);
    }



    @Override
    public int getItemCount() {
        return offersList.size();
    }
}
