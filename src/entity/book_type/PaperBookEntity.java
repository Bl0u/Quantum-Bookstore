package entity.book_type;

import entity.BookEntity;
import interfaces.HardCopy;
import interfaces.Shippable;

public class PaperBookEntity extends BookEntity implements HardCopy, Shippable {
    private int quantity;


    public PaperBookEntity(String isbn, String title, int yearOfPublishing, double price, int quantity) {
        super(isbn, title, yearOfPublishing, price);
        this.quantity = quantity;
    }

    @Override
    public Boolean isPurchasable() {
        return true;
    }

    @Override
    public void setQuantity(int quantity) {
        if(quantity >= 0)
            this.quantity = quantity;
        else
            throw new IllegalArgumentException("Quantity must be more than zero!");
    }

    @Override
    public int getQuantity() {
        return this.quantity;
    }

    public Boolean isAvailable(int quantity){
        return this.quantity >= quantity;
    }

    public String toString() {
        return "BookEntity{" +
                "ISBN='" + getIsbn() + '\'' +
                ", Title='" + getTitle() + '\'' +
                ", Year=" + getYearOfPublishing() +
                ", Price=" + getPrice() +
                ", Quantity=" + getQuantity() +
                '}';
    }
}
