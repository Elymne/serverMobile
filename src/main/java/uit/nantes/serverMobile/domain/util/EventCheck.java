package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.entities.Event;

public class EventCheck {

    public static boolean checkUpdate(Event event) {
        boolean result = true;
        if(event.getTitle().isBlank()
                || event.getPlace().isBlank()){
            result = false;
        }
        return result;
    }

    public static boolean checkInsert(Event event) {
        boolean result = true;
        if(event.getTitle().isBlank()
                || event.getPlace().isBlank()){
            result = false;
        }
        return result;
    }

}
