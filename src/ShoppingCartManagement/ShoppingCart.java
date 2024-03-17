
package ShoppingCartManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

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
                String [] parts = line.split(";");

                if (parts.length != 6) throw  new ShoppingCartException(
                        "Nesprávný počet položek na řádku číslo: " + lineCounter + ": "+line+"!");

                //Each part is created on the basis of the data contained in the file.
                LocalDateTime time = LocalDateTime.parse(parts[0]);
                BigDecimal price = new BigDecimal(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                Category category = Category.valueOf(parts[3]);
                String description = parts[4];
                boolean isOnStock = "ano".equals(parts[5]);

                //constructor - add values from file
                Item item = new Item(description, price, time, isOnStock, quantity, category);
                items.add(item);
            }

        } catch (FileNotFoundException e) {
            throw new ShoppingCartException("Soubor " + fileName + "nebyl nalezen!\n" + e.getLocalizedMessage());
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

