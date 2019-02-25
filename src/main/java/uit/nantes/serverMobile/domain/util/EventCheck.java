package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.pojo.EventPojo;

public class EventCheck {

    public static boolean checkUpdate(EventPojo eventPojo) {
        boolean result = true;
        if(eventPojo.getTitle().isBlank()
                || eventPojo.getPlace().isBlank()){
            result = false;
        }
        return result;
    }

    public static boolean checkInsert(EventPojo eventPojo) {
        boolean result = true;
        if(eventPojo.getTitle().isBlank()
                || eventPojo.getPlace().isBlank()){
            result = false;
        }
        return result;
    }

}
