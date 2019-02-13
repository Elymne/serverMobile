package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.entities.User;

/**
 * @author Elymne
 */
public class UserCheck {

    public static boolean checkUpdate(User user, User userUpdate) {
        boolean result = true;
        if(user.getPseudo().equals(userUpdate.getPseudo())
                || user.getEmail().equals(userUpdate.getEmail())
                || user.getPseudo().isBlank()
                || user.getEmail().isBlank()
                || user.getPassword().isBlank()
                || user.getPassword().length() < 5){
            result = false;
        }
        return result;
    }

    public static boolean checkInsert(User user) {
        boolean result = true;
        if (user.getPseudo().isBlank()
                || user.getEmail().isBlank()
                || user.getPassword().isBlank()
                || user.getPassword().length() < 5) {
            result = false;
        }
        return result;
    }
}
