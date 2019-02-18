package uit.nantes.serverMobile.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * @author Djurdjevic Sacha
 */
@Entity
public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private double amount;
    private String wording;
    @Transient
    private boolean exist;

    private Event idEvent;
    private User idUser;

    @ManyToOne
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JsonIgnore
    private User user;

    public Expense() {
        super();
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public Event getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Event idEvent) {
        this.idEvent = idEvent;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void exist() {
        this.exist = true;
    }

    public void notExist() {
        this.exist = false;
    }

    public boolean doesExist() {
        boolean result = false;
        if (this.exist) {
            result = true;
        }
        return result;
    }

    public void createId() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Expense{" + "id=" + id + ", amount=" + amount + ", wording=" + wording + '}';
    }

}
