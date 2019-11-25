package com.example.soswedding.ui.CreateRequest;

import android.content.Context;

import com.example.soswedding.model.Request;
import com.example.soswedding.service.RequestsService;

import androidx.lifecycle.ViewModel;

public class CreateRequestViewModel extends ViewModel {

    public CreateRequestViewModel(){

    }

    public void createRequest(Context context, Request request){
        RequestsService.postRequest(context, request);
    }
}
