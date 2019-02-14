package uit.nantes.serverMobile.api.entities;

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
import javax.persistence.Transient;

/**
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
    @Transient
    private boolean exist;
    @Transient
    private String pseudoUser;

    @OneToMany(mappedBy = "event")
    private List<Expense> expenseList;

    @ManyToMany(cascade = {
        CascadeType.MERGE,
        CascadeType.PERSIST},
            fetch = FetchType.LAZY)
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

    public Event(String title, LocalDate date, String place, String pseudoUser) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.pseudoUser = pseudoUser;
        this.date = date;
        this.active = true;
        this.place = place;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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

    public User getUser() {
        return user;
    }
    
    public String getPseudoUser(){
        return pseudoUser;
    }

    public void exist() {
        this.exist = true;
    }

    public void notExist() {
        this.exist = false;
    }

    public boolean doExist() {
        boolean result = false;
        if (this.exist) {
            result = true;
        }
        return result;
    }
    
    public void createId(){
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", title=" + title + ", date=" + date + ", active=" + active + ", user=" + user + '}';
    }

}
