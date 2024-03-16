package ShoppingCartManagement;

public enum Category {
    FOOD("jídlo"),
    CONSUMABLES("spotřební zboží"),
    OTHERS("ostatní");
    private String description;


    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "description='" + description + '\'' +
                '}';
    }
}
