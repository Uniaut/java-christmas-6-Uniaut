package christmas.view;

import christmas.view.io.IO;

public class ExceptionView {
    private IO ioInstance;

    public ExceptionView (IO ioInstance) {
        this.ioInstance = ioInstance;
    }

    public void printExceptionMessage(Exception e) {
        ioInstance.print(e.getMessage());
    }
}
