package christmas.parser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderParser {
    public Map<String, Integer> parse(String input) {
        // TODO: validate input

        List<String> items = List.of(input.split(","));

        return items.stream()
                .map(item -> item.split("-"))
                .collect(Collectors.toMap(
                        item -> item[0],
                        item -> Integer.parseInt(item[1]))
                );
    }
}
