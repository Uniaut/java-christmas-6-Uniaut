package christmas.parser;

import christmas.domain.MenuItem;
import java.util.List;

public class OrderParser {
    private static final String ITEM_DELIMITER = ",";
    private static final String NAME_QUANTITY_DELIMITER = "-";

    private void validateOneItemFormat(String item) {
        if (!item.matches("^([^-]+)-(\\d+)$")) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private MenuItem parseOneItem(String item) {
        validateOneItemFormat(item);

        String[] itemSplit = item.split(NAME_QUANTITY_DELIMITER);
        String name = itemSplit[0];
        int quantity = Integer.parseInt(itemSplit[1]);

        validateQuantity(quantity);

        return new MenuItem(name, quantity);
    }

    public List<MenuItem> parse(String input) {
        List<String> items = List.of(input.split(ITEM_DELIMITER));

        return items.stream()
                .map(this::parseOneItem)
                .toList();
    }
}
