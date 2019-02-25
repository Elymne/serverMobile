package uit.nantes.serverMobile.api.pojo;

public class UserPojo {

    private String pseudo;
    private String email;
    private String password;

    public UserPojo(String pseudo, String email, String password) {
        this.pseudo = pseudo;
        this.email = email;
        this.password = password;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
