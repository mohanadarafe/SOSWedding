package com.example.soswedding.ui.all_requests;

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
}