import entity.BookEntity;
import entity.BookStoreEntity;
import entity.CustomerEntity;
import entity.book_type.DemoBookEntity;
import entity.book_type.EBookEntity;
import entity.book_type.PaperBookEntity;
import java.util.List;

public class TestCases {

    public void add_book(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2022, 99.99, 4) ;
        BookEntity book2 = new EBookEntity("ISBN2", "Second", 2022, 45.99, "PDF") ;
        BookEntity book3 = new PaperBookEntity("ISBN3", "Third", 2022, 10.99, 5) ;
        BookEntity book4 = new EBookEntity("ISBN4", "Foruth", 2022, 1.99, "Word") ;
        BookEntity book5 = new DemoBookEntity("ISBN5", "Demo", 2020, 150.99) ;


        BookStoreEntity store = new BookStoreEntity() ;
        store.addBook(book1);
        store.addBook(book2);
        store.addBook(book3);
        store.addBook(book4);
        store.addBook(book5);

        store.getInventory().forEach((isbn, book )-> {
            System.out.println(book.getClass().getSimpleName());
        });
    }
    public void remove_book(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2022, 99.99, 4) ;
        BookEntity book2 = new EBookEntity("ISBN2", "Second", 2022, 45.99, "PDF") ;
        BookEntity book3 = new PaperBookEntity("ISBN3", "Third", 2022, 10.99, 5) ;
        BookEntity book4 = new EBookEntity("ISBN4", "Foruth", 2022, 1.99, "Word") ;

        BookStoreEntity store = new BookStoreEntity() ;
        store.addBook(book1);
        store.addBook(book3);
        store.addBook(book2);
        store.addBook(book4);

        store.removeBook("ISBN3") ;

        store.getInventory().forEach((ISBN, book) -> {
            System.out.println(book.getClass().getSimpleName());
        });
    }
    public void buy_book_shippable(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2022, 99.99, 4) ;
        BookEntity book2 = new EBookEntity("ISBN2", "Second", 2022, 45.99, "PDF") ;
        BookEntity book3 = new PaperBookEntity("ISBN3", "Third", 2022, 10.99, 5) ;
        BookEntity book4 = new EBookEntity("ISBN4", "Foruth", 2022, 1.99, "Word") ;

        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);
        bookStore.addBook(book2);
        bookStore.addBook(book3);
        bookStore.addBook(book4);

        bookStore.buyBook("ISBN3", 3, customer) ;
    }
    public void buy_book_emailable(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2022, 99.99, 4) ;
        BookEntity book2 = new EBookEntity("ISBN2", "Second", 2022, 45.99, "PDF") ;
        BookEntity book3 = new PaperBookEntity("ISBN3", "Third", 2022, 10.99, 5) ;
        BookEntity book4 = new EBookEntity("ISBN4", "Foruth", 2022, 1.99, "Word") ;

        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);
        bookStore.addBook(book2);
        bookStore.addBook(book3);
        bookStore.addBook(book4);

        bookStore.buyBook("ISBN2", 5, customer) ;
    }
    public void buy_dmeo_book(){
        BookEntity book = new DemoBookEntity("ISBN1", "Demo", 2020, 150.99) ;

        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book);

        bookStore.buyBook("ISBN1", 3, customer) ;
    }
    public void remove_outdate_stock(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2000, 99.99, 4) ;
        BookEntity book2 = new EBookEntity("ISBN2", "Second", 2022, 45.99, "PDF") ;
        BookEntity book3 = new PaperBookEntity("ISBN3", "Third", 2023, 10.99, 5) ;
        BookEntity book4 = new EBookEntity("ISBN4", "Foruth", 2019, 1.99, "Word") ;

        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);
        bookStore.addBook(book2);
        bookStore.addBook(book3);
        bookStore.addBook(book4);

        System.out.println("**Removed books that has been published since 4 ago**");
        List<BookEntity> outdatedBooks = bookStore.removeOutdatedBooks(4);
        bookStore.displayInventory();
    }
    public void buy_book_reduce_quantity(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2022, 99.99, 4) ;
        BookEntity book2 = new EBookEntity("ISBN4", "Foruth", 2022, 1.99, "Word") ;

        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);
        bookStore.addBook(book2);

        bookStore.displayInventory();
        bookStore.buyBook("ISBN1", 3, customer) ;
        System.out.println("\n Notice that the quantity of ISBN1 has been reduced");
        bookStore.displayInventory();
    }
    public void update_book_price(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2022, 99.99, 4) ;
        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);
        bookStore.displayInventory();
        bookStore.buyBook("ISBN1", 2, customer) ;
        bookStore.displayInventory();
        System.out.println("Update price");
        bookStore.updatePrice("ISBN1", 150);
        bookStore.displayInventory();


    }
    public void update_book_quantity(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2022, 99.99, 4) ;
        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);
        bookStore.displayInventory();
        bookStore.buyBook("ISBN1", 4, customer) ;
        bookStore.displayInventory();
        System.out.println("Update Quantity");
        bookStore.updateQuantity("ISBN1", 3);
        bookStore.displayInventory();
    }
    public void update_book_file_type(){
        BookEntity book1 = new EBookEntity("ISBN1", "First", 2022, 99.99, "PDF") ;
        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);
        bookStore.displayInventory();
        bookStore.buyBook("ISBN1", 2, customer) ;
        System.out.println("Update File Type");
        bookStore.updateFileType("ISBN1", "Word");
        bookStore.displayInventory();


    }
    public void buy_book_with_no_enough_stock(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2022, 99.99, 4) ;

        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);

        bookStore.buyBook("ISBN1", 5, customer) ;

    }

}
