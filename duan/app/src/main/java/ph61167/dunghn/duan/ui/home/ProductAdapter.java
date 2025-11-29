package ph61167.dunghn.duan.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import ph61167.dunghn.duan.data.model.Product;
import ph61167.dunghn.duan.databinding.ItemProductBinding;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> items = new ArrayList<>();

    public interface OnBuyClickListener {
        void onBuy(Product product);
    }

    private final List<Product> products = new ArrayList<>();
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    private final OnBuyClickListener listener;

    public ProductAdapter(OnBuyClickListener listener){
        this.listener = listener;
    }

    public void setItems(List<Product> data){
        products.clear();
        if(data != null) products.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(products.get(position), currencyFormat, listener);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
    public void setData(List<Product> list) {
        this.items.clear();
        if (list != null) {
            this.items.addAll(list);
        }
        notifyDataSetChanged(); // thông báo RecyclerView update
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductBinding binding;

        public ProductViewHolder(ItemProductBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product product, NumberFormat format, OnBuyClickListener listener){
            binding.tvProductName.setText(product.getName());
            binding.tvProductPrice.setText(format.format(product.getPrice()));
            binding.ivProduct.setImageResource(ph61167.dunghn.duan.R.drawable.img);
            binding.btnBuy.setOnClickListener(v -> {
                if(listener != null) listener.onBuy(product);
            });
        }


    }
}
