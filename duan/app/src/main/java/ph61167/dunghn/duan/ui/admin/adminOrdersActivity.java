package ph61167.dunghn.duan.ui.admin;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ph61167.dunghn.duan.R;
import ph61167.dunghn.duan.data.model.AdminOrderEntity;
import ph61167.dunghn.duan.data.remote.ApiClient;
import ph61167.dunghn.duan.data.remote.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminOrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        recyclerView = findViewById(R.id.recyclerAdminOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadOrders();
    }

    private void loadOrders() {
        ApiService api = ApiClient.getService();

        api.getOrders(null).enqueue(new Callback<List<AdminOrderEntity>>() {
            @Override
            public void onResponse(Call<List<AdminOrderEntity>> call, Response<List<AdminOrderEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AdminOrderEntity> list = response.body();
                    recyclerView.setAdapter(new AdminOrdersAdapter(list));
                } else {
                    Toast.makeText(AdminOrdersActivity.this, "Không tải được đơn hàng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdminOrderEntity>> call, Throwable t) {
                Toast.makeText(AdminOrdersActivity.this, "Lỗi API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
