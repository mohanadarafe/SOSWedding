package com.example.soswedding.ui.all_offers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;

import com.example.soswedding.Interface.RecyclerViewClickListener;
import com.example.soswedding.R;
import com.example.soswedding.model.Offer;



import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


public class OffersAdapter  extends RecyclerView.Adapter<OffersAdapter.OffersViewHolder> {
    public static Context context;
    public static Activity myActivity;
    private List<Offer> offersList;
    private static RecyclerViewClickListener itemListener;

    public static class OffersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView title;
//        public TextView name;
//        public TextView price; //double or float?
        public TextView description;
        public TextView type;
        public Button seeMoreBtn;

        public OffersViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
//            name = itemView.findViewById(R.id.nameTv);
//            price = itemView.findViewById(R.id.priceTv);
            description = itemView.findViewById(R.id.descriptionTv);
            type = itemView.findViewById(R.id.typeTv);
            seeMoreBtn = itemView.findViewById(R.id.seeMoreBtn);

            seeMoreBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());
        }
    }

    public OffersAdapter(Context context, Activity activity, List<Offer> offersList, RecyclerViewClickListener itemListener) {
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

    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }
}
