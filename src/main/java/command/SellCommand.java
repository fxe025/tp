package command;

import exceptions.CommandFormatException;
import item.Item;
import item.Transaction;
import itemlist.Cashier;
import itemlist.Itemlist;
import storage.Storage;
import storage.TransactionLogs;

public class SellCommand extends Command {

    protected String itemName;
    protected int sellQuantity;
    protected float discount;

    public SellCommand (String itemName, int quantity, float discount) {
        this.itemName = itemName;
        this.sellQuantity = quantity;
        this.discount = discount;
    }

    public String getItemName() {
        return itemName;
    }

    public int getSellQuantity() {
        return sellQuantity;
    }

    @Override
    public void execute() throws CommandFormatException {
        int index = -1;
        Item toSell = null;
        for (Item item : Itemlist.getItems()) {
            if (item.getItemName().toLowerCase().equals(itemName.toLowerCase())) {
                index = Itemlist.getItems().indexOf(item);
                toSell = item;
                break;
            }
        }
        if (index == -1) {
            //throw exception;
            System.out.println("Item not found!");
            return;
        }
        int remainingQuantity = Itemlist.getItem(index).getQuantity() - sellQuantity;
        float getSellPrice = Itemlist.getItem(index).getSellPrice();
        float sellPrice = (this.discount >= 0) ? this.discount * getSellPrice : getSellPrice;
        if (toSell.getIsOOS() || remainingQuantity < 0) {
            System.out.println("There is insufficient stock!");
            return;
        } else {
            ui.TextUi.showSellMessage(itemName, sellQuantity, remainingQuantity, sellPrice);
            Itemlist.editQuantity(index, remainingQuantity);
        }
        Storage.overwriteFile(Itemlist.getItems());
        Transaction newTransaction = new Transaction(Itemlist.getItem(index).getItemName(),
                sellQuantity, toSell.getBuyPrice(), sellPrice);
        Cashier.addItem(newTransaction);
        TransactionLogs.addToLog(Cashier.getTransactions());
    }

}
