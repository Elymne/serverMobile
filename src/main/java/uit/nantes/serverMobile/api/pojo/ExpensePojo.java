package uit.nantes.serverMobile.api.pojo;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class ExpensePojo {

    private final double amount;
    private final String wording;
    private final String userId;
    private final String eventId;

    public ExpensePojo(double amount, String wording, String userId, String eventId) {
        this.amount = amount;
        this.wording = wording;
        this.userId = userId;
        this.eventId = eventId;
    }

    public double getAmount() {
        return amount;
    }

    public String getWording() {
        return wording;
    }

    public String getUserId() {
        return userId;
    }

    public String getEventId() {
        return eventId;
    }

}
