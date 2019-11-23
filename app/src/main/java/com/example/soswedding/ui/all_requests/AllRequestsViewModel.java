package com.example.soswedding.ui.all_requests;

import com.example.soswedding.model.Request;

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

    public List<Request> getMockupList() {
        Request rq = new Request("test", "test", "test");
        List<Request> list = new ArrayList<>();
        for(int i =0; i < 15; i++)
            list.add(rq);
        return list;
    }

}