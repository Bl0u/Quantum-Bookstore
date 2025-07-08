package interfaces;

import entity.BookEntity;
import entity.CustomerEntity;

public interface Shippable {
    int getQuantity();
    void setQuantity(int quantity);

    default void reduceStock(int quantity){
        if(getQuantity() >= quantity)
            setQuantity(getQuantity() - quantity);
        else
            throw new RuntimeException("not enough books to be reduced.");
    }

    default void shipToAddress(BookEntity book, CustomerEntity customer, int quantity) {
        System.out.printf("%d Book: %s shipped to customer: %s, at Address: %s \n", quantity, book.getTitle(), customer.getName(), customer.getAddress());
    }
}
