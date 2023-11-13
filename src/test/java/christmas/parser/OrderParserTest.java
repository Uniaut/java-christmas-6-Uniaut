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

    @DisplayName("parse는 쉼표로 구분된 항목이 메뉴명-수량(정수)가 아니라면 예외를 발생시킨다.")
    @Test
    void parseWithInvalidFormatInput() {
        OrderParser orderParser = new OrderParser();

        String input = "타파스,제로콜라-7";

        assertThatIllegalArgumentException().isThrownBy(
                () -> orderParser.parse(input)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("parse는 수량이 1 미만이라면 예외를 발생시킨다.")
    @Test
    void parseWithInvalidQuantity() {
        OrderParser orderParser = new OrderParser();

        String input = "타파스-1,제로콜라-0";

        assertThatIllegalArgumentException().isThrownBy(
                () -> orderParser.parse(input)
        ).withMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
