package com.ing.utc;

import com.ing.application.Application;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class test1Test {

    @Test(timeout = 9000)
    public void timeoutTest() {
        Application.getThirdPartyATMList();
    }

    @Test
    public void validMessageTest() {

        try {
            assertTrue(isJSONValid(Application.getMessage().split("\\n")[1]));
        } catch(Exception e) {
            fail(e.getMessage());
        }
    }

    public boolean isJSONValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException ex) {
            try {
                new JSONArray(json);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

}