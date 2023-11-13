package christmas.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.Calendar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DayDateParserTest {
    @DisplayName("parse는 올바른 숫자를 입력받으면 2023년 12월의 Calendar 객체를 반환한다.")
    @Test
    void parseValid() {
        DayDateParser dayDateParser = new DayDateParser();

        String input = "25";
        Calendar actual = Calendar.getInstance();
        actual.set(2023, Calendar.DECEMBER, 25);

        assertThat(dayDateParser.parse(input))
                .isEqualTo(actual);
    }

    @DisplayName("parse는 숫자가 아닌 값을 입력받으면 예외가 발생한다.")
    @Test
    void parseWithInvalidInput() {
        DayDateParser dayDateParser = new DayDateParser();

        String input = "a";

        assertThatIllegalArgumentException().isThrownBy(
                () -> dayDateParser.parse(input)
        ).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("parse는 1 ~ 31 범위 밖의 값을 입력받으면 예외가 발생한다.")
    @Test
    void parseWithOutOfRangeInput() {
        DayDateParser dayDateParser = new DayDateParser();

        String input = "32";

        assertThatIllegalArgumentException().isThrownBy(
                () -> dayDateParser.parse(input)
        ).withMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

}
