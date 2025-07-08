package entity;

public abstract class BookEntity {
    private final String isbn;
    private final String title;
    private final int yearOfPublishing;
    private double price ;

    public BookEntity(String isbn, String title, int yearOfPublishing, double price) {
        this.isbn = isbn;
        this.title = title;
        this.yearOfPublishing = yearOfPublishing;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract Boolean isPurchasable();
}
