package com.example.soswedding;
import android.util.Log;
import com.example.soswedding.model.Singleton;
import com.example.soswedding.model.Request;
import com.example.soswedding.ui.all_requests.AllRequestsViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AllRequestsVmTests {

    private AllRequestsViewModel viewModel;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        viewModel = new AllRequestsViewModel();
    }
    @Test
    public void getRequestsObjectForCoupleNullTest(){
        String json = mockJsonRequest();
        ArrayList<Request> rq = (ArrayList<Request>) viewModel.getListOfAllRequests(json);
        assertEquals(rq.get(0).getAddress(), "Montreal");
        assertEquals(rq.get(0).getType(), "DANCER");
        assertEquals(String.valueOf(rq.get(0).getBudget()), "1.0");
        assertEquals(rq.get(0).getTitle(), "test");
        assertEquals(rq.get(0).getDescription(), "test");


    }
    @Test
    public void getDisplayableRequestsTestCoupleNoRequests(){

        Singleton.getInstance().setType("COUPLE");
        Singleton.getInstance().setUuid("0");
        ArrayList<Request> requests = new ArrayList<>();
        requests = (ArrayList<Request>) viewModel.getDisplayableRequests(mockJsonRequest());
        assertTrue(requests.isEmpty());
    }
    @Test
    public void getDisplayableRequestsTestCoupleOneRequests(){

        Singleton.getInstance().setType("COUPLE");
        Singleton.getInstance().setUuid("1");
        ArrayList<Request> requests;
        requests = (ArrayList<Request>) viewModel.getDisplayableRequests(mockJsonRequest());
        assertFalse(requests.isEmpty());
    }
    @Test
    public void getDisplayableRequestsTestProviderOneRequests(){
        Singleton.getInstance().setType("PROVIDER");
        Singleton.getInstance().setUuid("2");
        ArrayList<Request> requests;
        requests = (ArrayList<Request>) viewModel.getDisplayableRequests(mockJsonRequest());
        assertFalse(requests.isEmpty());
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
    public void isRequestFromUserTrue(){
        Singleton.getInstance().setType("COUPLE");
        Singleton.getInstance().setUuid("0");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(mockJsonRequest());
            assertFalse(viewModel.isRequestFromUser(jsonObject));
        }catch (JSONException err){
            Log.d("Error", err.toString());
        }

    }
    @Test
    public void isRequestFromUserFalse(){
        Singleton.getInstance().setType("COUPLE");
        Singleton.getInstance().setUuid("1");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(mockJsonRequest());
            assertFalse(viewModel.isRequestFromUser(jsonObject));
        }catch (JSONException err){
            Log.d("Error", err.toString());
        }

    }
    public String mockJsonRequest(){
        JSONArray array = new JSONArray();
        JSONObject item = new JSONObject();
        try {
            item.put("address", "Montreal");
            item.put("description", "test");
            item.put("serviceType", "DANCER");
            item.put("budget", "1.0");
            item.put("title", "test");
            item.put("coupleUuid", "1");
            item.put("id", "10");
            array.put(item);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonArray = array.toString();

        return jsonArray;
    }
}
