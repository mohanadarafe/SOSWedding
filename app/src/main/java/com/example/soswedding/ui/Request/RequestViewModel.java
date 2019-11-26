package com.example.soswedding.ui.Request;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.model.Singleton;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import static com.example.soswedding.service.BidService.postBid;

public class RequestViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public RequestViewModel() {

    }
    public void postBidModel(Context context,String amount,long requestID){
        double userID = Singleton.getInstance().getId(); //never used...
        postBid(context,amount,requestID, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("My App", "SUCCESS!");
            }
        });
    }
}
