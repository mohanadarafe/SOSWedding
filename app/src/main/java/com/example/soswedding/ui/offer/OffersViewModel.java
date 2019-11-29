package com.example.soswedding.ui.offer;
import android.content.Context;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.model.Request;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.soswedding.service.BidService.acceptBid;
import static com.example.soswedding.service.BidService.declineBid;
public class OffersViewModel extends ViewModel {

    public OffersViewModel() {
    }
    public void acceptBidModel(Context context,long requestID, long bidId) {
        acceptBid(context, requestID, bidId, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("My App", "SUCCESS!");
            }
        });
    }

    public void declineBidModel(Context context, long bidId) {
        declineBid(context, bidId, new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Log.e("My App", "SUCCESS!");
            }
        });
    }


    public Request getRequestObjectFromString(String result) throws  JSONException {
            JSONObject jsonBody = new JSONObject(result);
            String address      = jsonBody.getString("address");
            String description  = jsonBody.getString("description");
            String type         = jsonBody.getString("serviceType");
            double budget       = Double.parseDouble(jsonBody.getString("budget"));
            String title        = jsonBody.getString("title");
            long id             = jsonBody.getLong("id");
            String coupleUuid   = jsonBody.getString("coupleUuid");
            String status       = jsonBody.getString("status");
            return new Request(id, title, description, type, address, budget,coupleUuid,status);
    }

}