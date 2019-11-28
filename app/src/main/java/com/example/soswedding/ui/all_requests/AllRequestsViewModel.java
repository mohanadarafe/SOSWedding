package com.example.soswedding.ui.all_requests;

import android.util.Log;

import com.example.soswedding.model.Request;
import com.example.soswedding.model.Singleton;
import com.example.soswedding.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllRequestsViewModel extends ViewModel {


    public List<Request> getDisplayableRequests(String result){
        if(isUserACouple(Singleton.getInstance().getType())) {
            return getListOfRequestsForCoupleUserOnly(result);
        }
        else {
            return getListOfAllRequests(result);
        }
    }

    public boolean isUserACouple(String type){

        return type.equalsIgnoreCase("COUPLE");
    }

    public List<Request> getListOfAllRequests(String result) {
        try{
            return getRequestListFromJSONResponse(result);
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
        return null;
    }
    public List<Request> getRequestListFromJSONResponse(String result) throws JSONException {
        ArrayList<Request> requests = new ArrayList<>();
        JSONArray requestsObjectArr = new JSONArray(result);
        for(int i = 0; i < requestsObjectArr.length(); i++){
            JSONObject requestJSONObject = requestsObjectArr.getJSONObject(i);
            Request requestFromJSONObject = getRequestObjectFromJSONObject(requestJSONObject);
            requests.add(requestFromJSONObject);
        }
        return requests;
    }

    private Request getRequestObjectFromJSONObject(JSONObject requestJSONObject) throws JSONException {
        String address = requestJSONObject.getString("address");
        String description = requestJSONObject.getString("description");
        String type = requestJSONObject.getString("serviceType");
        double budget = Double.parseDouble(requestJSONObject.getString("budget"));
        String title = requestJSONObject.getString("title");
        long id       = requestJSONObject.getLong("id");
        String coupleUuid = requestJSONObject.getString("coupleUuid");
        return new Request(id, title, description, type, address, budget,coupleUuid);
    }


    public List<Request> getListOfRequestsForCoupleUserOnly(String result) {
        try {
            return getRequestListFromCoupleOnlyFromJSONResponse(result);
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
        return null;
    }
    public List<Request> getRequestListFromCoupleOnlyFromJSONResponse(String result) throws JSONException {
        ArrayList<Request> requests = new ArrayList<>();
        JSONArray requestsObjectArr = new JSONArray(result);
        for(int i = 0; i < requestsObjectArr.length(); i++){
            JSONObject requestJSONObject = requestsObjectArr.getJSONObject(i);
            if(isRequestFromUser(requestJSONObject)){
                Request rq = getRequestObjectFromJSONObject(requestJSONObject);
                requests.add(rq);
            }
        }
        return requests;
    }
    public boolean isRequestFromUser(JSONObject obj) throws JSONException {
        String userUid = Singleton.getInstance().getUuid();
        return userUid.equalsIgnoreCase(obj.getString("coupleUuid"));
    }

}