package christmas.parser;

import christmas.constant.ExceptionMessage;
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
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DATE.getMessage());
        }
    }

    private void validateDayInRange(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_DATE.getMessage());
        }
    }

    @Override
    public LocalDate parse(String input) {
        int day = parseInt(input);

        validateDayInRange(day);

        return LocalDate.of(YEAR, MONTH, day);
    }
}
