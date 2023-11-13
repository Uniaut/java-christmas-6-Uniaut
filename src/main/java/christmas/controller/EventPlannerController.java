package christmas.controller;

import christmas.domain.EventPlanner;
import christmas.domain.MenuItem;
import christmas.dto.ConsoleInput;
import christmas.parser.DayDateParser;
import christmas.parser.OrderParser;
import christmas.view.ExceptionView;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.io.ConsoleIO;
import java.util.Calendar;
import java.util.List;

public class EventPlannerController {
    EventPlanner eventPlanner = new EventPlanner();
    InputView inputView = new InputView(new ConsoleIO());
    OutputView outputView = new OutputView(new ConsoleIO());
    ExceptionView exceptionView = new ExceptionView(new ConsoleIO());

    private void printWelcome() {
        outputView.printWelcome();
    }

    private void setDate() {
        ConsoleInput input = inputView.askDate();

        DayDateParser dayDateParser = new DayDateParser();
        Calendar date = dayDateParser.parse(input.input());

        eventPlanner.setDate(date);
    }

    private void setOrder() {
        ConsoleInput input = inputView.askOrder();

        OrderParser orderParser = new OrderParser();
        List<MenuItem> order = orderParser.parse(input.input());

        eventPlanner.setOrder(order);
    }

    private void printResults() {
        outputView.printIntroDate(eventPlanner.getDate());
        outputView.printOrderItems(eventPlanner.getOrderItems());
        outputView.printTotalPrice(eventPlanner.getTotalCost());
        outputView.printFreebieItems(eventPlanner.getFreebieItems());
        outputView.printBenefitItems(eventPlanner.getBenefitItems());
        outputView.printTotalBenefit(eventPlanner.getBenefitTotal());
        outputView.printTotalPriceDiscounted(eventPlanner.getTotalCostDiscounted());
        outputView.printBadge(eventPlanner.getBadge());
    }

    public void run() {
        printWelcome();

        new TryUntilSuccess(this::setDate, exceptionView).run();

        new TryUntilSuccess(this::setOrder, exceptionView).run();

        printResults();
    }
}
