package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventPlannerTest {
    @DisplayName("setDate는 올바른 날짜를 입력받으면 예외가 발생하지 않는다.")
    @Test
    void setDateValid() {
        Calendar christmas = Calendar.getInstance();
        christmas.set(2023, Calendar.DECEMBER, 25);
        Date date = new Date(christmas.getTimeInMillis());
        EventPlanner eventPlanner = new EventPlanner();

        assertThatNoException().isThrownBy(
                () -> eventPlanner.setDate(date)
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
}
