package com.example.soswedding.ui.all_offers;

import android.util.Log;
import com.example.soswedding.model.Offer;
import com.example.soswedding.model.Singleton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllOffersViewModel extends ViewModel {

    public List<Offer> getDisplayableRequests(String result){
        String userType = Singleton.getInstance().getType();
        if(userType.equalsIgnoreCase("COUPLE")) {
            return getListOfOffersForCoupleUserOnly(result);
        }
        if(userType.equalsIgnoreCase("PROVIDER")) {
            return getListOfOffersForProviderUserOnly(result);
        }
        else {
            return null;
        }
    }

    private MutableLiveData<String> mText;

    public LiveData<String> getText() {
        return mText;
    }

    public List<Offer> getListOfAllOffers(String result) {
        try{
            return getOfferListFromJSONResponse(result);
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
        return null;
    }

    public List<Offer> getOfferListFromJSONResponse(String result) throws JSONException {
        ArrayList<Offer> offers = new ArrayList<>();
        JSONArray offersObjectArr = new JSONArray(result);
        for(int i = 0; i < offersObjectArr.length(); i++){
            JSONObject offerJSONObject = offersObjectArr.getJSONObject(i);
            Offer offerFromJSONObject = getOfferObjectFromJSONObject(offerJSONObject);
            offers.add(offerFromJSONObject);
        }
        System.out.println(offers);

        return offers;
    }

    private Offer getOfferObjectFromJSONObject(JSONObject objectJSONObject) throws JSONException {
        long id = objectJSONObject.getLong("id");
        double amount = objectJSONObject.getDouble("amount");
        String message = objectJSONObject.getString("message");
        String status = objectJSONObject.getString("status");
        String providerUuid = objectJSONObject.getString("providerUuid");
        String coupleUuid = objectJSONObject.getString("coupleUuid");
        long requestId = objectJSONObject.getLong("requestId");
        String companyName = objectJSONObject.getString("companyName");
        String requestTitle = objectJSONObject.getString("requestTitle");
        return new Offer(id,amount,message,status,providerUuid, coupleUuid, requestId,companyName, requestTitle);
    }

    public List<Offer> getListOfOffersForCoupleUserOnly(String result) {
        try {
            return getOfferListFromCoupleOnlyFromJSONResponse(result);
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
        return null;
    }

    public List<Offer> getListOfOffersForProviderUserOnly(String result) {
        try {
            return getOfferListFromProviderOnlyFromJSONResponse(result);
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
        return null;
    }

    public List<Offer> getOfferListFromCoupleOnlyFromJSONResponse(String result) throws JSONException {
        ArrayList<Offer> offers = new ArrayList<>();
        JSONArray offersObjectArr = new JSONArray(result);
        for(int i = 0; i < offersObjectArr.length(); i++){
            JSONObject offerJSONObject = offersObjectArr.getJSONObject(i);
            if(isOfferFromUser(offerJSONObject)){
                Offer rq = getOfferObjectFromJSONObject(offerJSONObject);
                offers.add(rq);
            }
        }
        System.out.println(offers);
        return offers;
    }

    public List<Offer> getOfferListFromProviderOnlyFromJSONResponse(String result) throws JSONException {
        ArrayList<Offer> offers = new ArrayList<>();
        JSONArray offersObjectArr = new JSONArray(result);
        for(int i = 0; i < offersObjectArr.length(); i++){
            JSONObject offerJSONObject = offersObjectArr.getJSONObject(i);
            if(isOfferFromUser(offerJSONObject)){
                Offer rq = getOfferObjectFromJSONObject(offerJSONObject);
                offers.add(rq);
            }
        }
        System.out.println(offers);
        return offers;
    }

    public boolean isOfferFromUser(JSONObject obj) throws JSONException {
        String userUid = Singleton.getInstance().getUuid();
        return userUid.equalsIgnoreCase(obj.getString("coupleUuid"));
    }

    public boolean isUserACouple(String type){

        return type.equalsIgnoreCase("COUPLE");
    }

    public boolean isUserAProvider(String type){

        return type.equalsIgnoreCase("PROVIDER");
    }


    public List<Offer> getOffersObjectForCouple(String result){
        try {
            ArrayList<Offer> offers = new ArrayList<>();
            JSONArray offerObjectArr = new JSONArray(result);
            String userUuid = Singleton.getInstance().getUuid();
            for(int i = 0; i < offerObjectArr.length(); i++){
                JSONObject obj = offerObjectArr.getJSONObject(i);
                if( userUuid.equalsIgnoreCase(obj.getString("coupleUuid"))){
                    long id = obj.getLong("id");
                    double amount = obj.getDouble("amount");
                    String message = obj.getString("message");
                    String status = obj.getString("status");
                    String providerUuid = obj.getString("providerUuid");
                    String coupleUuid = obj.getString("coupleUuid");
                    long requestId = obj.getLong("requestId");
                    String companyName = obj.getString("companyName");
                    String requestTitle = obj.getString("requestTitle");

                    Offer of = new Offer(id,amount,message,status,providerUuid, coupleUuid, requestId,companyName, requestTitle);
                    offers.add(of);
                }}
            return offers;
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
        return null;
    }


}
