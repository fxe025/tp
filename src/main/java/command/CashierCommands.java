package command;

import item.Item;
import itemlist.Cashier;
import parser.CommandType;
import ui.TextUi;

public class CashierCommands extends Command {
    protected CommandType commandType;

    public CashierCommands(CommandType command) {
        this.commandType = command;
    }

    @Override
    public void execute() {
        switch (commandType) {
        case BESTSELLER:
            Item bs = Cashier.getBestseller();
            TextUi.replyToUser("The bestseller is " + bs.getItemName());
            break;
        case TOTAL_PROFIT:
            float profit = Cashier.getTotalProfit();
            TextUi.replyToUser("The total profit so far is " + String.format("%.2f", profit));
            break;
        case TOTAL_REVENUE:
            float revenue = Cashier.getTotalRevenue();
            TextUi.replyToUser("The total revenue so far is " + String.format("%.2f", revenue));
            break;
        default:
            break;
        }

    };
}
