package christmas.view;

import christmas.dto.BenefitItem;
import christmas.dto.MenuItem;
import christmas.view.io.IO;
import java.time.LocalDate;
import java.util.List;

public class OutputView {
    private static final String TITLE_WELCOME = "우테코 식당 12월 이벤트 플래너입니다.";
    private static final String INTRO_DATE_FORMAT = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    private static final String NONE = "없음";

    private static final String TITLE_ORDER_ITEMS = "<주문 메뉴>";
    private static final String ORDER_ITEMS_FORMAT = "%s %d개";

    private static final String TITLE_TOTAL_PRICE = "<할인 전 총주문 금액>";
    private static final String TOTAL_PRICE_FORMAT = "%,d원";

    private static final String TITLE_FREEBIE_ITEMS = "<증정 메뉴>";

    private static final String TITLE_BENEFIT_ITEMS = "<혜택 내역>";
    private static final String BENEFIT_ITEMS_FORMAT = "%s: -%,d원";

    private static final String TITLE_TOTAL_BENEFIT = "<총혜택 금액>";
    private static final String TOTAL_BENEFIT_FORMAT = "-%,d원";

    private static final String TITLE_TOTAL_PRICE_DISCOUNTED = "<할인 후 예상 결제 금액>";

    private static final String TITLE_BADGE = "<12월 이벤트 배지>";


    private final IO ioInstance;

    public OutputView(IO ioInstance) {
        this.ioInstance = ioInstance;
    }

    public void printWelcome() {
        ioInstance.print(TITLE_WELCOME);
    }

    public void printIntroDate(LocalDate date) {
        ioInstance.print(INTRO_DATE_FORMAT.formatted(date.getMonthValue(), date.getDayOfMonth()));
        ioInstance.print("");
    }

    public void printOrderItems(List<MenuItem> menuItems) {
        ioInstance.print(TITLE_ORDER_ITEMS);
        for (var item : menuItems) {
            ioInstance.print(ORDER_ITEMS_FORMAT.formatted(item.menu().getName(), item.quantity()));
        }
        ioInstance.print("");
    }

    public void printTotalPrice(int totalPrice) {
        ioInstance.print(TITLE_TOTAL_PRICE);
        ioInstance.print(String.format(TOTAL_PRICE_FORMAT, totalPrice));
        ioInstance.print("");
    }

    public void printFreebieItems(List<MenuItem> freebieItems) {
        ioInstance.print(TITLE_FREEBIE_ITEMS);
        if (freebieItems.isEmpty()) {
            ioInstance.print(NONE);
            ioInstance.print("");
            return;
        }
        for (var item : freebieItems) {
            ioInstance.print(ORDER_ITEMS_FORMAT.formatted(item.menu().getName(), item.quantity()));
        }
        ioInstance.print("");
    }

    public void printBenefitItems(List<BenefitItem> benefitItems) {
        ioInstance.print(TITLE_BENEFIT_ITEMS);
        if (benefitItems.isEmpty()) {
            ioInstance.print(NONE);
            ioInstance.print("");
            return;
        }
        for (var item : benefitItems) {
            ioInstance.print(BENEFIT_ITEMS_FORMAT.formatted(item.name(), item.amount()));
        }
        ioInstance.print("");
    }

    public void printTotalBenefit(int totalBenefit) {
        ioInstance.print(TITLE_TOTAL_BENEFIT);
        if (totalBenefit == 0) {
            ioInstance.print(NONE);
            ioInstance.print("");
            return;
        }
        ioInstance.print(String.format(TOTAL_BENEFIT_FORMAT, totalBenefit));
        ioInstance.print("");
    }

    public void printTotalPriceDiscounted(int totalPriceDiscounted) {
        ioInstance.print(TITLE_TOTAL_PRICE_DISCOUNTED);
        ioInstance.print(String.format(TOTAL_PRICE_FORMAT, totalPriceDiscounted));
        ioInstance.print("");
    }

    public void printBadge(String badge) {
        ioInstance.print(TITLE_BADGE);
        ioInstance.print(badge);
        ioInstance.print("");
    }
}
