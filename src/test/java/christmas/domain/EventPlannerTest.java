package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventPlannerTest {
    @DisplayName("setDate는 올바른 날짜를 입력받으면 예외가 발생하지 않는다.")
    @Test
    void setDateValid() {
        Calendar christmas = Calendar.getInstance();
        christmas.set(2023, Calendar.DECEMBER, 25);
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

    @DisplayName("이벤트 플래너 생성자")
    @Test
    void setOrderValid() {
        Map<String, Integer> order = new HashMap<>();
        order.put("타파스", 1);
        order.put("제로콜라", 1);

        EventPlanner eventPlanner = new EventPlanner();

        assertThatNoException().isThrownBy(
                () -> eventPlanner.setOrder(order)
        );
    }

    @DisplayName("getTotalCost는 올바른 주문이 주어지면 값을 반환한다.")
    @Test
    void getTotalCostValid() {
        Map<String, Integer> order = new HashMap<>();
        order.put("타파스", 1);
        order.put("제로콜라", 1);

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getTotalCost())
                .isEqualTo(8500);
    }

    @DisplayName("getWeekendBenefit에 12월 30일의 이벤트인 주말 할인이 적용된다.")
    @Test
    void getWeekendBenefit() {
        Calendar dec30 = Calendar.getInstance();
        dec30.set(2023, Calendar.DECEMBER, 30);

        Map<String, Integer> order = new HashMap<>();
        order.put("초코케이크", 2);
        order.put("제로콜라", 1);

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setDate(dec30);
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getWeekendBenefit())
                .isEqualTo(2023 * 2);
    }

    // TODO: 최대한 많은 혜택 내역을 반영하도록 수정한다.
    @DisplayName("getBenefitItems는 혜택 내역들을 반환한다.")
    @Test
    void getBenefitItems() {
        Calendar dec30 = Calendar.getInstance();
        dec30.set(2023, Calendar.DECEMBER, 30);

        Map<String, Integer> order = new HashMap<>();
        order.put("초코케이크", 2);
        order.put("제로콜라", 1);

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setDate(dec30);
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getBenefitItems())
                .isInstanceOf(List.class);

    }

    // TODO: 최대한 많은 혜택 내역을 반영하도록 수정한다.
    @DisplayName("getBenefitTotal는 총혜택 금액을 반환한다. 증정을 포함한다.")
    @Test
    void getBenefitTotal() {
        Calendar dec30 = Calendar.getInstance();
        dec30.set(2023, Calendar.DECEMBER, 30);

        Map<String, Integer> order = new HashMap<>();
        order.put("초코케이크", 2);
        order.put("티본스테이크", 2);

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setDate(dec30);
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getBenefitTotal())
                .isEqualTo(2023 * 2 + 25000);
    }

    @DisplayName("getDiscountedTotalCost는 할인 후 예상 결제 금액을 반환한다. 증정은 제외한다.")
    @Test
    void getDiscountedTotalCost() {
        Calendar dec30 = Calendar.getInstance();
        dec30.set(2023, Calendar.DECEMBER, 30);

        Map<String, Integer> order = new HashMap<>();
        order.put("초코케이크", 2);
        order.put("티본스테이크", 2);

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setDate(dec30);
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getDiscountedTotalCost())
                .isEqualTo((55000 * 2) + (15000 * 2) - (2023 * 2));
    }

    @DisplayName("getBadge는 올바른 주문이 주어지면 해당하는 뱃지를 반환한다.")
    @Test
    void getBadge() {
        Calendar dec30 = Calendar.getInstance();
        dec30.set(2023, Calendar.DECEMBER, 30);

        Map<String, Integer> order = new HashMap<>();
        order.put("초코케이크", 2);
        order.put("티본스테이크", 2);

        EventPlanner eventPlanner = new EventPlanner();
        eventPlanner.setDate(dec30);
        eventPlanner.setOrder(order);

        assertThat(eventPlanner.getBadge())
                .isEqualTo("산타");
    }
}
