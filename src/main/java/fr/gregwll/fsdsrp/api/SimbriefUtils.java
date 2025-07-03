package fr.gregwll.fsdsrp.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import netscape.javascript.JSObject;

public class SimbriefUtils {

    public static String getflightnum(String username) {
        try {
            String json = SimbriefAPI.fetchFlightData(username);
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

            JsonObject general = obj.getAsJsonObject("general");
            return general.get("flight_number").getAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String getUserID(String username) {
        try {
            String json = SimbriefAPI.fetchFlightData(username);
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

            JsonObject general = obj.getAsJsonObject("params");
            return general.get("user_id").getAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
