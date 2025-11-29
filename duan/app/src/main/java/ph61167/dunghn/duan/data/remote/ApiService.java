package ph61167.dunghn.duan.data.remote;

import java.util.List;
import java.util.Map;

import ph61167.dunghn.duan.data.model.AdminOrderEntity;
import ph61167.dunghn.duan.data.model.MyNotification;
import ph61167.dunghn.duan.data.model.OrderRequest;
import ph61167.dunghn.duan.data.model.Product;
import ph61167.dunghn.duan.data.remote.request.LoginRequest;
import ph61167.dunghn.duan.data.remote.request.RegisterRequest;
import ph61167.dunghn.duan.data.remote.response.AuthData;
import ph61167.dunghn.duan.data.remote.response.BaseResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {

    // ---------- AUTH ----------
    @POST("users/login")
    Call<BaseResponse<AuthData>> login(@Body LoginRequest request);

    @POST("users/register")
    Call<BaseResponse<AuthData>> register(@Body RegisterRequest request);

    // ---------- PRODUCTS ----------
    @GET("products")
    Call<BaseResponse<List<Product>>> getProducts();

    // ---------- ORDERS ----------
    @POST("orders")
    Call<BaseResponse<Object>> createOrder(@Body OrderRequest request);

    @GET("orders")
    Call<BaseResponse<List<AdminOrderEntity>>> getOrders(@Query("userId") String userId);

    @PUT("orders/{id}")
    Call<BaseResponse<Object>> updateOrderStatus(@Path("id") String id, @Body Map<String, String> body);

    // ---------- NOTIFICATIONS ----------
    @GET("notifications/{userId}")
    Call<BaseResponse<List<MyNotification>>> getNotifications(@Path("userId") String userId);
}
