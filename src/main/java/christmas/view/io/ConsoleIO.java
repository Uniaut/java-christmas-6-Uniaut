package christmas.view.io;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleIO {
    public void print(String message) {
        System.out.println(message);
    }

    public String read() {
        return Console.readLine();
    }
}
