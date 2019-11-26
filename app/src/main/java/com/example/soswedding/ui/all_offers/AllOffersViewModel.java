package com.example.soswedding.ui.all_offers;

import com.example.soswedding.model.Offer;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllOffersViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public AllOffersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is all offer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public List<Offer> getMockupList() {
        Offer of = new Offer("test","PENDING","testMessage", "Juliet&Choco", 11, 100);
        List<Offer> list = new ArrayList<>();
        for(int i =0; i < 15; i++)
            list.add(of);
        return list;
    }
}
