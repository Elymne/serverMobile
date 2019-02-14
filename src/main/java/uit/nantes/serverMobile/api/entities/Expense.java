package uit.nantes.serverMobile.api.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    private boolean exist;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User user;

    public Expense() {
        super();
    }

    public Expense(double amount, String wording) {
        this.amount = amount;
        this.wording = wording;;
    }

    public Event getEvent() {
        return event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getId() {
        return this.id;
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
