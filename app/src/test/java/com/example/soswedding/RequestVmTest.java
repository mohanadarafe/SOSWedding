package com.example.soswedding;
import com.example.soswedding.model.User;
import com.example.soswedding.ui.Request.RequestViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequestVmTest {

    private RequestViewModel viewModel;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        viewModel = new RequestViewModel();
    }


    @Test
    public void isUserACoupleFalse(){
        User user = new User(2,"UUID","Hampic","Baboyan","hampic.baboyan@hotmail.com","PROVIDER","514514514");
        Boolean result = viewModel.verifyCoupleUserType(user);
        assertFalse(result);
    }

    @Test
    public void isUserACoupleTrue(){
        User user = new User(2,"UUID","Hampic","Baboyan","hampic.baboyan@hotmail.com","COUPLE","514514514");
        Boolean result = viewModel.verifyCoupleUserType(user);
        assertTrue(result);
    }

    @Test
    public void isInputValidTrue(){
        String input = "23";
        Boolean result = viewModel.userInputValidation(input);
        assertTrue(result);
    }

    @Test
    public void isInputValidFalse(){
        String input = "";
        Boolean result = viewModel.userInputValidation(input);
        assertFalse(result);
    }




//I dont think i need this
    public String mockJsonBid(){
        JSONArray array = new JSONArray();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("amount", 400);
            jsonBody.put("message", "Hello WORLD");
            jsonBody.put("status", "PENDING");
            jsonBody.put("providerUuid", "ProviderUUID");
            jsonBody.put("coupleUuid","CoupleUUID");
            jsonBody.put("requestId", 1);
            jsonBody.put("requestTitle","Baking Cake");
            jsonBody.put("companyName","SOSWedding");
            array.put(jsonBody);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonArray = array.toString();
        return jsonArray;
    }
}
