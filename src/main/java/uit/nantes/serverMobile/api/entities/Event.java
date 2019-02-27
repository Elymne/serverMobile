package uit.nantes.serverMobile.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
@Entity
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idEvent", nullable = false)
    private String id;

    private String title;
    private LocalDate date;
    private Boolean active;
    private String place;
    private String description;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.ALL})
    @JsonIgnore
    private List<Expense> expenseList;

    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_event",
            joinColumns = {
                @JoinColumn(name = "event_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "user_id")})
    private List<User> userList;

    @ManyToOne
    private User user;

    public Event() {
        super();
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public User getUser() {
        return user;
    }

    public void createId() {
        this.id = UUID.randomUUID().toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", title=" + title + ", date=" + date + ", active=" + active + ", user=" + user + '}';
    }

}
