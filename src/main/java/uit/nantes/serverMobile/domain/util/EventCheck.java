package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.pojo.EventPojo;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class EventCheck {

    /*
    *  @param eventPojo Pojo de la classe Event
    *  @return true si les informations de la dépense sont valides, false dans le cas contraire.
    */
    public static boolean checkUpdate(EventPojo eventPojo) {
        boolean result = true;
        if(eventPojo.getTitle().isEmpty()
                || eventPojo.getPlace().isEmpty()){
            result = false;
        }
        return result;
    }

    /*
    *  @param eventPojo Pojo de la classe Event
    *  @return true si les informations de la dépense sont valides, false dans le cas contraire.
    */
    public static boolean checkInsert(EventPojo eventPojo) {
        boolean result = true;
        if(eventPojo.getTitle().isEmpty()
                || eventPojo.getPlace().isEmpty()){
            result = false;
        }
        return result;
    }

}
