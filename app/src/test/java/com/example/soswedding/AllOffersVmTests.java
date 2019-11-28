package com.example.soswedding;

import com.example.soswedding.model.Offer;
import com.example.soswedding.ui.all_offers.AllOffersViewModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AllOffersVmTests {

    private AllOffersViewModel viewModel;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        viewModel = new AllOffersViewModel();
    }

    @Test
    public void getOffersObjectForCoupleNullTest(){
        String json = mockJsonBid();
        ArrayList<Offer> rq = (ArrayList<Offer>) viewModel.getListOfAllOffers(json);
        assertEquals(String.valueOf(rq.get(0).getId()), "1");
        assertEquals(String.valueOf(rq.get(0).getAmount()), "30.0");
        assertEquals(rq.get(0).getMessage(), "testMessage");
        assertEquals(rq.get(0).getStatus(), "PENDING");
        assertEquals(rq.get(0).getCoupleUuid(), "j5wrx0HNFEYeARGJPkSTCWjgNF62"); //Added
        assertEquals(rq.get(0).getRequestTitle(), "test");

    }

    public String mockJsonBid(){

        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();
        try {
            item.put("id", "1");
            item.put("amount", "30.0");
            item.put("message", "testMessage");
            item.put("status", "PENDING");
            item.put("coupleUuid", "j5wrx0HNFEYeARGJPkSTCWjgNF62");
            item.put("requestTitle", "test");
            array.put(item);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonArray = array.toString();

        return jsonArray;

    }

}
