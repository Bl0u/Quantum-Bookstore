package entity;

import entity.book_type.EBookEntity;
import entity.book_type.PaperBookEntity;
import interfaces.Emailable;
import interfaces.Shippable;
import java.time.Year;
import java.util.*;

public class BookStoreEntity {
    private Map<String, BookEntity> inventory = new HashMap<>();

    public Map<String, BookEntity> getInventory() {
        return inventory;
    }

    public void displayInventory(){
        Iterator<Map.Entry<String, BookEntity>> it = inventory.entrySet().iterator();
            System.out.println("**Inventory**");
            while (it.hasNext()){
            BookEntity book = it.next().getValue();
            System.out.println(book);
        }
            System.out.println("");
    }
    public void updatePrice(String ISBN, double price){
        if (inventory.containsKey(ISBN)){
            BookEntity book = inventory.get(ISBN);
            book.setPrice(price);
        }
    }
    public void updateQuantity(String ISBN, int quantity){
        if (inventory.containsKey(ISBN)){
            BookEntity book = inventory.get(ISBN);
            if (book instanceof PaperBookEntity){
                ((PaperBookEntity) book).setQuantity(quantity);
            }
        }
    }
    public void updateFileType(String ISBN, String fileType){
        if (inventory.containsKey(ISBN)){
            BookEntity book = inventory.get(ISBN);
            if (book instanceof EBookEntity){
                ((EBookEntity) book).setFileType(fileType);
            }
        }
    }
    public void addBook(BookEntity book) {
        String isbn = book.getIsbn();

        if (inventory.containsKey(isbn)) {
            throw new IllegalArgumentException("Book already exists");
        } else {
            inventory.put(isbn, book);
            System.out.println("Book store: Added book with ISBN " + isbn);
        }
    }


    public BookEntity removeBook(String isbn) {
        return inventory.remove(isbn);
    }


    public List<BookEntity> removeOutdatedBooks(int maxAge) {
        int currentYear = Year.now().getValue();
        List<BookEntity> removed = new ArrayList<>();

        Iterator<Map.Entry<String, BookEntity>> it = inventory.entrySet().iterator();
        while (it.hasNext()) {
            BookEntity book = it.next().getValue();
            if ((currentYear - book.getYearOfPublishing()) <= maxAge) {
                removed.add(book);
                it.remove();
                System.out.println("Quantum book store: Removed outdated book " + book);
            }
        }
        return removed;
    }

    public double buyBook(String isbn, int qty, CustomerEntity customer) {
        if (!inventory.containsKey(isbn)) {
            throw new IllegalArgumentException("Book not found");
        }

        BookEntity book = inventory.get(isbn);
        if (!book.isPurchasable()) {
            throw new IllegalArgumentException("Book not for sale");
        }

        if (book instanceof PaperBookEntity) {
            PaperBookEntity paperBook = (PaperBookEntity) book;
            if (!paperBook.isAvailable(qty)) {
                throw new IllegalArgumentException("Not enough of this book in the inventory");
            }
            paperBook.reduceStock(qty);
        }


        double totalPrice = book.getPrice();
        if (book instanceof Shippable){
            ((Shippable) book).shipToAddress(book, customer, qty);
            totalPrice *= qty;
        }
        else if (book instanceof Emailable){
            ((Emailable) book).sendByEmail(book, customer);

        }

        System.out.println("Quantum book store: Purchase complete. Amount paid: " + totalPrice);
        return totalPrice;
    }
}
