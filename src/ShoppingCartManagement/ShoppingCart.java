
package ShoppingCartManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    private List <Item> items = new ArrayList<>();

    /*
    > BufferedReader - improves performance when loading Java files,
         allows line-by-line reading, supports different encodings and offers a simple interface.
     > Always prepare exceptions when working with files.
     */
    public void loadContentFromFile(String fileName) throws ShoppingCartException {
        int lineCounter = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName))))  {
            while(scanner.hasNextLine()) {
                lineCounter++;

                String line = scanner.nextLine();
                System.out.println(line);
                String [] parts = line.split(",");

                if (parts.length != 6) throw  new ShoppingCartException(
                        "Nesprávný počet položek na řádku číslo: " + lineCounter + ": "+line+"!");

            }

        } catch (FileNotFoundException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
    }




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

