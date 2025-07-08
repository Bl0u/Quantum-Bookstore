# Book Store Management System - Test Cases Documentation
---

## Test Cases and Results

### 1. Adding Books (`add_book()`)

**Description:** Demonstrates adding different types of books to the inventory.

**Implementation:** Each successful addition prints confirmation and displays book types.

```java
System.out.println("Book store: Added book with ISBN " + isbn);
store.getInventory().forEach((isbn, book) -> {
    System.out.println(book.getClass().getSimpleName());
});
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

**Implementation:** Same logic as add_book with removal confirmation.

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

**PDF Reference:** *"Paper book which have stock and may be shipped"*

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN2
Book store: Added book with ISBN ISBN3
Book store: Added book with ISBN ISBN4
3 Book: Third shipped to customer: Peter, at Address: El-Giza 
Quantum book store: Purchase complete. Amount paid: 32.97
```

---

### 4. Purchasing Emailable Books (`buy_book_emailable()`)

**Description:** When purchasing an emailable book (E-Book), it's sent to the customer's email.

**PDF Reference:** *"EBook which have a filetype and may be sent via email"*

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN2
Book store: Added book with ISBN ISBN3
Book store: Added book with ISBN ISBN4
Book: Second sent to customer: Peter, at Email: peter@gmail.com
Quantum book store: Purchase complete. Amount paid: 45.99
```

---

### 5. Attempting to Purchase Demo Books (`buy_demo_book()`)

**Description:** Demonstrates that demo books cannot be purchased.

**PDF Reference:** *"Showcase/Demo book which is not for sale"*

**Output:**
```
Book store: Added book with ISBN ISBN1
Exception in thread "main" java.lang.IllegalArgumentException: Book not for sale
	at entity.BookStoreEntity.buyBook(BookStoreEntity.java:88)
	at TestCases.buy_demo_book(TestCases.java:88)
	at Main.main(Main.java:4)
```

---

### 6. Removing Outdated Stock (`remove_outdated_stock()`)

**Description:** Removes books that have been published for 4 years or more from the current date.

**Logic:** Books older than the specified age threshold are considered outdated and removed.

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN2
Book store: Added book with ISBN ISBN3
Book store: Added book with ISBN ISBN4
**Removed books that have been published since 4 years ago**
Quantum book store: Removed outdated book BookEntity{ISBN='ISBN1', Title='First', Year=2000, Price=99.99, Quantity=4}
Quantum book store: Removed outdated book BookEntity{ISBN='ISBN4', Title='Fourth', Year=2019, Price=1.99, File Type=Word}
**Inventory**
BookEntity{ISBN='ISBN3', Title='Third', Year=2023, Price=10.99, Quantity=5}
BookEntity{ISBN='ISBN2', Title='Second', Year=2022, Price=45.99, File Type=PDF}
```

---

### 7. Quantity Reduction on Purchase (`buy_book_reduce_quantity()`)

**Description:** When a customer buys a book, the quantity in inventory is automatically reduced.

**Test Case:** Customer buys 3 copies of book with "ISBN1". Initial quantity: 4, Final quantity: 1.

**Output:**
```
Book store: Added book with ISBN ISBN1
Book store: Added book with ISBN ISBN4
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, Quantity=4}
BookEntity{ISBN='ISBN4', Title='Fourth', Year=2022, Price=1.99, File Type=Word}

3 Book: First shipped to customer: Peter, at Address: El-Giza 
Quantum book store: Purchase complete. Amount paid: 299.97

**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, Quantity=1}
BookEntity{ISBN='ISBN4', Title='Fourth', Year=2022, Price=1.99, File Type=Word}
```

---

### 8. Updating Book Price (`update_book_price()`)

**Description:** Updates the price of a book in the inventory based on ISBN.

**Output:**
```
Book store: Added book with ISBN ISBN1
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, Quantity=4}

2 Book: First shipped to customer: Peter, at Address: El-Giza 
Quantum book store: Purchase complete. Amount paid: 199.98
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, Quantity=2}

Update price
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=150.0, Quantity=2}
```

---

### 9. Updating Book Quantity (`update_book_quantity()`)

**Description:** Updates the quantity of a paper book in inventory.

**Output:**
```
Book store: Added book with ISBN ISBN1
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, Quantity=4}

4 Book: First shipped to customer: Peter, at Address: El-Giza 
Quantum book store: Purchase complete. Amount paid: 399.96
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, Quantity=0}

Update Quantity
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, Quantity=3}
```

---

### 10. Updating E-Book File Type (`update_book_file_type()`)

**Description:** Updates the file type of an E-Book in inventory.

**Output:**
```
Book store: Added book with ISBN ISBN1
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, File Type=PDF}

Book: First sent to customer: Peter, at Email: peter@gmail.com
Quantum book store: Purchase complete. Amount paid: 99.99
Update File Type
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, File Type=Word}
```

---

## Error Handling Test Cases

### 11. Insufficient Stock (`buy_book_with_no_enough_stock()`)

**Description:** Attempting to buy more books than available in inventory.

**Output:**
```
Book store: Added book with ISBN ISBN1
Exception in thread "main" java.lang.IllegalArgumentException: Not enough of this book in the inventory
	at entity.BookStoreEntity.buyBook(BookStoreEntity.java:94)
	at TestCases.buy_book_with_no_enough_stock(TestCases.java:173)
	at Main.main(Main.java:4)
```

---

### 12. Invalid Quantity Update (`refuse_update_book_quantity()`)

**Description:** Attempting to update quantity for an E-Book (not allowed).

**Output:**
```
Book store: Added book with ISBN ISBN1
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, File Type=PDF}

Book: First sent to customer: Peter, at Email: peter@gmail.com
Quantum book store: Purchase complete. Amount paid: 99.99
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, File Type=PDF}

Update Quantity
Exception in thread "main" java.lang.IllegalArgumentException: This book isn't of type Paper Book
	at entity.BookStoreEntity.updateQuantity(BookStoreEntity.java:38)
	at TestCases.refuse_update_book_quantity(TestCases.java:161)
	at Main.main(Main.java:4)
```

---

### 13. Invalid File Type Update (`refuse_update_book_file_type()`)

**Description:** Attempting to update file type for a Paper Book (not allowed).

**Output:**
```
Book store: Added book with ISBN ISBN1
**Inventory**
BookEntity{ISBN='ISBN1', Title='First', Year=2022, Price=99.99, Quantity=3}

2 Book: First shipped to customer: Peter, at Address: El-Giza 
Quantum book store: Purchase complete. Amount paid: 199.98
Update File Type
Exception in thread "main" java.lang.IllegalArgumentException: This book isn't of type EBook
	at entity.BookStoreEntity.updateFileType(BookStoreEntity.java:48)
	at TestCases.refuse_update_book_file_type(TestCases.java:187)
	at Main.main(Main.java:4)
```

---

### 14. Non-existent Book Purchase (`buy_book_not_exist()`)

**Description:** Attempting to buy a book with an ISBN that doesn't exist in inventory.

**Output:**
```
Book store: Added book with ISBN ISBN1
Exception in thread "main" java.lang.IllegalArgumentException: Book not found
	at entity.BookStoreEntity.buyBook(BookStoreEntity.java:87)
	at TestCases.buy_book_not_exist(TestCases.java:214)
	at Main.main(Main.java:4)
```

---

## Summary

The book store management system successfully demonstrates:

- ✅ **Book Management**: Adding and removing books from inventory
- ✅ **Purchase System**: Different handling for shippable vs. emailable books
- ✅ **Inventory Control**: Automatic quantity reduction and stock management
- ✅ **Update Operations**: Price, quantity, and file type modifications
- ✅ **Error Handling**: Proper validation and exception throwing
- ✅ **Business Logic**: Demo books restriction and type-specific
