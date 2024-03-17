
package ShoppingCartManagement;

import java.io.*;
import java.math.BigDecimal;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;


public class ShoppingCart {
    private List <Item> items = new ArrayList<>();

    /*
    > INFO: BufferedReader - improves performance when loading Java files,
         allows line-by-line reading, supports different encodings and offers a simple interface.
     > Always prepare exceptions when working with files.
     */
    public void loadContentFromFile(String fileName) throws ShoppingCartException {
        int lineCounter = 0;
        items.clear(); //when I add new entries the list will be deleted
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName))))  {
            while(scanner.hasNextLine()) {
                lineCounter++; //for ShoppingCartException purpose - calculate lines (lineCounter)

                String line = scanner.nextLine();
                System.out.println(line);
                String [] parts = line.split(";"); //make from .txt each parts, which are describe below

                if (parts.length != 6) throw  new ShoppingCartException(
                        "Nesprávný počet položek na řádku číslo: " + lineCounter + ": "+line+"!");

                //From the text file convert the 'text' to 'values'. Starts from the left to right.
                LocalDateTime time = LocalDateTime.parse(parts[0]);
                BigDecimal price = new BigDecimal(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                Category category = Category.valueOf(parts[3]);
                String description = parts[4];
                boolean isOnStock = "ano".equals(parts[5]);

                Item item = new Item(description, price, time, isOnStock, quantity, category);
                items.add(item);
            }

        } catch (FileNotFoundException e) {
            throw new ShoppingCartException("Soubor " + fileName + "nebyl nalezen!\n" + e.getLocalizedMessage());
        }
        catch (NumberFormatException e) {
            throw new ShoppingCartException("Chyba při čtení číselné hodnoty na řádku číslo: "+lineCounter+":\n"
                    + e.getLocalizedMessage());
        } catch (IllegalArgumentException e) {
            throw new ShoppingCartException("Chyba při čtení kategorie na řádku číslo: "+lineCounter+":\n"
                    + e.getLocalizedMessage());
        } catch (DateTimeParseException e) {
            throw new ShoppingCartException("Chyba při čtení data na řádku číslo: "+lineCounter+":\n"
                    + e.getLocalizedMessage());

        }

    }


    public void saveContentToFile(String fileName) throws ShoppingCartException {
        String delimiter = Settings.getDelimiter();
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Item item : items) {
                writer.println(item.getReservationTime() + delimiter
                        + item.getPrice() + delimiter
                        + item.getQuantityOfItem() + delimiter
                        + item.getCategory() + delimiter
                        + item.getDescription() + delimiter
                        + (item.getOnStock() ? "ano" : "ne"));
            }
        } catch (FileNotFoundException e) {
            throw new ShoppingCartException("Soubor " + fileName + " nebyl nalezen!\n"
                    + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new ShoppingCartException("Chyba výstupu při zápisu do souboru: " + fileName
                    +":\n"+ e.getLocalizedMessage());
        }
    }




    //creates a copy of the items list and returns it as a new list.
    //changes made to the returned list do not affect the original list items - SAVER OPTION
    public List<Item> getItems() {
        return new ArrayList<>(items);
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

