package ShoppingCartManagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List <Item> items = new ArrayList<>();


    //add item
    public void addItem(Item item) {
        items.add(item);
    }

    //add list of items
    public void addItems(List<Item> items) {
    this.items.addAll(items);
    }

    public void removeItem(Item item) {
        items.remove(item);

    }
    public void removeItems(List<Item> items) {
        this.items.removeAll(items);
    }

    //Statistical methods
    public List<Item> itemsWithPriceOverLimit (BigDecimal limit) {
    List <Item> itemsWithPriceOverLimit = new ArrayList<>();

    for(Item item: items) {
        if (item.getPrice().compareTo(limit) > 0) {
            itemsWithPriceOverLimit.add(item);
        }
    }
        return itemsWithPriceOverLimit;
    }


    //total price of items in ShoppingCart
    public BigDecimal TotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : items) {
             totalPrice = totalPrice.add(item.getPrice());
        }
        return totalPrice;

    }


    // items -> List
    public int itemsCount () {
        return items.size();
    }



}

