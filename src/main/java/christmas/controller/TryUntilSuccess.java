package christmas.controller;

import christmas.view.ExceptionView;

public class TryUntilSuccess {
    private final Runnable runnable;
    private final ExceptionView exceptionView;

    public TryUntilSuccess(Runnable runnable, ExceptionView exceptionView) {
        this.runnable = runnable;
        this.exceptionView = exceptionView;
    }

    public void run() {
        while (true) {
            try {
                runnable.run();
                return;
            } catch (Exception e) {
                exceptionView.printExceptionMessage(e);
            }
        }
    }
}
