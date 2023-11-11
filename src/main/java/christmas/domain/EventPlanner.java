package christmas.domain;

import java.util.Date;
import java.util.Map;

public class EventPlanner {
    private Date date;
    private Map<String, Integer> order;

    public EventPlanner() {
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("[ERROR] date is null");
        }
        this.date = date;
    }

    public void setOrder(Map<String, Integer> order) {
        this.order = order;
    }
}
