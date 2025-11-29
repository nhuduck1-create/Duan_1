package ph61167.dunghn.duan.ui.admin;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ph61167.dunghn.duan.R;
import ph61167.dunghn.duan.data.model.OrderRequest;

public class AdminOrdersAdapter extends RecyclerView.Adapter<AdminOrdersAdapter.OrderViewHolder> {

    private final Context context;
    private final List<OrderRequest> list;

    public AdminOrdersAdapter(Context context, List<OrderRequest> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_admin_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderRequest order = list.get(position);

        holder.tvId.setText(order.getProductId());
        holder.tvUser.setText(String.valueOf(order.getUserId()));
        holder.tvStatus.setText("Đã đặt"); // Vì OrderRequest không có status
        holder.tvTotal.setText(order.getQuantity() + " sản phẩm");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvUser, tvStatus, tvTotal;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_order_id);
            tvUser = itemView.findViewById(R.id.tv_order_user);
            tvStatus = itemView.findViewById(R.id.tv_order_status);
            tvTotal = itemView.findViewById(R.id.tv_order_total);
        }
    }
}
