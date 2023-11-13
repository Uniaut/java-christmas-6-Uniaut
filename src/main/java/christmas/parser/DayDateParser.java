package christmas.parser;

import java.util.Calendar;

public class DayDateParser implements Parser<Calendar>{
    public Calendar parse(String input) {
        // TODO: add validation feature
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.DECEMBER, Integer.parseInt(input));
        return calendar;
    }
}
