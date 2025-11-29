package ph61167.dunghn.duan.data.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("_id")
    private String id;

    private String name;
    private String description;
    private double price;
    private int stock;
    private String image;
    private Category category;

    // ====== CONSTRUCTOR ======

    public Product() {
    }

    public Product(String name, String description, double price, int stock, String image, String categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.category = new Category(categoryId);
    }

    // ====== GETTER ======

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getImage() {
        return image;
    }

    public Category getCategory() {
        return category;
    }

    // ====== SETTER ======

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String categoryId) {
        this.category = new Category(categoryId);
    }

    // ====== CATEGORY CLASS ======

    public static class Category {

        @SerializedName("_id")
        private String id;

        private String name;

        public Category() {
        }

        public Category(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
