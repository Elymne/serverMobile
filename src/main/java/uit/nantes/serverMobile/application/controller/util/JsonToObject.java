package uit.nantes.serverMobile.application.controller.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.domain.UserService;

/**
 * @author Djurdjevic Sacha
 */
public class JsonToObject {

    @Autowired
    static UserService userService;

    public static Event JsonToEvent(JSONObject object) throws JSONException, ParseException {

        String title = object.getString("title");
        String place = object.getString("place");
        String pseudoUser = object.getString("pseudo");

        String dateStr = object.getString("date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = new java.sql.Date(sdf.parse(dateStr).getTime()).toLocalDate();

        return new Event(title, localDate, place, userService.findByPseudo(pseudoUser));

    }

}
