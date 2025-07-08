import java.time.LocalDate;
enum Type{
    PAPER, EBOOK, DEMO, NEW
}
public class Book {
    private String ISBN, title;
    private LocalDate publish_year;
    private double price;

    private Type book_type;
    private int quantity;
    private String file_type;

    public Book(String ISBN, String title, LocalDate publish_year, double price, Type book_type, int quantity) {
        this.ISBN = ISBN.toLowerCase();
        this.title = title;
        this.publish_year = publish_year;
        this.price = price;
        this.book_type = book_type;
        this.quantity = quantity;
    }

    public Book(String ISBN, String title, LocalDate publish_year, double price, Type book_type, String file_type) {
        this.ISBN = ISBN;
        this.title = title;
        this.publish_year = publish_year;
        this.price = price;
        this.book_type = book_type;
        this.file_type = file_type;
    }

    public Book(String ISBN, String title, LocalDate publish_year, double price, Type book_type) {
        this.ISBN = ISBN;
        this.title = title;
        this.publish_year = publish_year;
        this.price = price;
        this.book_type = book_type;
    }

    public String getISBN() {
        return ISBN;
    }

    public LocalDate getPublish_year() {
        return publish_year;
    }

    public double getPrice() {
        return price;
    }

    public Type getBook_type() {
        return book_type;
    }

    public int getQuantity() {
        return quantity;
    }



    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getTitle() {
        return title;
    }
}
