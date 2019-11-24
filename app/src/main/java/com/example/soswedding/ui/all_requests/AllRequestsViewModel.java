package com.example.soswedding.ui.all_requests;

import android.util.Log;

import com.example.soswedding.model.Request;
import com.example.soswedding.model.Singleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllRequestsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AllRequestsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is all request fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public List<Request> getRequestsObject(String result) {
        try {
            ArrayList<Request> requests = new ArrayList<>();
            JSONArray requestsObjectArr = new JSONArray(result);
            for(int i = 0; i < requestsObjectArr.length(); i++){
                JSONObject obj = requestsObjectArr.getJSONObject(i);
                String address = obj.getString("address");
                String description = obj.getString("description");
                String type = obj.getString("serviceType");
                String budget = obj.getString("budget");
                Request rq = new Request("no title", description, type, address, budget);
                requests.add(rq);
            }
            return requests;

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
        return null;
    }


    public List<Request> getRequestsObjectForCouple(String result) {
        try {
            ArrayList<Request> requests = new ArrayList<>();
            JSONArray requestsObjectArr = new JSONArray(result);
            double userId = Singleton.getInstance().getId();
            for(int i = 0; i < requestsObjectArr.length(); i++){
                JSONObject obj = requestsObjectArr.getJSONObject(i);
                if(userId == obj.getDouble("userId")){
                    String address = obj.getString("address");
                    String description = obj.getString("description");
                    String type = obj.getString("serviceType");
                    String budget = obj.getString("budget");
                    Request rq = new Request("no title", description, type, address, budget);
                    requests.add(rq);
                }
            }
            return requests;
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
        return null;
    }

}