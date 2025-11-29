package ph61167.dunghn.duan.data.model;

public class OrderRequest {

    private String productId; // đổi từ long sang String
    private long userId;
    private int quantity;

    public OrderRequest(String productId, long userId, int quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public long getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }
}
