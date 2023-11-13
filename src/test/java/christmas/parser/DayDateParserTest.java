package christmas.parser;

import static org.assertj.core.api.Assertions.assertThat;

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

}
