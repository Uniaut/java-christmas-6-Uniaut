package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.BenefitItem;
import christmas.domain.MenuItem;
import christmas.view.io.MockIO;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OutputViewTest {

    private MockIO mockIO;

    @BeforeEach
    void clearMockIO() {
        mockIO = new MockIO("");
    }


    @DisplayName("주문 메뉴를 포맷에 맞게 출력한다.")
    @Test
    void printOrderItems() {
        List<MenuItem> menuItems = List.of(
                new MenuItem("타파스", 1),
                new MenuItem("제로콜라", 1)
        );

        OutputView outputView = new OutputView(mockIO);
        outputView.printOrderItems(menuItems);

        assertThat(mockIO.getOutputs())
                .contains("<주문 메뉴>", "타파스 1개", "제로콜라 1개");
    }

    @DisplayName("할인 전 총 주문 금액을 포맷에 맞게 출력한다.")
    @Test
    void printTotalPrice() {
        OutputView outputView = new OutputView(mockIO);
        outputView.printTotalPrice(8500);

        assertThat(mockIO.getOutputs())
                .contains("<할인 전 총주문 금액>", "8,500원");
    }

    @DisplayName("증정 메뉴를 포맷에 맞게 출력한다.")
    @Test
    void printFreebieItems() {
        List<MenuItem> freebieItems = List.of(
                new MenuItem("샴페인", 1)
        );

        OutputView outputView = new OutputView(mockIO);
        outputView.printFreebieItems(freebieItems);

        assertThat(mockIO.getOutputs())
                .contains("<증정 메뉴>", "샴페인 1개");
    }

    @DisplayName("혜택 내역을 포맷에 맞게 출력한다.")
    @Test
    void printBenefitItems() {
        List<BenefitItem> benefitItems = List.of(
                new BenefitItem("크리스마스 디데이 할인", 1200),
                new BenefitItem("증정이벤트", 25000)
        );

        OutputView outputView = new OutputView(mockIO);
        outputView.printBenefitItems(benefitItems);

        assertThat(mockIO.getOutputs())
                .contains("<혜택 내역>", "크리스마스 디데이 할인: -1,200원", "증정이벤트: -25,000원");
    }

    @DisplayName("총혜택 금액을 포맷에 맞게 출력한다.")
    @Test
    void printTotalBenefit() {
        OutputView outputView = new OutputView(mockIO);
        outputView.printTotalBenefit(31246);

        assertThat(mockIO.getOutputs())
                .contains("<총혜택 금액>", "-31,246원");
    }

    @DisplayName("할인 후 예상 결제 금액을 포맷에 맞게 출력한다.")
    @Test
    void printTotalPriceDiscounted() {
        OutputView outputView = new OutputView(mockIO);
        outputView.printTotalPriceDiscounted(135754);

        assertThat(mockIO.getOutputs())
                .contains("<할인 후 예상 결제 금액>", "135,754원");
    }

    @DisplayName("뱃지를 포맷에 맞게 출력한다.")
    @Test
    void printBadge() {
        OutputView outputView = new OutputView(mockIO);
        outputView.printBadge("산타");

        assertThat(mockIO.getOutputs())
                .contains("<12월 이벤트 배지>", "산타");
    }

}
