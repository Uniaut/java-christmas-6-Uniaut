package christmas.domain;

import christmas.constant.ExceptionMessage;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {
    private LocalDate date;
    private Map<String, Integer> order;

    private final LocalDate DEC_01 = LocalDate.of(2023, 12, 1);
    private final LocalDate DEC_31 = LocalDate.of(2023, 12, 31);
    private final List<String> beverage = List.of("제로콜라", "레드와인", "샴페인");
    private static final Map<String, Integer> menuCost = new HashMap<>();

    static {
        menuCost.put("양송이수프", 6000);
        menuCost.put("타파스", 5500);
        menuCost.put("시저샐러드", 8000);
        menuCost.put("티본스테이크", 55000);
        menuCost.put("바비큐립", 54000);
        menuCost.put("해산물파스타", 35000);
        menuCost.put("크리스마스파스타", 25000);
        menuCost.put("초코케이크", 15000);
        menuCost.put("아이스크림", 5000);
        menuCost.put("제로콜라", 3000);
        menuCost.put("레드와인", 60000);
        menuCost.put("샴페인", 25000);
    }

    public EventPlanner() {
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DATE.getMessage());
        }
        if (date.isBefore(DEC_01) || date.isAfter(DEC_31)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DATE.getMessage());
        }
        this.date = date;
    }

    private void validateOrderComposition(List<MenuItem> order) {
        int numberTotalMenu = order.stream()
                .mapToInt(MenuItem::quantity)
                .sum();
        if (numberTotalMenu > 20) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }

        boolean isOnlyBeverage = order.stream()
                .allMatch(menuItem -> beverage.contains(menuItem.name()));
        if (isOnlyBeverage) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateOrder(List<MenuItem> order) {
        if (order == null) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
        if (order.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }

        boolean isNotValidMenu = order.stream()
                .anyMatch(menu -> !menuCost.containsKey(menu.name()));
        if (isNotValidMenu) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }

        long distinctSize = order.stream()
                .map(MenuItem::name)
                .distinct()
                .count();
        if (order.size() != distinctSize) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
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

    public List<MenuItem> getFreebieItems() {
        Benefits benefits = new Benefits(date, order);
        return benefits.getFreebieItems();
    }

    public List<BenefitItem> getBenefitItems() {
        Benefits benefits = new Benefits(date, order);
        return benefits.getBenefitItems();
    }

    public int getBenefitTotal() {
        return getBenefitItems().stream()
                .mapToInt(BenefitItem::amount)
                .sum();
    }

    public int getTotalCostDiscounted() {
        int totalDiscount = getBenefitItems().stream()
                .filter(item -> item.name().contains("할인"))
                .mapToInt(BenefitItem::amount)
                .sum();

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
