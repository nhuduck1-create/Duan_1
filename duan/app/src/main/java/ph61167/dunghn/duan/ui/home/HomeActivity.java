package ph61167.dunghn.duan.ui.home;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.*;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;
import java.util.Map;

import ph61167.dunghn.duan.data.local.SessionManager;
import ph61167.dunghn.duan.data.model.MyNotification;
import ph61167.dunghn.duan.data.model.Product;
import ph61167.dunghn.duan.data.model.OrderRequest;
import ph61167.dunghn.duan.data.remote.ApiClient;
import ph61167.dunghn.duan.data.remote.response.BaseResponse;
import ph61167.dunghn.duan.databinding.ActivityHomeBinding;
import ph61167.dunghn.duan.ui.auth.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.core.app.NotificationCompat;




public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private SessionManager sessionManager;
    private ProductAdapter productAdapter;
    private Handler handler;
    private long demoUserId = 1L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sessionManager = new SessionManager(this);

        if (!sessionManager.isLoggedIn()) {
            navigateToLogin();
            return;
        }

        setupHeader();
        setupRecyclerView();
        setupAdminButton();
        fetchProducts();
        setupNotifications();
    }

    private void setupHeader() {
        String greeting = sessionManager.getUserName() != null
                ? "Xin chào, " + sessionManager.getUserName()
                : "Xin chào!";
        binding.tvUserGreeting.setText(greeting);

        binding.ivLogout.setOnClickListener(v -> {
            sessionManager.clearSession();
            navigateToLogin();
        });
    }

<<<<<<< HEAD
=======
    private void setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == ph61167.dunghn.duan.R.id.nav_home) {
                // Đã ở trang chủ
                return true;
            } else if (itemId == ph61167.dunghn.duan.R.id.nav_cart) {
                // TODO: Chuyển đến trang giỏ hàng
                Toast.makeText(this, "Tính năng giỏ hàng đang phát triển", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == ph61167.dunghn.duan.R.id.nav_orders) {
                Intent intent = new Intent(this, ph61167.dunghn.duan.ui.orders.OrdersActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == ph61167.dunghn.duan.R.id.nav_wallet) {
                // TODO: Chuyển đến trang ví
                Toast.makeText(this, "Tính năng ví đang phát triển", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == ph61167.dunghn.duan.R.id.nav_account) {
                Intent intent = new Intent(this, ph61167.dunghn.duan.ui.users.UsersActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

>>>>>>> 3811d5da0d3c81792e2813b505f908b7baf75a76
    private void setupRecyclerView() {
        productAdapter = new ProductAdapter(new ProductAdapter.OnBuyClickListener() {
            @Override
            public void onBuy(Product product) {

                OrderRequest req = new OrderRequest(product.getId(), demoUserId, 1);


                ApiClient.getService()
                        .createOrder(req)
                        .enqueue(new Callback<BaseResponse<Object>>() {
                            @Override
                            public void onResponse(Call<BaseResponse<Object>> call,
                                                   Response<BaseResponse<Object>> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    Toast.makeText(HomeActivity.this,
                                            "Đặt hàng thành công: " + product.getName(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(HomeActivity.this,
                                            "Lỗi đặt hàng",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseResponse<Object>> call, Throwable t) {
                                Toast.makeText(HomeActivity.this,
                                        "Lỗi: " + t.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        binding.rvProducts.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvProducts.setAdapter(productAdapter);
    }

    private void setupAdminButton() {
        if (binding.btnAdmin != null) {
            binding.btnAdmin.setOnClickListener(v ->
                    Toast.makeText(this, "Chuyển đến trang Admin (demo)", Toast.LENGTH_SHORT).show()
            );
        }
    }

    private void fetchProducts() {
        showProductLoading(true);

        ApiClient.getService().getProducts()
                .enqueue(new Callback<BaseResponse<List<Product>>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<List<Product>>> call, Response<BaseResponse<List<Product>>> response) {
                        showProductLoading(false);

                        if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                            productAdapter.setData(response.body().getData());
                            productAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(HomeActivity.this, "Không thể tải sản phẩm", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<List<Product>>> call, Throwable t) {
                        showProductLoading(false);
                        Toast.makeText(HomeActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showProductLoading(boolean isLoading) {
        binding.progressProducts.setVisibility(isLoading ? android.view.View.VISIBLE : android.view.View.GONE);
        binding.rvProducts.setVisibility(isLoading ? android.view.View.INVISIBLE : android.view.View.VISIBLE);
    }

    private void setupNotifications() {
        handler = new Handler(Looper.getMainLooper());
        createNotificationChannel();
        startPollingNotifications();
    }

    private void startPollingNotifications() {
        handler.post(new Runnable() {
            @Override
            public void run() {

                ApiClient.getService().getNotifications(String.valueOf(demoUserId))

                        .enqueue(new Callback<BaseResponse<List<MyNotification>>>() {
                            @Override
                            public void onResponse(Call<BaseResponse<List<MyNotification>>> call,
                                                   Response<BaseResponse<List<MyNotification>>> response) {

                                if (response.isSuccessful()
                                        && response.body() != null
                                        && response.body().isSuccess()
                                        && !response.body().getData().isEmpty()) {

                                    MyNotification n = response.body().getData().get(0);
                                    showLocalNotification(n.getMessage());
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseResponse<List<MyNotification>>> call, Throwable t) {}
                        });

                handler.postDelayed(this, 15000);
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("shop_channel", "Shop Channel", NotificationManager.IMPORTANCE_DEFAULT);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(channel);
        }
    }



    private void showLocalNotification(String text){
        NotificationCompat.Builder b = new NotificationCompat.Builder(this, "shop_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Thông báo từ Shop")
                .setContentText(text)
                .setAutoCancel(true);
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify((int)System.currentTimeMillis(), b.build());
    }




    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
