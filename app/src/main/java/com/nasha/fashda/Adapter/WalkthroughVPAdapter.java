package com.nasha.fashda.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nasha.fashda.R;
import com.nasha.fashda.databinding.WalkthroughItemBinding;
import com.nasha.fashda.models.WalkthroughModel;

import java.util.List;

public class WalkthroughVPAdapter extends RecyclerView.Adapter<WalkthroughVPAdapter.WalkthroughViewHolder> {

    private final List<WalkthroughModel> walkthroughs;
    private static WalkthroughItemBinding binding;

    public WalkthroughVPAdapter(List<WalkthroughModel> walkthroughs) {
        this.walkthroughs = walkthroughs;
    }

    @NonNull
    @Override
    public WalkthroughViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.walkthrough_item,
                parent,
                false
        );

        binding = WalkthroughItemBinding.bind(view);

        return new WalkthroughViewHolder(
                binding.getRoot()
        );
    }

    @Override
    public void onBindViewHolder(@NonNull WalkthroughViewHolder holder, int position) {
        holder.setWalkthroughItemData(walkthroughs.get(position));
    }

    @Override
    public int getItemCount() {
        return walkthroughs.size();
    }

    static class WalkthroughViewHolder extends RecyclerView.ViewHolder {

        public WalkthroughViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setWalkthroughItemData(WalkthroughModel data) {
            binding.wtTitle.setText(data.getTitle());
            binding.wtDescription.setText(data.getDescription());
            binding.wtImage.setImageResource(data.getImageDrawable());
        }
    }
}
