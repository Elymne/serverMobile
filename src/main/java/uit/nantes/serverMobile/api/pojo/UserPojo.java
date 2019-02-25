package uit.nantes.serverMobile.api.pojo;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class UserPojo {

    private final String pseudo;
    private final String email;
    private final String password;

    public UserPojo(String pseudo, String email, String password) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
