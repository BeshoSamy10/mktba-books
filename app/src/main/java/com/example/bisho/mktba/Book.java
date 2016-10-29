package com.example.bisho.mktba;

/**
 * Created by bisho on 29-Sep-16.
 */
public class Book {

    private String name;
    private String bookSubject;
    private String copiesNumber;
    private String booksCode;
    private String autherName;
    private String enteringCode;

    public Book (String name, String bookSubject, String copiesNumber, String booksCode, String autherName, String enteringCode) {
        this.name = name;
        this.bookSubject = bookSubject;
        this.copiesNumber = copiesNumber;
        this.booksCode = booksCode;
        this.autherName = autherName;
        this.enteringCode = enteringCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookSubject() {
        return bookSubject;
    }

    public void setBookSubject(String bookSubject) {
        this.bookSubject = bookSubject;
    }

    public String getCopiesNumber() {
        return copiesNumber;
    }

    public void setCopiesNumber(String copiesNumber) {
        this.copiesNumber = copiesNumber;
    }

    public String getBooksCode() {
        return booksCode;
    }

    public void setBooksCode(String booksCode) {
        this.booksCode = booksCode;
    }

    public String getAutherName() {
        return autherName;
    }

    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    public String getEnteringCode() {
        return enteringCode;
    }

    public void setEnteringCode(String enteringCode) {
        this.enteringCode = enteringCode;
    }
}
