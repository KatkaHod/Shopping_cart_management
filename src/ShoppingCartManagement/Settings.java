package ShoppingCartManagement;

public class Settings {

    //txt file constant
    private static final String FILENAME = "resources/cart.txt";
    private static final String DELIMITER = ";";


    public static String getFilename() {
        return FILENAME;
    }

    public static String getDelimiter() {
        return DELIMITER;
    }

}
