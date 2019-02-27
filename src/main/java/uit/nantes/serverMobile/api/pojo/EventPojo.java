package uit.nantes.serverMobile.api.pojo;

import java.time.LocalDate;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class EventPojo {

    private final String title;
    private final LocalDate date;
    private final String place;
    private final String userId;
    private final String description;

    public EventPojo(String title, LocalDate date, String place, String userId, String description) {
        this.title = title;
        this.date = date;
        this.place = place;
        this.userId = userId;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

}
