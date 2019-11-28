package com.example.soswedding.ui.Request;

import android.content.Context;
import android.util.Log;

import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.model.Request;
import com.example.soswedding.model.User;

import androidx.lifecycle.ViewModel;

import static com.example.soswedding.service.BidService.postBid;

public class RequestViewModel extends ViewModel {
    boolean postedSuccessfully;

    public boolean postBidModel(Context context, String amount, Request request, User user){
        postedSuccessfully = false;
        if(!verifyCoupleUserType(user)){
            sendPostBidRequest(context,amount,request,user);
            postedSuccessfully = true;
        }
        return postedSuccessfully;
    }

    public void sendPostBidRequest(Context context,String amount,Request request, User user){
        postBid(context,amount,request, user, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e(result,"Successfully posted bid");
            }
        });
    }



    public boolean verifyCoupleUserType(User user){
        return user.getType().equalsIgnoreCase(("COUPLE"));
    }


    public boolean userInputValidation(String str){
        return str.length()>0;
    }



}
