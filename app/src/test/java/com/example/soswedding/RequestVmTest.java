package com.example.soswedding;
import com.example.soswedding.model.Offer;
import com.example.soswedding.model.Request;
import com.example.soswedding.model.User;
import com.example.soswedding.ui.Request.RequestViewModel;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
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
    public void isUserACoupleFalse() {
        User user = new User(2, "UUID", "Hampic", "Baboyan", "hampic.baboyan@hotmail.com", "PROVIDER", "514514514");
        Boolean result = viewModel.verifyCoupleUserType(user);
        assertFalse(result);
    }

    @Test
    public void isUserACoupleTrue() {
        User user = new User(2, "UUID", "Hampic", "Baboyan", "hampic.baboyan@hotmail.com", "COUPLE", "514514514");
        Boolean result = viewModel.verifyCoupleUserType(user);
        assertTrue(result);
    }

    @Test
    public void isInputValidTrue() {
        String input = "23";
        Boolean result = viewModel.userInputValidation(input);
        assertTrue(result);
    }

    @Test
    public void isInputValidFalse() {
        String input = "";
        Boolean result = viewModel.userInputValidation(input);
        assertFalse(result);
    }

    @Test
    public void createOfferObject() {
        String amount = "220";
        User userProvider = new User(2, "providerUuid", "Hampic", "Baboyan", "hampic.baboyan@hotmail.com", "PROVIDER", "514514514");
        Request request = new Request(1, "Wedding Cake Urgent", "I want a chocolate cake for my wedding by tmr morning", "BAKER", "hampic.baboyan@hotmail.com", 200, "CoupleUuid", "PENDING");
        JSONObject jsonObject = viewModel.createBidObject(amount, request, userProvider);
        Offer offer = new Offer();
        try {
            offer.setAmount(jsonObject.getDouble("amount"));
            offer.setMessage(jsonObject.getString("message"));
            offer.setCoupleUuid(jsonObject.getString("coupleUuid"));
            offer.setProviderUuid(jsonObject.getString("providerUuid"));
            offer.setStatus(jsonObject.getString("status"));
            offer.setRequestId(jsonObject.getLong("requestId"));
            offer.setRequestTitle(jsonObject.getString("requestTitle"));
            offer.setCompanyName(jsonObject.getString("companyName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assertEquals(String.valueOf(offer.getAmount()), "220.0");
        assertEquals(offer.getMessage(), "Hello WORLD");
        assertEquals(offer.getCompanyName(), "SOSWedding");
        assertEquals(offer.getCoupleUuid(), "CoupleUuid");
        assertEquals(offer.getProviderUuid(), "providerUuid");
        assertEquals(String.valueOf(offer.getRequestId()), "1");
        assertEquals(offer.getRequestTitle(), "Wedding Cake Urgent");
        assertEquals(offer.getStatus(), "PENDING");
    }
}