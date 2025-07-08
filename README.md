# Book Store Management System - Test Cases Documentation

---

## Test Cases and Results

### 1. Adding Books (`add_book()`)

**Description:** Demonstrates adding different types of books to the inventory.

**Implementation:**  
Adds multiple book types (PaperBook, EBook, DemoBook) to the inventory and prints their types.

```java
public void add_book() {
    BookEntity book1 = new PaperBookEntity("ISBN1", "First Book", 2022, 99.99, 4);
    BookEntity book2 = new EBookEntity("ISBN2", "Second Book", 2022, 45.99, "PDF");
    BookEntity book3 = new PaperBookEntity("ISBN3", "Third Book", 2022, 10.99, 5);
    BookEntity book4 = new EBookEntity("ISBN4", "Fourth Book", 2022, 1.99, "Word");
    BookEntity book5 = new DemoBookEntity("ISBN5", "Demo Book", 2020, 150.99);

    BookStoreEntity store = new BookStoreEntity();
    store.addBook(book1);
    store.addBook(book2);
    store.addBook(book3);
    store.addBook(book4);
    store.addBook(book5);

    store.getInventory().forEach((isbn, book) -> {
        System.out.println(book.getClass().getSimpleName());
    });
}
```

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN2
Book store: Added book with ISBN ISBN3
Book store: Added book with ISBN ISBN4
Book store: Added book with ISBN ISBN5
PaperBookEntity
DemoBookEntity
EBookEntity
PaperBookEntity
EBookEntity
```

---

### 2. Removing Books (`remove_book()`)

**Description:** Demonstrates book removal functionality from inventory.

**Implementation:**  
Adds books, removes one by ISBN, prints remaining types.

```java
public void remove_book() {
    BookEntity book1 = new PaperBookEntity("ISBN1", "First Book", 2022, 99.99, 4);
    BookEntity book2 = new EBookEntity("ISBN2", "Second Book", 2022, 45.99, "PDF");
    BookEntity book3 = new PaperBookEntity("ISBN3", "Third Book", 2022, 10.99, 5);
    BookEntity book4 = new EBookEntity("ISBN4", "Fourth Book", 2022, 1.99, "Word");

    BookStoreEntity store = new BookStoreEntity();
    store.addBook(book1);
    store.addBook(book3);
    store.addBook(book2);
    store.addBook(book4);

    store.removeBook("ISBN3");

    store.getInventory().forEach((ISBN, book) -> {
        System.out.println(book.getClass().getSimpleName());
    });
}
```

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN3
Book store: Added book with ISBN ISBN2
Book store: Added book with ISBN ISBN4
PaperBookEntity
EBookEntity
EBookEntity
```

---

### 3. Purchasing Shippable Books (`buy_book_shippable()`)

**Description:** When purchasing a shippable item (Paper Book), it's shipped to the customer's address.

**PDF:** Paper book which have stock and may be shipped

**Implementation:**  
Customer buys a PaperBook, triggers shipping logic.

```java
public void buy_book_shippable() {
    BookEntity book1 = new PaperBookEntity("ISBN1", "First Book", 2022, 99.99, 4);
    BookEntity book2 = new EBookEntity("ISBN2", "Second Book", 2022, 45.99, "PDF");
    BookEntity book3 = new PaperBookEntity("ISBN3", "Third Book", 2022, 10.99, 5);
    BookEntity book4 = new EBookEntity("ISBN4", "Fourth Book", 2022, 1.99, "Word");

    CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com");

    BookStoreEntity bookStore = new BookStoreEntity();
    bookStore.addBook(book1);
    bookStore.addBook(book2);
    bookStore.addBook(book3);
    bookStore.addBook(book4);

    bookStore.buyBook("ISBN3", 3, customer);
}
```

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN2
Book store: Added book with ISBN ISBN3
Book store: Added book with ISBN ISBN4
3 Book: Third Book shipped to customer: Peter, at Address: El-Giza 
Quantum book store: Purchase complete. Amount paid: 32.97
```

---

### 4. Purchasing Emailable Books (`buy_book_emailable()`)

**Description:** When purchasing an emailable book (E-Book), it's sent to the customer's email.

**PDF:** EBook which have a file type and may be sent via email

**Implementation:**  
Customer buys an EBook, triggers email delivery logic.

```java
public void buy_book_emailable() {
    BookEntity book1 = new PaperBookEntity("ISBN1", "First Book", 2022, 99.99, 4);
    BookEntity book2 = new EBookEntity("ISBN2", "Second Book", 2022, 45.99, "PDF");
    BookEntity book3 = new PaperBookEntity("ISBN3", "Third Book", 2022, 10.99, 5);
    BookEntity book4 = new EBookEntity("ISBN4", "Fourth Book", 2022, 1.99, "Word");

    CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com");

    BookStoreEntity bookStore = new BookStoreEntity();
    bookStore.addBook(book1);
    bookStore.addBook(book2);
    bookStore.addBook(book3);
    bookStore.addBook(book4);

    bookStore.buyBook("ISBN2", 5, customer);
}
```

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN2
Book store: Added book with ISBN ISBN3
Book store: Added book with ISBN ISBN4
Book: Second Book sent to customer: Peter, at Email: peter@gmail.com
Quantum book store: Purchase complete. Amount paid: 45.99
```

---

### 5. Attempting to Purchase Demo Books (`buy_demo_book()`)

**Description:** Demonstrates that demo books cannot be purchased.

**PDF:** Showcase/Demo book which is not for sale

**Implementation:**  
Attempts to buy a DemoBook; should throw an exception.

```java
public void buy_dmeo_book() {
    BookEntity book = new DemoBookEntity("ISBN1", "Demo Book", 2020, 150.99);

    CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com");

    BookStoreEntity bookStore = new BookStoreEntity();
    bookStore.addBook(book);

    bookStore.buyBook("ISBN1", 3, customer);
}
```

**Output:**
```
Book store: Added book with ISBN ISBN1
Exception in thread "main" java.lang.IllegalArgumentException: Book not for sale
	at entity.BookStoreEntity.buyBook(BookStoreEntity.java:88)
	at TestCases.buy_demo_book(TestCases.java:88)
	at Main.main(Main.java:4)
```

---

### 6. Removing Outdated Stock (`remove_outdate_stock()`)

**Description:** Removes books that have been published for 4 years or more from the current date.

**PDF:** Remove and return outdated books that passed specifi c number of years

**Implementation:**  
Removes books that are outdated and displays remaining inventory.

```java
    public void remove_outdate_stock(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First Book", 2022, 99.99, 4) ;
        BookEntity book2 = new EBookEntity("ISBN2", "Second Book", 2000, 45.99, "PDF") ;
        BookEntity book3 = new PaperBookEntity("ISBN3", "Third Book", 2019, 10.99, 5) ;
        BookEntity book4 = new EBookEntity("ISBN4", "Fourth Book", 2022, 1.99, "Word") ;

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
```

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN2
Book store: Added book with ISBN ISBN3
Book store: Added book with ISBN ISBN4
**Removed books that has been published since 4 ago**
Quantum book store: Removed outdated book BookEntity{ISBN='ISBN3', Title='Third Book', Year=2019, Price=10.99, Quantity=5}
Quantum book store: Removed outdated book BookEntity{ISBN='ISBN2', Title='Second Book', Year=2000, Price=45.99, File Type=PDF}
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, Quantity=4}
BookEntity{ISBN='ISBN4', Title='Fourth Book', Year=2022, Price=1.99, File Type=Word}

```

---

### 7. Quantity Reduction on Purchase (`buy_book_reduce_quantity()`)

**Description:** When a customer buys a book, the quantity in inventory is automatically reduced.

**PDF:** Reduces the quantity of the book from the inventory, throw error if not available + [ Send Paper book to the ShippingService with the address provided (no implementation required) || Send EBook to MailService with the email provided (no implementation required) ]

**Implementation:**  
Buys a quantity of a paper book, checks inventory before and after.

```java
    public void buy_book_reduce_quantity(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First Book", 2022, 99.99, 4) ;
        BookEntity book2 = new EBookEntity("ISBN2", "Second Book", 2022, 45.99, "PDF") ;

        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);
        bookStore.addBook(book2);

        bookStore.displayInventory();
        bookStore.buyBook("ISBN1", 3, customer) ;
        bookStore.buyBook("ISBN2", 3, customer) ;
        System.out.println("\n Notice that the quantity of ISBN1 has been reduced");
        bookStore.displayInventory();
    }
```

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN2
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, Quantity=4}
BookEntity{ISBN='ISBN2', Title='Second Book', Year=2022, Price=45.99, File Type=PDF}

3 Book: First Book shipped to customer: Peter, at Address: El-Giza 
Quantum book store: Purchase complete. Amount paid: 299.96999999999997
Book: Second Book sent to customer: Peter, at Email: peter@gmail.com
Quantum book store: Purchase complete. Amount paid: 45.99

 Notice that the quantity of ISBN1 has been reduced
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, Quantity=1}
BookEntity{ISBN='ISBN2', Title='Second Book', Year=2022, Price=45.99, File Type=PDF}

```

---

### 8. Updating Book Price (`update_book_price()`)

**Description:** Updates the price of a book in the inventory based on ISBN.

**Implementation:**  
Buys books, updates price, verifies with display.

```java
    public void update_book_price(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First Book", 2022, 99.99, 4) ;
        BookEntity book2 = new EBookEntity("ISBN2", "Second Book", 2023, 1.99, "PDF") ;


        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);
        bookStore.addBook(book2);
        bookStore.displayInventory();
        bookStore.buyBook("ISBN1", 2, customer) ;
        bookStore.buyBook("ISBN2", 5, customer) ;
        bookStore.displayInventory();
        System.out.println("Update price");
        bookStore.updatePrice("ISBN1", 150);
        bookStore.updatePrice("ISBN2", 50);
        bookStore.displayInventory();
    }
```

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN2
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, Quantity=4}
BookEntity{ISBN='ISBN2', Title='Second Book', Year=2023, Price=1.99, File Type=PDF}

2 Book: First Book shipped to customer: Peter, at Address: El-Giza 
Quantum book store: Purchase complete. Amount paid: 199.98
Book: Second Book sent to customer: Peter, at Email: peter@gmail.com
Quantum book store: Purchase complete. Amount paid: 1.99
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, Quantity=2}
BookEntity{ISBN='ISBN2', Title='Second Book', Year=2023, Price=1.99, File Type=PDF}

Update price
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=150.0, Quantity=2}
BookEntity{ISBN='ISBN2', Title='Second Book', Year=2023, Price=50.0, File Type=PDF}


```

---

### 9. Updating Book Quantity (`update_book_quantity()`)

**Description:** Updates the quantity of a paper book in inventory.

**Implementation:**  
Buys all stock of a paper book, then increases its quantity.

```java
    public void update_book_quantity(){
        BookEntity book1 = new PaperBookEntity("ISBN1", "First Book", 2022, 99.99, 4) ;
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
```

**Output:**
```
Book store: Added book with ISBN ISBN1
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, Quantity=4}

4 Book: First Book shipped to customer: Peter, at Address: El-Giza 
Quantum book store: Purchase complete. Amount paid: 399.96
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, Quantity=0}

Update Quantity
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, Quantity=3}


```

---

### 10. Updating E-Book File Type (`update_book_file_type()`)

**Description:** Updates the file type of an E-Book in inventory.

**Implementation:**  
Buys an EBook, then changes its file type.

```java
public void update_book_file_type(){
        BookEntity book1 = new EBookEntity("ISBN1", "First Book", 2022, 99.99, "PDF") ;
        CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com") ;

        BookStoreEntity bookStore = new BookStoreEntity() ;
        bookStore.addBook(book1);
        bookStore.displayInventory();
        bookStore.buyBook("ISBN1", 2, customer) ;
        System.out.println("Update File Type");
        bookStore.updateFileType("ISBN1", "Word");
        bookStore.displayInventory();


    }
```

**Output:**
```
Book store: Added book with ISBN ISBN1
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, File Type=PDF}

Book: First Book sent to customer: Peter, at Email: peter@gmail.com
Quantum book store: Purchase complete. Amount paid: 99.99
Update File Type
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, File Type=Word}

```

---

## Error Handling Test Cases

### 11. Insufficient Stock (`buy_book_with_no_enough_stock()`)

**Description:** Attempting to buy more books than available in inventory.

**Implementation:**  
Attempts to buy more than available; should throw an exception.

```java
public void buy_book_with_no_enough_stock() {
    BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2022, 99.99, 4);

    CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com");

    BookStoreEntity bookStore = new BookStoreEntity();
    bookStore.addBook(book1);

    bookStore.buyBook("ISBN1", 5, customer);
}
```

**Output:**
```
Book store: Added book with ISBN ISBN1
Exception in thread "main" java.lang.IllegalArgumentException: Not enough of this book in the inventory
	at entity.BookStoreEntity.buyBook(BookStoreEntity.java:98)
	at TestCases.buy_book_with_no_enough_stock(TestCases.java:205)
	at Main.main(Main.java:4)

```

---

### 12. Invalid Quantity Update (`refuse_update_book_quantity()`)

**Description:** Attempting to update quantity for an E-Book (not allowed).

**Implementation:**  
Tries to update quantity of an EBook; should throw an exception.

```java
    public void refuse_update_book_quantity(){
        BookEntity book1 = new EBookEntity("ISBN1", "First Book", 2022, 99.99, "PDF") ;
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
```

**Output:**
```
Book store: Added book with ISBN ISBN1
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, File Type=PDF}

Book: First Book sent to customer: Peter, at Email: peter@gmail.com
Quantum book store: Purchase complete. Amount paid: 99.99
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, File Type=PDF}

Update Quantity
Exception in thread "main" java.lang.IllegalArgumentException: This book isn't of type Paper Book
	at entity.BookStoreEntity.updateQuantity(BookStoreEntity.java:38)
	at TestCases.refuse_update_book_quantity(TestCases.java:166)
	at Main.main(Main.java:4)

```

---

### 13. Invalid File Type Update (`refuse_update_book_file_type()`)

**Description:** Attempting to update file type for a Paper Book (not allowed).

**Implementation:**  
Tries to update file type of a PaperBook; should throw an exception.

```java
public void refuse_update_book_file_type() {
    BookEntity book1 = new PaperBookEntity("ISBN1", "First Book", 2022, 99.99, 3);
    CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com");

    BookStoreEntity bookStore = new BookStoreEntity();
    bookStore.addBook(book1);
    bookStore.displayInventory();
    bookStore.buyBook("ISBN1", 2, customer);
    System.out.println("Update File Type");
    bookStore.updateFileType("ISBN1", "Word");
    bookStore.displayInventory();
}
```

**Output:**
```
Book store: Added book with ISBN ISBN1
**Inventory**
BookEntity{ISBN='ISBN1', Title='First Book', Year=2022, Price=99.99, Quantity=3}

2 Book: First Book shipped to customer: Peter, at Address: El-Giza 
Quantum book store: Purchase complete. Amount paid: 199.98
Update File Type
Exception in thread "main" java.lang.IllegalArgumentException: This book isn't of type EBook
	at entity.BookStoreEntity.updateFileType(BookStoreEntity.java:48)
	at TestCases.refuse_update_book_file_type(TestCases.java:192)
	at Main.main(Main.java:4)

```

---

### 14. Non-existent Book Purchase (`buy_book_not_exist()`)

**Description:** Attempting to buy a book with an ISBN that doesn't exist in inventory.

**Implementation:**  
Buys a book with an invalid ISBN; should throw an exception.

```java
public void buy_book_not_exist() {
    BookEntity book1 = new PaperBookEntity("ISBN1", "First", 2022, 99.99, 4);

    CustomerEntity customer = new CustomerEntity("Peter", "El-Giza", "peter@gmail.com");

    BookStoreEntity bookStore = new BookStoreEntity();
    bookStore.addBook(book1);

    bookStore.buyBook("ISBN3", 5, customer);
}
```

**Output:**
```
Book store: Added book with ISBN ISBN1
Exception in thread "main" java.lang.IllegalArgumentException: Book not found
	at entity.BookStoreEntity.buyBook(BookStoreEntity.java:87)
	at TestCases.buy_book_not_exist(TestCases.java:216)
	at Main.main(Main.java:4)

```
