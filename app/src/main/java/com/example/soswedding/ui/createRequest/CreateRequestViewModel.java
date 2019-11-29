package com.example.soswedding.ui.createRequest;

import android.content.Context;

import com.example.soswedding.model.Request;
import com.example.soswedding.service.RequestsService;

import androidx.lifecycle.ViewModel;

public class CreateRequestViewModel extends ViewModel {
    public void createRequest(Context context, Request request){
        RequestsService.postRequest(context, request);
    }
}
