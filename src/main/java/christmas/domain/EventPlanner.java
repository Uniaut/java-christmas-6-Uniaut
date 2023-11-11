package christmas.domain;

import java.util.Date;

public class EventPlanner {
    private Date date;
    public EventPlanner() {
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("[ERROR] date is null");
        }
        this.date = date;
    }
}
