package uit.nantes.serverMobile.api.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
@Entity
public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private double amount;
    private String wording;

    @ManyToOne
    private Event event;

    @ManyToOne
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

    public void createId() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Expense{" + "id=" + id + ", amount=" + amount + ", wording=" + wording + '}';
    }

}
