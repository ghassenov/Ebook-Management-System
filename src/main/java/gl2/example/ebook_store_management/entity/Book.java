package gl2.example.ebook_store_management.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String author;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;  // Changed from String to BigDecimal

    // Constructors
    public Book() {}

    public Book(String name, String author, BigDecimal price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // Helper method for price formatting
    public String getFormattedPrice() {
        return "TND " + price;
    }
}