package com.example.loginsignup;

public class BookData
{
    private  String title;
    private String author;
    private String date;
    private String pagesNumber;
    private String language;

    public BookData (String title,String author,String date,String language,String pagesNumber){
        this.title = title;
        this.author = author;
        this.date = date ;
        this.pagesNumber = pagesNumber ;
        this.language = language ;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(String pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", pagesNumber=" + pagesNumber +
                ", language='" + language + '\'' +
                '}';
    }
}
