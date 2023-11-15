package christmas.parser;

import christmas.constant.ExceptionMessage;
import christmas.domain.Menu;
import christmas.domain.MenuItem;
import java.util.List;

public class OrderParser {
    private static final String ITEM_DELIMITER = ",";
    private static final String NAME_QUANTITY_DELIMITER = "-";

    private void validateOneItemFormat(String item) {
        if (!item.matches("^([^-]+)-(\\d+)$")) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    private MenuItem parseOneItem(String item) {
        validateOneItemFormat(item);

        String[] itemSplit = item.split(NAME_QUANTITY_DELIMITER);
        String name = itemSplit[0];
        int quantity = Integer.parseInt(itemSplit[1]);

        validateQuantity(quantity);

        try {
            Menu menu = Menu.findByName(name);
            return new MenuItem(menu, quantity);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_ORDER.getMessage());
        }
    }

    public List<MenuItem> parse(String input) {
        List<String> items = List.of(input.split(ITEM_DELIMITER));

        return items.stream()
                .map(this::parseOneItem)
                .toList();
    }
}
