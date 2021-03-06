package uit.nantes.serverMobile.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idUser", nullable = false)
    private String id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Expense> expenseList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private List<Event> eventAdminList;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "userList")
    @JsonIgnore
    private List<Event> eventList;

    public User() {
        super();
    }

    public String getId() {
        return id;
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

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public List<Event> getEventAdminList() {
        return eventAdminList;
    }

    public void setEventAdminList(List<Event> eventAdminList) {
        this.eventAdminList = eventAdminList;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public void createId() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", speudo=" + username + ", email=" + email + ", password=" + password + '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
