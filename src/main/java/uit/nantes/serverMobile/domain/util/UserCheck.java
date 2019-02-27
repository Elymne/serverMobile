package uit.nantes.serverMobile.domain.util;

import javax.mail.internet.InternetAddress;

import uit.nantes.serverMobile.api.pojo.UserPojo;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class UserCheck {

    /*
    *  @param UserPojo userPojo Un objet Pojo de la classe User
    *  @return true si les informations de l'utilisateur sont valides, false dans le cas contraire.
    */
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

    /*
    *  @param userPojo UserPojo Un objet Pojo de la classe User
    *  @return true si les informations de l'utilisateur sont valides et si l'email utilisé est valide, false dans le cas contraire.
    */
    public static boolean checkInsert(UserPojo userPojo) {
        boolean result = true;
        if (userPojo.getPseudo().isEmpty()
                || userPojo.getEmail().isEmpty()
                || userPojo.getPassword().isEmpty()
                //|| !isValid(userPojo.getPassword())
                //|| !isEmail(userPojo.getEmail())
                ) {
            result = false;
        }
        return result;
    }
    
    public static boolean isEmail(String email) {
    	boolean result = false;
    	try {			
    		InternetAddress emailCheck = new InternetAddress(email);
    		emailCheck.validate();
    		result = true;
		} catch (Exception e) {
			result = false;
		}
    	return result;
    }
    
    public static boolean isValid(String password) {
		if (password.length() < 8) return false;
		
		int charCount = 0;
		int numCount = 0;
		
		for (int i = 0; i < password.length(); i++) {
			char ch = password.charAt(i);
			if (isNumeric(ch))
				numCount++;
			else if (isLetter(ch))
				charCount++;
			else
				return false;
		}
		return (charCount >= 1 && numCount >= 1);
	}

	public static boolean isLetter(char ch) {
		ch = Character.toUpperCase(ch);
		return (ch >= 'A' && ch <= 'Z');
	}
 
	public static boolean isNumeric(char ch) {
		return (ch >= '0' && ch <= '9');
	}
}
