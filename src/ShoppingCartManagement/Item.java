package ShoppingCartManagement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Item {

    private String description;
    private BigDecimal price;
    private LocalDateTime reservationTime;
    private Boolean isOnStock;
    private int quantityOfItem;
    private Category category = Category.OTHERS;



    //setPrice(price), the exception (price must be greater than 0) is added in setPrice.
    public Item(String description, BigDecimal price, LocalDateTime reservationTime, Boolean isOnStock, int quantityOfItem, Category category)
            throws ShoppingCartException {

        this.description = description;
        setPrice(price);
        this.reservationTime = reservationTime;
        this.isOnStock = isOnStock;
        this.quantityOfItem = quantityOfItem;
        this.category = category;
    }

    public Item(String description, BigDecimal price) throws ShoppingCartException {
        this(description,price, LocalDateTime.now(),true,1, Category.OTHERS);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }



    public void setPrice(BigDecimal price) throws ShoppingCartException {
        if(price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ShoppingCartException("Price must be greater than zero!" + " Provided price: " + price);
        }
        this.price = price;
    }


    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Boolean getOnStock() {
        return isOnStock;
    }

    public void setOnStock(Boolean onStock) {
        isOnStock = onStock;
    }

    public int getQuantityOfItem() {
        return quantityOfItem;
    }

    public void setQuantityOfItem(int quantityOfItem) {
        this.quantityOfItem = quantityOfItem;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", reservationTime=" + reservationTime +
                ", isOnStock=" + isOnStock +
                ", quantityOfItem=" + quantityOfItem +
                ", category=" + category + "\n" + '}';
        }


}


