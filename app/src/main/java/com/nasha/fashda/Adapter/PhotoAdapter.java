package com.nasha.fashda.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nasha.fashda.R;
import com.nasha.fashda.databinding.PhotoItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private ArrayList<String> photos;

    public PhotoAdapter(ArrayList<String> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.photo_item,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItemData(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final PhotoItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = PhotoItemBinding.bind(itemView);
        }

        public void setItemData(String photo) {
            Picasso.get()
                    .load(photo)
                    .placeholder(R.drawable.item_frame)
                    .into(binding.thumbnail);
        }
    }

}
