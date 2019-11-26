package com.example.soswedding.ui.all_offers;

import android.util.Log;

import com.example.soswedding.model.Offer;
import com.example.soswedding.model.Singleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllOffersViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public AllOffersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is all offer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

//    public List<Offer> getMockupList() {
//        Offer of = new Offer(1000.0, "I offer dj services", "PENDING","","",);
//        List<Offer> list = new ArrayList<>();
//        for(int i =0; i < 15; i++)
//            list.add(of);
//        return list;
//    }

    public List<Offer> getOffersObject(String result) {
        try {
            ArrayList<Offer> offers = new ArrayList<>();
            JSONArray offersObjectArr = new JSONArray(result);
            for(int i = 0; i < offersObjectArr.length(); i++){
                JSONObject obj = offersObjectArr.getJSONObject(i);
                long id = obj.getLong("id");
                double amount = obj.getDouble("amount");
                String message = obj.getString("message");
                String status = obj.getString("status");
                String providerUuid = obj.getString("providerUuid");
                String coupleUuid = obj.getString("coupleUuid");
                long requestId = obj.getLong("requestId");
                Offer of = new Offer(id,amount,message,status,providerUuid, coupleUuid, requestId);
                offers.add(of);
            }
            return offers;

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
        return null;
    }

    public List<Offer> getOffersObjectForCouple(String result){
         try {
        ArrayList<Offer> offers = new ArrayList<>();
        JSONArray offerObjectArr = new JSONArray(result);
        double userId = Singleton.getInstance().getId();
        for(int i = 0; i < offerObjectArr.length(); i++){
            JSONObject obj = offerObjectArr.getJSONObject(i);
            if( userId == obj.getDouble("userId")){
            long id = obj.getLong("id");
            double amount = obj.getDouble("amount");
            String message = obj.getString("message");
            String status = obj.getString("status");
            String providerUuid = obj.getString("providerUuid");
            String coupleUuid = obj.getString("coupleUuid");
            long requestId = obj.getLong("requestId");
            Offer of = new Offer(id,amount,message,status,providerUuid, coupleUuid, requestId);
            offers.add(of);
        }}
        return offers;
    } catch (Throwable t) {
        Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
    }
        return null;
}
}
