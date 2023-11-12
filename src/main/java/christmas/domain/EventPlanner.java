package christmas.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EventPlanner {
    private Calendar date;
    private Map<String, Integer> order;

    private final Map<String, Integer> menuCost;

    private final List<String> dessert = List.of("초코케이크", "아이스크림");

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

    public int getWeekendBenefit() {
        List<Integer> weekend = List.of(Calendar.SATURDAY, Calendar.SUNDAY);
        if (weekend.contains(date.get(Calendar.DAY_OF_WEEK))) {
            int numberDessert = order.entrySet().stream()
                    .filter(menu -> dessert.contains(menu.getKey()))
                    .mapToInt(Entry::getValue)
                    .sum();
            return numberDessert * 2023;
        }
        return 0;
    }

    public List<BenefitItem> getBenefitItems() {
        List<BenefitItem> items = new ArrayList<>();

        items.add(new BenefitItem("주말 할인", getWeekendBenefit()));

        return items;
    }

    public int getBenefitTotal() {
        return getBenefitItems().stream()
                .mapToInt(BenefitItem::amount)
                .sum();
    }

    public int getTotalCostDiscounted() {
        return getTotalCost() - getBenefitTotal();
    }

    public String getBadge() {
        if (getBenefitTotal() >= 20000) {
            return "산타";
        }
        if (getBenefitTotal() >= 10000) {
            return "트리";
        }
        if (getBenefitTotal() >= 5000) {
            return "별";
        }
        return "없음";
    }

}
