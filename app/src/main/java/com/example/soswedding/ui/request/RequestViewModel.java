package com.example.soswedding.ui.request;

import android.content.Context;
import android.util.Log;

import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.model.Request;
import com.example.soswedding.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.lifecycle.ViewModel;

import static com.example.soswedding.service.BidService.postBid;

public class RequestViewModel extends ViewModel {
    public RequestViewModel(){
    }

    public void postBidModel(Context context, String amount, Request request, User user){
        if(!verifyCoupleUserType(user) && userInputValidation(amount)){
            JSONObject bidObject = createBidObject(amount,request,user);
            sendPostBidRequest(context,bidObject);
        }
    }

    public void sendPostBidRequest(Context context,JSONObject bidObject){
        postBid(context,bidObject, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e(result,"Successfully posted bid");
            }
        });
    }


    public JSONObject createBidObject(String amount, Request request, User user){
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("amount", Double.parseDouble(amount));
            jsonBody.put("message", "Hello WORLD");
            jsonBody.put("status", "PENDING");
            jsonBody.put("providerUuid", user.getUuid());
            jsonBody.put("coupleUuid", request.getuID());
            jsonBody.put("requestId", request.getId());
            jsonBody.put("requestTitle", request.getTitle());
            jsonBody.put("companyName", user.getCompanyName());
            return jsonBody;
        }
        catch( JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verifyCoupleUserType (User user){
        return user.getType().equalsIgnoreCase(("COUPLE"));
    }

    public boolean userInputValidation(String str){
        return str.length()>0;
    }
}
