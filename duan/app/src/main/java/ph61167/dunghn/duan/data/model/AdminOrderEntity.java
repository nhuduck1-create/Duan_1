package ph61167.dunghn.duan.data.model;

public class AdminOrderEntity {
    private Long id;
    private Long productId;
    private Long userId;
    private int quantity;
    private String status;
    private String createdAt;
    // getters/setters
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public Long getProductId(){return productId;}
    public void setProductId(Long productId){this.productId=productId;}
    public Long getUserId(){return userId;}
    public void setUserId(Long userId){this.userId=userId;}
    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity=quantity;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
    public String getCreatedAt(){return createdAt;}
    public void setCreatedAt(String createdAt){this.createdAt=createdAt;}
}
