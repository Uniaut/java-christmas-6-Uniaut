package christmas.controller;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.view.ExceptionView;
import christmas.view.io.MockIO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TryUntilSuccessTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    private boolean called = false;

    private void call() {
        if (!called) {
            called = true;
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    @DisplayName("run은 Exception이 발생하면 오류 메시지를 출력하고 다시 실행한다.")
    @Test
    void runWithException() {
        MockIO mockIO = new MockIO("");
        ExceptionView exceptionView = new ExceptionView(mockIO);

        TryUntilSuccess tryUntilSuccess = new TryUntilSuccess(this::call, exceptionView);
        tryUntilSuccess.run();

        assertThat(mockIO.getOutputs())
                .contains(ERROR_MESSAGE);
    }

    @DisplayName("run은 Exception이 발생하지 않으면 그대로 실행을 종료한다.")
    @Test
    void runWithNoException() {
        MockIO mockIO = new MockIO("");
        ExceptionView exceptionView = new ExceptionView(mockIO);

        try {
            call();
        } catch(IllegalArgumentException e) {
            // do nothing
        }
        TryUntilSuccess tryUntilSuccess = new TryUntilSuccess(this::call, exceptionView);
        tryUntilSuccess.run();

        assertThat(mockIO.getOutputs())
                .doesNotContain(ERROR_MESSAGE);
    }
}
