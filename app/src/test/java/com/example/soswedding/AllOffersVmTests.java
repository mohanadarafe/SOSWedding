package com.example.soswedding;

import com.example.soswedding.model.Offer;
import com.example.soswedding.model.Singleton;
import com.example.soswedding.ui.allOffers.AllOffersViewModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AllOffersVmTests {

    private AllOffersViewModel viewModel;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        viewModel = new AllOffersViewModel();
    }

    @Test
    public void isUserACoupleFalse(){
        Boolean result = viewModel.isUserACouple("PROVIDER");
        assertFalse(result);
    }
    @Test
    public void isUserACoupleTrue(){
        Boolean result = viewModel.isUserACouple("COUPLE");
        assertTrue(result);
    }

    @Test
    public void isUserAProviderFalse(){
        Boolean result = viewModel.isUserAProvider("COUPLE");
        assertFalse(result);
    }
    @Test
    public void isUserAProviderTrue(){
        Boolean result = viewModel.isUserAProvider("PROVIDER");
        assertTrue(result);
    }

    @Test
    public void getOffersObjectForCoupleNullTest(){

        String json = mockJsonBid();
        Singleton.getInstance().setType("PROVIDER");
        Singleton.getInstance().setUuid("j5wrx0HNFEYeARGJPkSTCWjgNF61");
        ArrayList<Offer> rq = (ArrayList<Offer>) viewModel.getListOfAllOffers(json);
        assertEquals(rq.get(0).getId(), Long.parseLong("1"));
        assertEquals(String.valueOf(rq.get(0).getAmount()), "30.0");
        assertEquals(rq.get(0).getMessage(), "testMessage");
        assertEquals(rq.get(0).getStatus(), "PENDING");
        assertEquals(rq.get(0).getCoupleUuid(), "j5wrx0HNFEYeARGJPkSTCWjgNF62"); //Added
        assertEquals(rq.get(0).getRequestTitle(), "test");
    }

    @Test
    public void getDisplayableOfferCoupleNoOffers(){

        Singleton.getInstance().setType("COUPLE");
        Singleton.getInstance().setUuid("0");
        ArrayList<Offer> offers = new ArrayList<>();
        offers = (ArrayList<Offer>) viewModel.getDisplayableRequests(mockJsonBid());
        assertTrue(offers.isEmpty());
    }

    @Test
    public void getDisplayableOfferProviderNoOffers(){

        Singleton.getInstance().setType("PROVIDER");
        Singleton.getInstance().setUuid("1");
        ArrayList<Offer> offers = new ArrayList<>();
        offers = (ArrayList<Offer>) viewModel.getDisplayableRequests(mockJsonBid());
        System.out.println(offers);
        assertTrue(offers.isEmpty());
    }

    @Test
    public void getDisplayableOfferCoupleOneOffer(){

        Singleton.getInstance().setType("COUPLE");
        Singleton.getInstance().setUuid("j5wrx0HNFEYeARGJPkSTCWjgNF62");
        ArrayList<Offer> offers = new ArrayList<>();
        offers = (ArrayList<Offer>) viewModel.getDisplayableRequests(mockJsonBid());
        assertFalse(offers.isEmpty());
    }

    @Test
    public void getDisplayableOfferProviderOneOffer(){

        Singleton.getInstance().setType("PROVIDER");
        Singleton.getInstance().setUuid("j5wrx0HNFEYeARGJPkSTCWjgNF62");
        ArrayList<Offer> offers = new ArrayList<>();
        offers = (ArrayList<Offer>) viewModel.getDisplayableRequests(mockJsonBid());
        assertFalse(offers.isEmpty());
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
            item.put("providerUuid", "j5wrx0HNFEYeARGJPkSTCWjgNF61");
            item.put("requestTitle", "test");
            item.put("requestId", "21");
            item.put("companyName", "ALAINISACHAMPION");
            array.put(item);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonArray = array.toString();
        return jsonArray;
    }


}
