package christmas.domain;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, MenuType.APPETIZER),
    TAPAS("타파스", 5500, MenuType.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuType.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, MenuType.MAIN_DISH),
    PORK_RIBS("바비큐립", 54000, MenuType.MAIN_DISH),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuType.MAIN_DISH),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuType.MAIN_DISH),
    CHOCOLATE_CAKE("초코케이크", 15000, MenuType.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuType.DESSERT),
    ZERO_COLA("제로콜라", 3000, MenuType.BEVERAGE),
    RED_WINE("레드와인", 60000, MenuType.BEVERAGE),
    CHAMPAGNE("샴페인", 25000, MenuType.BEVERAGE);

    private final String name;
    private final int cost;
    private final MenuType type;

    Menu(String name, int cost, MenuType type) {
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public static Menu findByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.name.equals(name)) {
                return menu;
            }
        }

        throw new IllegalArgumentException("존재하지 않는 메뉴입니다.");
    }

    public boolean isTypeOf(MenuType type) {
        return this.type.equals(type);
    }

    public int getCost(int quantity) {
        return quantity * cost;
    }

    public String getName() {
        return name;
    }
}
