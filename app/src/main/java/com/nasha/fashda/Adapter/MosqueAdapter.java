package com.nasha.fashda.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nasha.fashda.R;
import com.nasha.fashda.databinding.MosqueItemBinding;
import com.nasha.fashda.models.PlaceModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MosqueAdapter extends RecyclerView.Adapter<MosqueAdapter.ViewHolder> {
    private List<PlaceModel> places;

    public MosqueAdapter(List<PlaceModel> model){
        this.places = places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.mosque_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItemData(places.get(position));
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MosqueItemBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = MosqueItemBinding.bind(itemView);
        }

        public void setItemData(PlaceModel data) {
            binding.placeName.setText(data.getPlaceName());
            binding.address.setText(data.getFormattedAddress());

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
