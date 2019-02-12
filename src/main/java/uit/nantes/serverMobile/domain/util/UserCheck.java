package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.entities.User;

/**
 * @author Elymne
 */
public class UserCheck {

    public static boolean checkUpdate(User user, User userUpdate) {
        boolean result = true;
        if (user.getEmail().equals(userUpdate.getEmail())) {
            if (user.getPassword().equals(userUpdate.getPassword())) {
                if (user.getPseudo().equals(userUpdate.getPseudo())) {
                    result = false;
                }
            }
        }
        return result;
    }

    public static boolean checkInsert(User user, String pseudo, String email) {
        boolean result = true;
        if (user.getEmail() == email 
                || user.getPseudo() == pseudo
                || user.getEmail().isEmpty()
                || user.getEmail().isEmpty()
                || user.getPassword().length() < 5) {
            result = false;
        }

        return result;
    }
}
