package christmas.view;

import christmas.dto.ConsoleInput;
import christmas.view.io.IO;

public class InputView {
    private static final String ASK_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ASK_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final IO ioInstance;

    public InputView(IO ioInstance) {
        this.ioInstance = ioInstance;
    }

    public ConsoleInput askDate() {
        ioInstance.print(ASK_DATE_MESSAGE);
        return ConsoleInput.from(ioInstance.read());
    }

    public ConsoleInput askOrder() {
        ioInstance.print(ASK_ORDER_MESSAGE);
        return ConsoleInput.from(ioInstance.read());
    }

}
