package uit.nantes.serverMobile.application.controller.util;

import org.json.JSONException;
import org.json.JSONObject;
import uit.nantes.serverMobile.api.entities.User;

/**
 * @author Elymne
 */
public class ToJson {

    public static JSONObject userToJSON(User user) throws JSONException {
        JSONObject response = new JSONObject();
        if (!user.equals(null)) {
            response.put("ok", true);
            response.put("pseudo", user.getPseudo());
            response.put("password", user.getPassword());
            response.put("email", user.getEmail());
        } else {
            response.put("ok", false);
            response.put("message", "user non existant");
        }
        return response;
    }

}
