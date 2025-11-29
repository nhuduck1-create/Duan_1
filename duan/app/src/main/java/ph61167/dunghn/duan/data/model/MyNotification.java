package ph61167.dunghn.duan.data.model;

public class MyNotification {
    private Long id;
    private Long userId;
    private String message;
    private String createdAt;
    private boolean readed;

    public Long getId(){return id;}
    public Long getUserId(){return userId;}
    public String getMessage(){return message;}
    public String getCreatedAt(){return createdAt;}
    public boolean isReaded(){return readed;}
}
