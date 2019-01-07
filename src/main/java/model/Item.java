package model;


public class Item {
    private String username;
    private String title;
    private String description;
    private String price;
    private String date;


    public Item(String username, String title, String description, String price, String date) {
        this.username = username;
        this.title = title;
        this.description = description;
        this.price = price;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }
}
