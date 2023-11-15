package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BenefitsTest {
    @DisplayName("getWeekdayBenefit에 12월 13일의 이벤트인 평일 할인이 적용된다.")
    @Test
    void getWeekdayBenefit() {
        LocalDate dec13 = LocalDate.of(2023, 12, 13);
        Map<String, Integer> order = Map.of(
                "초코케이크", 2,
                "제로콜라", 1
        );

        Benefits benefits = new Benefits(dec13, order);

        assertThat(benefits.getWeekdayBenefit())
                .isEqualTo(2023 * 2);
    }

    @DisplayName("getWeekendBenefit에 12월 30일의 이벤트인 주말 할인이 적용된다.")
    @Test
    void getWeekendBenefit() {
        LocalDate dec30 = LocalDate.of(2023, 12, 30);
        Map<String, Integer> order = Map.of(
                "티본스테이크", 2,
                "제로콜라", 1
        );

        Benefits benefits = new Benefits(dec30, order);

        assertThat(benefits.getWeekendBenefit())
                .isEqualTo(2023 * 2);
    }

    @DisplayName("getBenefitItems는 혜택 내역들을 반환한다.")
    @Test
    void getBenefitItems() {
        LocalDate dec3 = LocalDate.of(2023, 12, 3);
        Map<String, Integer> order = Map.of(
                "티본스테이크", 1,
                "바비큐립", 1,
                "초코케이크", 2,
                "제로콜라", 1
        );

        Benefits benefits = new Benefits(dec3, order);

        assertThat(benefits.getBenefitItems())
                .contains(new BenefitItem("크리스마스 디데이 할인", 1200))
                .contains(new BenefitItem("평일 할인", 2023 * 2))
                .contains(new BenefitItem("특별 할인", 1000))
                .contains(new BenefitItem("증정 이벤트", 25000));

    }

    @DisplayName("getChristmasBenefit는 24일에 3300을 반환한다.")
    @Test
    void getChristmasBenefitChristmasEve() {
        LocalDate christmasEve = LocalDate.of(2023, 12, 25);
        Map<String, Integer> order = Map.of(
                "초코케이크", 2,
                "제로콜라", 1
        );

        Benefits benefits = new Benefits(christmasEve, order);

        assertThat(benefits.getChristmasBenefit())
                .isEqualTo(3400);
    }

    @DisplayName("getChristmasBenefit는 크리스마스 이후에 0을 반환한다.")
    @Test
    void getChristmasBenefitAfterChristmas() {
        LocalDate afterChristmas = LocalDate.of(2023, 12, 26);

        Map<String, Integer> order = Map.of(
                "초코케이크", 2,
                "제로콜라", 1
        );

        Benefits benefits = new Benefits(afterChristmas, order);

        assertThat(benefits.getChristmasBenefit())
                .isEqualTo(0);
    }

    @DisplayName("getSpecialBenefit는 크리스마스에 1000을 반환한다.")
    @Test
    void getSpecialBenefitChristmas() {
        LocalDate christmas = LocalDate.of(2023, 12, 25);

        Map<String, Integer> orderMap = Map.of(
                "초코케이크", 2,
                "제로콜라", 1
        );

        Benefits benefits = new Benefits(christmas, orderMap);

        assertThat(benefits.getSpecialBenefit())
                .isEqualTo(1000);
    }

    @DisplayName("getFreebieBenefit는 샴페인 증정 혜택 금액을 반환한다.")
    @Test
    void getFreebieBenefit() {
        LocalDate dec8 = LocalDate.of(2023, 12, 8);
        Map<String, Integer> orderMap = Map.of(
                "초코케이크", 2,
                "티본스테이크", 2
        );

        Benefits benefits = new Benefits(dec8, orderMap);

        assertThat(benefits.getFreebieBenefit())
                .isEqualTo(25000);
    }
}
