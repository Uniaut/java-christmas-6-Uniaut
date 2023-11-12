package christmas.controller;

import christmas.domain.EventPlanner;
import christmas.dto.ConsoleInput;
import christmas.parser.DateParser;
import christmas.parser.OrderParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.io.ConsoleIO;
import java.util.Calendar;
import java.util.Map;

public class EventPlannerController {
    EventPlanner eventPlanner = new EventPlanner();
    InputView inputView = new InputView(new ConsoleIO());
    OutputView outputView = new OutputView(new ConsoleIO());

    private void setDate() {
        ConsoleInput input = inputView.askDate();

        DateParser dateParser = new DateParser();
        Calendar date = dateParser.parse(input.input());

        eventPlanner.setDate(date);
    }

    private void setOrder() {
        ConsoleInput input = inputView.askOrder();

        OrderParser orderParser = new OrderParser();
        Map<String, Integer> order = orderParser.parse(input.input());

        eventPlanner.setOrder(order);
    }

    private void printResults() {
        outputView.printOrderItems(eventPlanner.getOrderItems());
        outputView.printTotalPrice(eventPlanner.getTotalCost());
        outputView.printFreebieItems(eventPlanner.getFreebieItems());
        outputView.printBenefitItems(eventPlanner.getBenefitItems());
        outputView.printTotalBenefit(eventPlanner.getBenefitTotal());
        outputView.printTotalPriceDiscounted(eventPlanner.getTotalCostDiscounted());
        outputView.printBadge(eventPlanner.getBadge());
    }

    public void run() {
        setDate();

        setOrder();

        printResults();
    }
}
