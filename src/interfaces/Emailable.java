package interfaces;

import entity.BookEntity;
import entity.CustomerEntity;

public interface Emailable {
    default void sendByEmail(BookEntity book, CustomerEntity customer) {
        System.out.printf("Book: %s sent to customer: %s, at Email: %s%n", book.getTitle(), customer.getName(), customer.getEmail());
    }
}
