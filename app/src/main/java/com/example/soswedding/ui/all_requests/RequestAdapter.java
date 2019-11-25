package com.example.soswedding.ui.all_requests;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.soswedding.Interface.RecyclerViewClickListener;
import com.example.soswedding.R;
import com.example.soswedding.model.Request;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {
    public static  Context context;
    public static Activity myActivity;
    private List<Request> requestsList;
    private static RecyclerViewClickListener itemListener;


    public static class RequestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView title;
        public TextView description;
        public TextView type;
        public Button seeMoreBtn;

        public RequestViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
            description = itemView.findViewById(R.id.requestDescriptionTv);
            type = itemView.findViewById(R.id.typeTv);
            seeMoreBtn = itemView.findViewById(R.id.seeMoreBtn);
            seeMoreBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());
        }
    }

    public RequestAdapter(Context context, Activity activity, List<Request> requestsList, RecyclerViewClickListener itemListener) {
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

        holder.title.setText(requestsList.get(position).getTitle());
        holder.description.setText(requestsList.get(position).getDescription());
        holder.type.setText("Type: " +requestsList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }
}
