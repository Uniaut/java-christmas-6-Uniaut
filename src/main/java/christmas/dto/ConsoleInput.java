package christmas.dto;

public record ConsoleInput(String input) {
    public static ConsoleInput from(String input) {
        return new ConsoleInput(input);
    }
}
