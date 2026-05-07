package bookstore.model;

public class Book {
    int id;
    String title;
    String author;
    String publisher;
    double price;
    int stock;

    public Book(int id, String title, String author, String publisher, double price, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.stock = stock;
    }

    public Book(String title, String author, String publisher, double price, int stock) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.stock = stock;
    }

    public Book(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author +
                ", publisher=" + publisher  + ", price=" + price + ", stock=" + stock + "]";
    }

}
