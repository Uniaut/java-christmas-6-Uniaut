package christmas.parser;

import java.util.Calendar;

public class DayDateParser implements Parser<Calendar>{
    private static final Integer MIN_DAY = 1;
    private static final Integer MAX_DAY = 31;

    private static final Integer YEAR = 2023;
    private static final Integer MONTH = Calendar.DECEMBER;

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
    public Calendar parse(String input) {
        // TODO: add validation feature
        int day = parseInt(input);

        validateDayInRange(day);

        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR, MONTH, day);
        return calendar;
    }
}
