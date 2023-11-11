package christmas.domain;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EventPlanner {
    private Calendar date;
    private Map<String, Integer> order;

    private final Map<String, Integer> menuCost;

    public EventPlanner() {
        menuCost = new HashMap<>();
        menuCost.put("타파스", 5500);
        menuCost.put("제로콜라", 3000);
    }

    public void setDate(Calendar date) {
        if (date == null) {
            throw new IllegalArgumentException("[ERROR] date is null");
        }
        this.date = date;
    }

    public void setOrder(Map<String, Integer> order) {
        this.order = order;
    }

    public int getTotalCost() {
        return order.keySet().stream()
                .mapToInt(menu -> order.get(menu) * menuCost.get(menu))
                .sum();
    }

}
