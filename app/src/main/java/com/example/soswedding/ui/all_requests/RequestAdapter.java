package com.example.soswedding.ui.all_requests;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;

import com.example.soswedding.R;
import com.example.soswedding.model.Request;
import com.example.soswedding.ui.Request.RequestFragment;
import com.example.soswedding.ui.forgotPassword.ForgotPasswordFragment;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {
    public static  Context context;
    public static Activity myActivity;
    private List<Request> requestsList;


    public static class RequestViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title;
        public TextView description;
        public TextView type;
        public Button seeMoreBtn;

        public RequestViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTv);
            description = itemView.findViewById(R.id.descriptionTv);
            type = itemView.findViewById(R.id.typeTv);
            seeMoreBtn = itemView.findViewById(R.id.seeMoreBtn);
            seeMoreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRequestClick();
                }
            });
        }
    }
    public static void onRequestClick() {
        FragmentManager manager = ((AppCompatActivity) myActivity).getSupportFragmentManager();
        Fragment fragment = null;
        try {
            fragment = RequestFragment.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        manager.beginTransaction().replace(R.id.homeMenuContainer, fragment).commit();
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public RequestAdapter(Context context, Activity activity, List<Request> requestsList) {
        this.context = context;
        this.myActivity = activity;
        this.requestsList = requestsList;
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

    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }
}
