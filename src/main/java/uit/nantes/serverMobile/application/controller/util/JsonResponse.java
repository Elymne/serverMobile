package uit.nantes.serverMobile.application.controller.util;

import org.json.JSONException;
import org.json.JSONObject;
import uit.nantes.serverMobile.api.entities.Expense;
import uit.nantes.serverMobile.api.entities.User;

/**
 * @author Djurdjevic Sacha
 */
public class JsonResponse {
    
    final static String UPDATE_ERROR_MESSAGE = "Les valeurs n'ont pas étés modifiés";
    final static String UPDATE_OK_MESSAGE = "Les valeurs ont bien étés changés)";
    
    final static String INSERT_ERROR_MESSAGE = "L'insertion impossible";
    final static String INSERT_OK_MESSAGE = "Insertion faite";
    
    final static String DELETE_ERROR_MESSAGE = "Suppression non effectué";
    final static String DELETE_OK_MESSAGE = "Suppression effectué";

    public static JSONObject getJsonUserResponse(User user) throws JSONException {
        JSONObject response = new JSONObject();
        if (user.doesExist()) {
            response.put("ok", true);
            response.put("id", user.getId());
            response.put("pseudo", user.getPseudo());
            response.put("password", user.getPassword());
            response.put("email", user.getEmail());
        } else {
            response.put("ok", false);
            response.put("message", "user non existant");
        }
        return response;
    }
    
    public static JSONObject getJsonExpenseResponse(Expense expense) throws JSONException {
        JSONObject response = new JSONObject();
        if (expense.doesExist()) {
            response.put("ok", true);
            response.put("id", expense.getId());
            response.put("amount", expense.getAmount());
            response.put("wording", expense.getWording());
        } else {
            response.put("ok", false);
            response.put("message", "dépense non existante");
        }
        return response;
    }
    
    public static JSONObject insertJsonResponse(boolean value) throws JSONException{
        JSONObject response = new JSONObject();
        
        if(value){
            response.put("ok", true);
            response.put("message", INSERT_OK_MESSAGE);
        }else{
            response.put("ok", false);
            response.put("message", INSERT_ERROR_MESSAGE);
        }
        
        return response;
    }
    
    public static JSONObject updateJsonResponse(boolean value) throws JSONException{
        JSONObject response = new JSONObject();
        
        if(value){
            response.put("ok", true);
            response.put("message", UPDATE_OK_MESSAGE);
        }else{
            response.put("ok", false);
            response.put("message", UPDATE_ERROR_MESSAGE);
        }
        
        return response;
    }
    
    public static JSONObject deleteJsonResponse(boolean value) throws JSONException{
        JSONObject response = new JSONObject();
        
        if(value){
            response.put("ok", true);
            response.put("message", DELETE_OK_MESSAGE);
        }else{
            response.put("ok", false);
            response.put("message", DELETE_ERROR_MESSAGE);
        }
        
        return response;
    }

}
