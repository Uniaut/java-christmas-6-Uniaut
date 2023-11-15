package christmas.domain;

import christmas.constant.ExceptionMessage;
import christmas.dto.BenefitItem;
import christmas.dto.MenuItem;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPlanner {
    private LocalDate date;
    private Map<Menu, Integer> order;

    private final LocalDate DEC_01 = LocalDate.of(2023, 12, 1);
    private final LocalDate DEC_31 = LocalDate.of(2023, 12, 31);

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
                .allMatch(menuItem -> menuItem.menu().isTypeOf(MenuType.BEVERAGE));
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

        long distinctSize = order.stream()
                .map(MenuItem::menu)
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
                (map, item) -> map.put(item.menu(), item.quantity()),
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
                .mapToInt(menu -> menu.getCost(order.get(menu)))
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
