package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventPlannerTest {
    @DisplayName("setDate는 올바른 날짜를 입력받으면 예외가 발생하지 않는다.")
    @Test
    void testEventPlannerConstructor() {
        Calendar christmas = Calendar.getInstance();
        christmas.set(2023, Calendar.DECEMBER, 25);
        Date date = new Date(christmas.getTimeInMillis());
        EventPlanner eventPlanner = new EventPlanner();

        assertThatNoException().isThrownBy(
                () -> eventPlanner.setDate(date)
        );
    }

    @DisplayName("이벤트 플래너 생성자는 Date로 null을 입력받으면 예외가 발생한다.")
    @Test
    void testEventPlannerConstructorWithNull() {
        EventPlanner eventPlanner = new EventPlanner();

        assertThatIllegalArgumentException().isThrownBy(
                () -> eventPlanner.setDate(null)
        ).withMessageContaining("[ERROR]");
    }
}
