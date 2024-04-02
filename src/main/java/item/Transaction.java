package item;

import itemlist.Cashier;
import itemlist.Itemlist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String dateTime;
    private float totalPrice;
    private float profit;
    private Item item;
    private int quantity;
    private float buyPrice;
    private float sellPrice;
    private Boolean isVoided;

    public Transaction(String name, int inputQty, float inputBuy, float inputSell) {
        setDateTime();
        item = Itemlist.getItem(name);
        quantity = inputQty;
        buyPrice = inputBuy;
        sellPrice = inputSell;
        totalPrice = sellPrice * quantity;
        profit = totalPrice - buyPrice * quantity;
        isVoided = false;
    }

    public Transaction(String name, int inputQty, int inputBuy, int inputSell, String storedTime) {
        dateTime = storedTime;
        item = Itemlist.getItem(name);
        quantity = inputQty;
        buyPrice = inputBuy;
        sellPrice = inputSell;
        totalPrice = sellPrice * quantity;
        profit = totalPrice - buyPrice * quantity;
    }

    public String getItemName() {
        return this.item.getItemName();
    }

    public Item getItem() {
        return this.item;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public float getSellPrice() {
        return this.sellPrice;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public float getTotalPrice() {
        return this.totalPrice;
    }

    public float getProfit() {
        return this.profit;
    }

    public Boolean getIsVoided() {
        return this.isVoided;
    }

    public void setDateTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dateTime = currentTime.format(formatter);
    }

    public void voidTransaction(int index) {
        Transaction txn = Cashier.getTransaction(index);
        txn.isVoided = true;
    }
}
