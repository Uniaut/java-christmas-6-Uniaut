package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EventPlanner {
    private LocalDate date;
    private final LocalDate DEC_01 = LocalDate.of(2023, 12, 1);
    private final LocalDate DEC_31 = LocalDate.of(2023, 12, 31);
    private Map<String, Integer> order;

    private final Map<String, Integer> menuCost;

    private final List<String> dessert = List.of("초코케이크", "아이스크림");
    private final List<String> mainDish = List.of("티본스테이크", "바비큐립", "해산물파스타");
    private final List<String> beverage = List.of("제로콜라", "레드와인", "샴페인");

    public EventPlanner() {
        menuCost = new HashMap<>();
        menuCost.put("타파스", 5500);
        menuCost.put("제로콜라", 3000);
        menuCost.put("초코케이크", 15000);
        menuCost.put("아이스크림", 5000);
        menuCost.put("티본스테이크", 55000);
        menuCost.put("바비큐립", 54000);
        menuCost.put("해산물파스타", 35000);
        menuCost.put("샴페인", 25000);
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("[ERROR] date is null");
        }
        if (date.isBefore(DEC_01) || date.isAfter(DEC_31)) {
            throw new IllegalArgumentException("[ERROR] date is not in December, 2023");
        }
        this.date = date;
    }

    private void validateOrderComposition(List<MenuItem> order) {
        int numberTotalMenu = order.stream()
                .mapToInt(MenuItem::quantity)
                .sum();
        if (numberTotalMenu > 20) {
            throw new IllegalArgumentException("[ERROR] 한 번에 %d개까지만 주문할 수 있습니다.".formatted(20));
        }

        boolean isOnlyBeverage = order.stream()
                .allMatch(menuItem -> beverage.contains(menuItem.name()));
        if (isOnlyBeverage) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다.");
        }
    }

    private void validateOrder(List<MenuItem> order) {
        if (order == null) {
            throw new IllegalArgumentException("[ERROR] order is null");
        }
        if (order.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] order is empty");
        }

        boolean isNotValidMenu = order.stream()
                .anyMatch(menu -> !menuCost.containsKey(menu.name()));
        if (isNotValidMenu) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해주세요.");
        }

        long distinctSize = order.stream()
                .map(MenuItem::name)
                .distinct()
                .count();
        if (order.size() != distinctSize) {
            throw new IllegalArgumentException("[ERROR] 중복된 주문이 있습니다. 다시 입력해주세요.");
        }

        validateOrderComposition(order);
    }

    public void setOrder(List<MenuItem> order) {
        validateOrder(order);
        this.order = order.stream().collect(
                HashMap::new,
                (map, item) -> map.put(item.name(), item.quantity()),
                HashMap::putAll
        );
    }

    public LocalDate getDate() {
        return date;
    }

    public List<MenuItem> getOrderItems() {
        return order.entrySet().stream()
                .map(entry -> new MenuItem(entry.getKey(), entry.getValue()))
                .toList();
    }

    public int getTotalCost() {
        return order.keySet().stream()
                .mapToInt(menu -> order.get(menu) * menuCost.get(menu))
                .sum();
    }

    public int getWeekendBenefit() {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            int numberDessert = order.entrySet().stream()
                    .filter(menu -> dessert.contains(menu.getKey()))
                    .mapToInt(Entry::getValue)
                    .sum();
            return numberDessert * 2023;
        }
        return 0;
    }

    public List<MenuItem> getFreebieItems() {
        if (getTotalCost() >= 120000) {
            return List.of(new MenuItem("샴페인", 1));
        }
        return List.of();
    }

    public int getFreebieBenefit() {
        return getFreebieItems().stream()
                .mapToInt(item -> item.quantity() * menuCost.get(item.name()))
                .sum();
    }

    public List<BenefitItem> getBenefitItems() {
        List<BenefitItem> items = new ArrayList<>();

        items.add(new BenefitItem("주말 할인", getWeekendBenefit()));
        items.add(new BenefitItem("증정 이벤트", getFreebieBenefit()));

        return items;
    }

    public int getBenefitTotal() {
        return getBenefitItems().stream()
                .mapToInt(BenefitItem::amount)
                .sum();
    }

    public int getTotalCostDiscounted() {
        int totalDiscount = 0;

        for (BenefitItem item : getBenefitItems()) {
            if (item.name().equals("증정 이벤트")) {
                continue;
            }
            totalDiscount += item.amount();
        }

        return getTotalCost() - totalDiscount;
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
