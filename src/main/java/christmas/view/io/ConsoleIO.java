package christmas.view.io;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleIO implements IO {
    public void print(String message) {
        System.out.println(message);
    }

    public String read() {
        return Console.readLine();
    }
}
