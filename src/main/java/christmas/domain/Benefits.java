package christmas.domain;

import christmas.dto.BenefitItem;
import christmas.dto.MenuItem;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Benefits {
    private final LocalDate date;
    private final Map<Menu, Integer> order;

    private final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);

    public Benefits(LocalDate date, Map<Menu, Integer> order) {
        this.date = date;
        this.order = order;
    }

    private int getTotalCost() {
        return order.keySet().stream()
                .mapToInt(menu -> menu.getCost(order.get(menu)))
                .sum();
    }

    public int getWeekendBenefit() {
        List<DayOfWeek> weekend = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        if (weekend.contains(date.getDayOfWeek())) {
            int numberDessert = order.entrySet().stream()
                    .filter(menuItem -> menuItem.getKey().isTypeOf(MenuType.MAIN_DISH))
                    .mapToInt(Entry::getValue)
                    .sum();
            return numberDessert * 2023;
        }
        return 0;
    }

    public int getWeekdayBenefit() {
        List<DayOfWeek> weekday = List.of(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY);
        if (weekday.contains(date.getDayOfWeek())) {
            int numberDessert = order.entrySet().stream()
                    .filter(menuItem -> menuItem.getKey().isTypeOf(MenuType.DESSERT))
                    .mapToInt(Entry::getValue)
                    .sum();
            return numberDessert * 2023;
        }
        return 0;
    }

    public List<MenuItem> getFreebieItems() {
        if (getTotalCost() >= 120000) {
            return List.of(new MenuItem(Menu.CHAMPAGNE, 1));
        }
        return List.of();
    }

    public int getFreebieBenefit() {
        return getFreebieItems().stream()
                .mapToInt(item -> item.menu().getCost(item.quantity()))
                .sum();
    }

    public int getChristmasBenefit() {
        int days = (int) ChronoPeriod.between(date, CHRISTMAS).get(ChronoUnit.DAYS);
        if (days >= 0) {
            return 3400 - days * 100;
        }
        return 0;
    }

    public int getSpecialBenefit() {
        List<LocalDate> starDay = List.of(
                LocalDate.of(2023, 12, 3),
                LocalDate.of(2023, 12, 10),
                LocalDate.of(2023, 12, 17),
                LocalDate.of(2023, 12, 24),
                LocalDate.of(2023, 12, 25),
                LocalDate.of(2023, 12, 31)
        );
        if (starDay.contains(date)) {
            return 1000;
        }
        return 0;
    }

    private void addBenefitItem(List<BenefitItem> items, String name, int amount) {
        if (amount != 0) {
            items.add(new BenefitItem(name, amount));
        }
    }

    public List<BenefitItem> getBenefitItems() {
        List<BenefitItem> items = new ArrayList<>();

        if (getTotalCost() < 10000) {
            return items;
        }

        addBenefitItem(items, "평일 할인", getWeekdayBenefit());
        addBenefitItem(items, "주말 할인", getWeekendBenefit());
        addBenefitItem(items, "증정 이벤트", getFreebieBenefit());
        addBenefitItem(items, "크리스마스 디데이 할인", getChristmasBenefit());
        addBenefitItem(items, "특별 할인", getSpecialBenefit());

        return items;
    }
}
