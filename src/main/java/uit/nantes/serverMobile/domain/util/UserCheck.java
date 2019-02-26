package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.pojo.UserPojo;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class UserCheck {

    public static boolean checkUpdate(UserPojo userPojo) {
        boolean result = true;
        if(userPojo.getPseudo().isEmpty()
                || userPojo.getEmail().isEmpty()
                || userPojo.getPassword().isEmpty()
                || userPojo.getPassword().length() < 5){
            result = false;
        }
        return result;
    }

    public static boolean checkInsert(UserPojo userPojo) {
        boolean result = true;
        if (userPojo.getPseudo().isEmpty()
                || userPojo.getEmail().isEmpty()
                || userPojo.getPassword().isEmpty()
                || userPojo.getPassword().length() < 5) {
            result = false;
        }
        return result;
    }
}
