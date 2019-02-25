package uit.nantes.serverMobile.api.pojo;

import java.time.LocalDate;

public class EventPojo {

    private final String title;
    private final LocalDate date;
    private final String place;
    private final String userId;

    public EventPojo(String title, LocalDate date, String place, String userId) {
        this.title = title;
        this.date = date;
        this.place = place;
        this.userId = userId;
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
}
