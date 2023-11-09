package ru.marat.project1_library.Models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int bookId;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 15, message = "Name should be between 2 and 30 ch")
    private String bookName;
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 15, message = "Author should be between 2 and 30 ch")
    private String bookAuthor;

    public Book(int bookId, String bookName, String bookAuthor, int bookYear) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookYear = bookYear;
    }

    @Min(value = 0, message = "Year should be greater than 0 ")
    @Max(value = 2023, message = "Year should be less than 100 ")
    private int bookYear;

    public Book(){


    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}
