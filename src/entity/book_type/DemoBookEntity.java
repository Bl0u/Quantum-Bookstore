package entity.book_type;

import entity.BookEntity;
import interfaces.HardCopy;

public class DemoBookEntity extends BookEntity implements HardCopy {
    private int quantity;


    public DemoBookEntity(String isbn, String title, int yearOfPublishing, double price) {
        super(isbn, title, yearOfPublishing, price);
    }

    @Override
    public Boolean isPurchasable() {
        return false;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getQuantity() {
        return 0;
    }
}
