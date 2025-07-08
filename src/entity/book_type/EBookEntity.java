package entity.book_type;

import entity.BookEntity;
import interfaces.Emailable;
import interfaces.SoftCopy;

public class EBookEntity extends BookEntity implements SoftCopy, Emailable {
    private String fileType;

    public EBookEntity(String isbn, String title, int yearOfPublishing, double price, String fileType) {
        super(isbn, title, yearOfPublishing, price);
        this.fileType = fileType;
    }

    @Override
    public Boolean isPurchasable() {
        return true;
    }


    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String getFileType() {
        return this.fileType;
    }

    public String toString() {
        return "BookEntity{" +
                "ISBN='" + getIsbn() + '\'' +
                ", Title='" + getTitle() + '\'' +
                ", Year=" + getYearOfPublishing() +
                ", Price=" + getPrice() +
                ", File Type=" + getFileType() +
                '}';
    }
}
