// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import ShoppingCartManagement.ShoppingCart;
import ShoppingCartManagement.ShoppingCartException;
import ShoppingCartManagement.Item;
import ShoppingCartManagement.Category;

import java.math.BigDecimal;
public class Main {
    public static void main(String[] args) {

        // enum Category:
        Category category = Category.FOOD;
        System.out.println("Category: " + category);

        if (category.equals(Category.FOOD)) {
            System.out.println("This is food");
        }

        try {
            Item item = new Item("Bread",
                    new BigDecimal("25.0"));
        } catch (ShoppingCartException | IllegalArgumentException e) {
            System.err.println(
                    "Nastala chyba při vytváření položky košíku:\n "
                            +e.getLocalizedMessage());
        } catch (Exception e) {
            System.err.println(
                    "Nastala neznámá chyba:\n "
                            +e.getLocalizedMessage());
        }
    }
}