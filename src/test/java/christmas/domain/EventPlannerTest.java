package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventPlannerTest {
    @DisplayName("setDate는 올바른 날짜를 입력받으면 예외가 발생하지 않는다.")
    @Test
    void setDateValid() {
        LocalDate christmas = LocalDate.of(2023, 12, 25);
        EventPlanner eventPlanner = new EventPlanner();

        assertThatNoException().isThrownBy(
                () -> eventPlanner.setDate(christmas)
        );
    }

    @DisplayName("setDate는 null을 입력받으면 예외가 발생한다.")
    @Test
    void setDateWithNull() {
        EventPlanner eventPlanner = new EventPlanner();

        assertThatIllegalArgumentException().isThrownBy(
                () -> eventPlanner.setDate(null)
        ).withMessageContaining("[ERROR]");
    }

    @DisplayName("setDate는 2023년 12월이 아닌 날짜를 입력받으면 예외가 발생한다.")
    @Test
    void setDateWithInvalidDate() {
        LocalDate lastYearChristmas = LocalDate.of(2022, 12, 25);
        EventPlanner eventPlanner = new EventPlanner();

        assertThatIllegalArgumentException().isThrownBy(
                () -> eventPlanner.setDate(lastYearChristmas)
        ).withMessageContaining("[ERROR]");
    }

    @DisplayName("setOrder는 올바른 주문을 입력받으면 예외가 발생하지 않는다.")
    @Test
    void setOrderValid() {
        List<MenuItem> order = List.of(
                new MenuItem("타파스", 1),
                new MenuItem("제로콜라", 1)
        );

        EventPlanner eventPlanner = new EventPlanner();

        assertThatNoException().isThrownBy(
                () -> eventPlanner.setOrder(order)
        );
    }

    @DisplayName("setOrder는 null을 입력받으면 예외가 발생한다.")
    @Test
    void setOrderWithNull() {
        EventPlanner eventPlanner = new EventPlanner();

        assertThatIllegalArgumentException().isThrownBy(
                () -> eventPlanner.setOrder(null)
        ).withMessageContaining("[ERROR]");
    }

    @DisplayName("setOrder는 빈 주문을 입력받으면 예외가 발생한다.")
    @Test
    void setOrderWithEmpty() {
        List<MenuItem> order = List.of();

        EventPlanner eventPlanner = new EventPlanner();

        assertThatIllegalArgumentException().isThrownBy(
                () -> eventPlanner.setOrder(order)
        ).withMessageContaining("[ERROR]");
    }

    @DisplayName("setOrder는 유효하지 않은 메뉴를 입력받으면 예외가 발생한다.")
    @Test
    void setOrderWithInvalidMenu() {
        List<MenuItem> order = List.of(
                new MenuItem("없는메뉴", 1)
        );

        EventPlanner eventPlanner = new EventPlanner();

        assertThatIllegalArgumentException().isThrownBy(
                () -> eventPlanner.setOrder(order)
        ).withMessageContaining("[ERROR]");
    }

    @DisplayName("setOrder는 중복된 메뉴를 입력받으면 예외가 발생한다.")
    @Test
    void setOrderWithDuplicatedMenu() {
        List<MenuItem> order = List.of(
                new MenuItem("타파스", 1),
                new MenuItem("타파스", 2)
        );

        EventPlanner eventPlanner = new EventPlanner();

        assertThatIllegalArgumentException().isThrownBy(
                () -> eventPlanner.setOrder(order)
        ).withMessageContaining("[ERROR]");
    }

    @DisplayName("setOrder는 음료만 주문할 경우 예외가 발생한다.")
    @Test
    void setOrderWithOnlyBeverage() {
        List<MenuItem> order = List.of(
                new MenuItem("제로콜라", 1)
        );

        EventPlanner eventPlanner = new EventPlanner();

        assertThatIllegalArgumentException().isThrownBy(
                () -> eventPlanner.setOrder(order)
        ).withMessageContaining("[ERROR]");
    }

    @DisplayName("setOrder는 총 수량이 20개를 초과할 경우 예외가 발생한다.")
    @Test
    void setOrderWithOverQuantity() {
        List<MenuItem> order = List.of(
                new MenuItem("타파스", 11),
                new MenuItem("제로콜라", 11)
        );

        EventPlanner eventPlanner = new EventPlanner();

        assertThatIllegalArgumentException().isThrownBy(
                () -> eventPlanner.setOrder(order)
        ).withMessageContaining("[ERROR]");
    }

    @DisplayName("getOrders는 입력받은 주문을 반환한다.")
    @Test
    void getOrders() {
        List<MenuItem> order = List.of(
                new MenuItem("타파스", 1),
                new MenuItem("제로콜라", 1)
        );

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getOrderItems())
                .contains(new MenuItem("타파스", 1))
                .contains(new MenuItem("제로콜라", 1));
    }

    @DisplayName("getTotalCost는 올바른 주문이 주어지면 값을 반환한다.")
    @Test
    void getTotalCostValid() {
        List<MenuItem> order = List.of(
                new MenuItem("타파스", 1),
                new MenuItem("제로콜라", 1)
        );

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getTotalCost())
                .isEqualTo(8500);
    }

    @DisplayName("getBenefitTotal는 총혜택 금액을 반환한다. 증정을 포함한다.")
    @Test
    void getBenefitTotal() {
        LocalDate dec8 = LocalDate.of(2023, 12, 8);

        List<MenuItem> order = List.of(
                new MenuItem("티본스테이크", 1),
                new MenuItem("바비큐립", 1),
                new MenuItem("초코케이크", 2),
                new MenuItem("제로콜라", 1)
        );

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setDate(dec8);
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getBenefitTotal())
                .isEqualTo(2023 * 2 + 25000 + 1700);
    }

    @DisplayName("모든 혜택은 총주문 금액 10000원 미만에는 적용되지 않는다")
    @Test
    void getBenefitTotalWithLessThan10000() {
        LocalDate christmas = LocalDate.of(2023, 12, 25);

        List<MenuItem> order = List.of(
                new MenuItem("타파스", 1)
        );

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setDate(christmas);
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getBenefitTotal())
                .isEqualTo(0);
    }

    @DisplayName("getDiscountedTotalCost는 할인 후 예상 결제 금액을 반환한다. 증정은 제외한다.")
    @Test
    void getTotalCostDiscounted() {
        LocalDate dec30 = LocalDate.of(2023, 12, 30);

        List<MenuItem> order = List.of(
                new MenuItem("초코케이크", 2),
                new MenuItem("티본스테이크", 2)
        );

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setDate(dec30);
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getTotalCostDiscounted())
                .isEqualTo((55000 * 2) + (15000 * 2) - (2023 * 2));
    }

    @DisplayName("getBadge는 올바른 주문이 주어지면 해당하는 뱃지를 반환한다.")
    @Test
    void getBadge() {
        LocalDate dec30 = LocalDate.of(2023, 12, 30);

        List<MenuItem> order = List.of(
                new MenuItem("초코케이크", 2),
                new MenuItem("티본스테이크", 2)
        );

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setDate(dec30);
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getBadge())
                .isEqualTo("산타");
    }
}
