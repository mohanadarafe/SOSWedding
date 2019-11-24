package com.example.soswedding.ui.SignIn;

import android.util.Log;

import com.example.soswedding.model.Singleton;
import com.example.soswedding.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.ViewModel;

public class SignInViewModel extends ViewModel {

    public void getUserInfo(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            Singleton.getInstance().setId(obj.getLong("id"));
            Singleton.getInstance().setUuid(obj.getString("uuid"));
            Singleton.getInstance().setFirstName(obj.getString("firstName"));
            Singleton.getInstance().setLastName(obj.getString("lastName"));
            Singleton.getInstance().setPhoneNumber(obj.getString("phoneNumber"));
            Singleton.getInstance().setType(obj.getString("type"));
            Singleton.getInstance().setCompanyName(obj.getString("companyName"));
            Singleton.getInstance().setServiceProvided(obj.getString("serviceType"));
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }
    }
}