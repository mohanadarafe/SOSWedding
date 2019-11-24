package com.example.soswedding.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.soswedding.Interface.VolleyCallback;
import com.example.soswedding.model.User;
//TODO: Implement Service
public class UserServiceImpl {
    static String user;
    public static String getByUuid(Context context, String uuid, final VolleyCallback callback) {
            String url = "https://soswedding.herokuapp.com/user/"+uuid;
            RequestQueue queue = Volley.newRequestQueue(context);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            callback.onSuccess(response);
                            user  = response;
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            queue.add(stringRequest);
            return user;
    }
}
