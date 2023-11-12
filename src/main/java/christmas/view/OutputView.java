package christmas.view;

import christmas.domain.BenefitItem;
import christmas.domain.OrderItem;
import christmas.view.io.IO;
import java.util.List;

public class OutputView {
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

    public void printOrderItems(List<OrderItem> orderItems) {
        ioInstance.print(TITLE_ORDER_ITEMS);
        for (var item : orderItems) {
            ioInstance.print(ORDER_ITEMS_FORMAT.formatted(item.name(), item.quantity()));
        }
        ioInstance.print("");
    }

    public void printTotalPrice(int totalPrice) {
        ioInstance.print(TITLE_TOTAL_PRICE);
        ioInstance.print(String.format(TOTAL_PRICE_FORMAT, totalPrice));
        ioInstance.print("");
    }

    public void printFreebieItems(List<OrderItem> freebieItems) {
        ioInstance.print(TITLE_FREEBIE_ITEMS);
        for (var item : freebieItems) {
            ioInstance.print(ORDER_ITEMS_FORMAT.formatted(item.name(), item.quantity()));
        }
        ioInstance.print("");
    }

    public void printBenefitItems(List<BenefitItem> benefitItems) {
        ioInstance.print(TITLE_BENEFIT_ITEMS);
        for (var item : benefitItems) {
            ioInstance.print(BENEFIT_ITEMS_FORMAT.formatted(item.name(), item.amount()));
        }
        ioInstance.print("");
    }

    public void printTotalBenefit(int totalBenefit) {
        ioInstance.print(TITLE_TOTAL_BENEFIT);
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
