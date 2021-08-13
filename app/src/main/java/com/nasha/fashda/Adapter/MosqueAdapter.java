package com.nasha.fashda.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nasha.fashda.MosqueDetailActivity;
import com.nasha.fashda.R;
import com.nasha.fashda.databinding.MosqueItemBinding;
import com.nasha.fashda.models.PhotoModel;
import com.nasha.fashda.models.PlaceModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MosqueAdapter extends RecyclerView.Adapter<MosqueAdapter.ViewHolder> {
    private List<PlaceModel> mosquePlaces;

    public MosqueAdapter(List<PlaceModel> mosquePlaces){
        this.mosquePlaces = mosquePlaces;
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
        holder.setItemData(mosquePlaces.get(position));
    }

    @Override
    public int getItemCount() {
        return mosquePlaces.size();
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
                    Intent intent = new Intent(binding.getRoot().getContext(), MosqueDetailActivity.class);
                    intent.putExtra("place_id", data.getPlaceId());
                    intent.putExtra("place_name", data.getPlaceName());

                    if (data.getPhotos()!=null){
                        PhotoModel photoModel = data.getPhotos()[0];
                        intent.putExtra("place_photo_url",photoModel.getPhotoUrl());
                    }
                    binding.getRoot().getContext().startActivity(intent);
                }
            });
            if (data.getPhotos()!=null && data.getPhotos().length>0){
                PhotoModel photoModel = data.getPhotos()[0];
                photoModel.setMaxWidth(320);

                Picasso.get()
                        .load(photoModel.getPhotoUrl())
                        .placeholder(R.drawable.item_frame)
                        .into(binding.thumbnail);
            }else {
                binding.thumbnail.setImageResource(R.drawable.item_frame);
            }
        }
    }
}