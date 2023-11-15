package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Benefits {
    private final LocalDate date;
    private final Map<String, Integer> order;

    private final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);
    private final List<String> appetizer = List.of("타파스", "양송이수프", "시저샐러드");
    private final List<String> dessert = List.of("초코케이크", "아이스크림");
    private final List<String> mainDish = List.of("티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타");

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

    public Benefits(LocalDate date, Map<String, Integer> order) {
        this.date = date;
        this.order = order;
    }

    private int getTotalCost() {
        return order.keySet().stream()
                .mapToInt(menu -> order.get(menu) * menuCost.get(menu))
                .sum();
    }

    public int getWeekendBenefit() {
        List<DayOfWeek> weekend = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
        if (weekend.contains(date.getDayOfWeek())) {
            int numberDessert = order.entrySet().stream()
                    .filter(menu -> mainDish.contains(menu.getKey()))
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

    public List<BenefitItem> getBenefitItems() {
        List<BenefitItem> items = new ArrayList<>();

        if (getTotalCost() < 10000) {
            return items;
        }

        BenefitItem weekday = new BenefitItem("평일 할인", getWeekdayBenefit());
        if (weekday.amount() != 0) {
            items.add(weekday);
        }
        BenefitItem weekend = new BenefitItem("주말 할인", getWeekendBenefit());
        if (weekend.amount() != 0) {
            items.add(weekend);
        }
        BenefitItem freebie = new BenefitItem("증정 이벤트", getFreebieBenefit());
        if (freebie.amount() != 0) {
            items.add(freebie);
        }
        BenefitItem christmas = new BenefitItem("크리스마스 디데이 할인", getChristmasBenefit());
        if (christmas.amount() != 0) {
            items.add(christmas);
        }
        BenefitItem special = new BenefitItem("특별 할인", getSpecialBenefit());
        if (special.amount() != 0) {
            items.add(special);
        }

        return items;
    }
}
