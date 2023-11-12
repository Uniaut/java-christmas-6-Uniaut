package christmas.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderParserTest {
    @DisplayName("parse는 올바른 문자열을 입력받으면 Map 형식의 주문을 반환한다.")
    @Test
    void parseValid() {
        OrderParser orderParser = new OrderParser();

        String input = "타파스-1,제로콜라-1";
        Map<String, Integer> actual = Map.of("타파스", 1, "제로콜라", 1);

        assertThat(orderParser.parse(input))
                .isEqualTo(actual);
    }
}
