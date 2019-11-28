package com.example.soswedding.ui.all_requests;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soswedding.Interface.OnRequestItemClickListener;
import com.example.soswedding.R;
import com.example.soswedding.model.Request;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {
    public static  Context context;
    public static Activity myActivity;
    private List<Request> requestsList;
    private static OnRequestItemClickListener itemListener;

    public RequestAdapter(Context context, Activity activity, List<Request> requestsList, OnRequestItemClickListener itemListener) {
        this.context = context;
        this.myActivity = activity;
        this.requestsList = requestsList;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.request, null);
        RequestViewHolder holder = new RequestViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {

        holder.bind(requestsList.get(position), itemListener);

    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public ImageView logo;
        public TextView title;
        public TextView type;
        //        public Button seeMoreBtn;
        private TextView budget;


        public RequestViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
            type = itemView.findViewById(R.id.typeTv);
            budget = itemView.findViewById(R.id.allRequestBudgetTv);
            logo = itemView.findViewById(R.id.serviceLogo);
        }

        public void bind(final Request item, final OnRequestItemClickListener listener) {
            title.setText(item.getTitle());
            budget.setText("Budget: "+item.getBudget());
            type.setText("Type: " + item.getType());
            getImageId(item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
        public void getImageId(Request request){
            switch(request.getType()){
                case "DANCE" : logo.setImageResource(R.drawable.dance_service);
                break;
                case "BAKER" : logo.setImageResource(R.drawable.cake_service);
                    break;
                case "CAR" : logo.setImageResource(R.drawable.car_service);
                    break;
                case "RECEPTION" : logo.setImageResource(R.drawable.reception_service);
                    break;
                case "ALCOHOL" : logo.setImageResource(R.drawable.alcohol_service);
                    break;
                case "CATERING" : logo.setImageResource(R.drawable.catering_service);
                    break;
                case "MUSIC" : logo.setImageResource(R.drawable.music_service);
                    break;
                case "FLORIST" : logo.setImageResource(R.drawable.flower_service);
                    break;
                case "PHOTOGRAPHER" : logo.setImageResource(R.drawable.photographer_service);
                    break;

            }
        }
    }
}
