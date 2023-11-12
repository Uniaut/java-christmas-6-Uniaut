package christmas.view.io;

import java.util.ArrayList;
import java.util.List;

public class MockIO implements IO {
    private final String input;
    private List<String> outputs;

    public MockIO(String input) {
        this.input = input;
        this.outputs = new ArrayList<>();
    }

    public String read() {
        return input;
    }

    public void print(String message) {
        outputs.add(message);
    }

    public List<String> getOutputs() {
        return outputs;
    }
}
