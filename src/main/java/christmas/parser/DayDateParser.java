package christmas.parser;

import java.time.LocalDate;

public class DayDateParser implements Parser<LocalDate>{
    private static final Integer MIN_DAY = 1;
    private static final Integer MAX_DAY = 31;

    private static final Integer YEAR = 2023;
    private static final Integer MONTH = 12;

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDayInRange(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    @Override
    public LocalDate parse(String input) {
        // TODO: add validation feature
        int day = parseInt(input);

        validateDayInRange(day);

        return LocalDate.of(YEAR, MONTH, day);
    }
}
