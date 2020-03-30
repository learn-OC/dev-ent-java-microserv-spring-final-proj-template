package com.hinkmond.finalproj;

public class AddBookDetail {
    int bookid;
    String title;
    String author;
    String category;
    Double cost;
    int inventory;
    int shelfNum;


    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setShelfNum(int shelfNum) {
        this.shelfNum = shelfNum;
    }


    public int getBookid() {
        return bookid;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public Double getCost() {
        return cost;
    }

    public int getInventory() {
        return inventory;
    }

    public int getShelfNum() {
        return shelfNum;
    }


}
