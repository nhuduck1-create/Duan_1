package ph61167.dunghn.duan.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ph61167.dunghn.duan.data.model.AdminOrderEntity;
import ph61167.dunghn.duan.databinding.ItemOrderBinding;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final List<AdminOrderEntity> orders = new ArrayList<>();
    private OnShipClickListener shipListener;

    public interface OnShipClickListener {
        void onShip(AdminOrderEntity order);
    }

    public void setOnShipClickListener(OnShipClickListener listener) {
        this.shipListener = listener;
    }

    public void submitList(List<AdminOrderEntity> data) {
        orders.clear();
        if (data != null) orders.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding binding = ItemOrderBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.bind(orders.get(position), shipListener);
    }

    @Override
    public int getItemCount() { return orders.size(); }


    // ===================== VIEW HOLDER ======================
    static class OrderViewHolder extends RecyclerView.ViewHolder {

        private final ItemOrderBinding binding;

        OrderViewHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(AdminOrderEntity o, OnShipClickListener listener) {

            // --- GỘP ĐÚNG THEO ĐOẠN 1 ---
            String info =
                    "Order #" + o.getId() +
                            "\nProduct ID: " + o.getProductId() +
                            "\nUser ID: " + o.getUserId() +
                            "\nQuantity: " + o.getQuantity();

            binding.tvOrderInfo.setText(info);
            binding.tvStatus.setText("Status: " + o.getStatus());

            binding.btnShip.setOnClickListener(v -> {
                if (listener != null) listener.onShip(o);

                // cập nhật UI sau khi ship
                binding.tvStatus.setText("Status: SHIPPED");
            });
        }
    }
}
