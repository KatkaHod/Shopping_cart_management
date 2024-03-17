// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import ShoppingCartManagement.*;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // enum Category:
        Category category = Category.FOOD;
        System.out.println("Category: " + category);

        if (category.equals(Category.FOOD)) {
            System.out.println("This is food");
        }


        //Item - when call methods with exceptions, use try and catch in the main method
        try {
            Item item = new Item("Bread", new BigDecimal("-25.0"));
        } catch (ShoppingCartException | IllegalArgumentException e) {
            System.err.println(
                    "Nastala chyba při vytváření položky košíku:\n " + e.getLocalizedMessage());
        } catch (Exception e) {
            System.err.println(
                    "Nastala neznámá chyba:\n " + e.getLocalizedMessage());
        }


        //ShoppingCart
         String fileName = Settings.getFilename();
         ShoppingCart cart = new ShoppingCart();
        try {
            cart.loadContentFromFile(fileName);

        } catch (ShoppingCartException e) {
            System.err.println("Nastala chyba pri nacitani kosiku ze souboru: "
                    + fileName + ": \n"+ e.getLocalizedMessage() + "Pokracuji s prazdnym kosikem.");
        }






    }

}