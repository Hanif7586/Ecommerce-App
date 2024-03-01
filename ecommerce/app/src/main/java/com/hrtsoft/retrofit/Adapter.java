package com.hrtsoft.retrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<products>products;

    public Adapter(List<com.hrtsoft.retrofit.products> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        products products1= products.get(position);

        // Use Picasso or Glide for efficient image loading
        Picasso.get().load(products1.getThumbnail()).into(holder.imageViewThumbnail);

        holder.textViewTitle.setText(products1.getTitle());
        holder.price.setText(products1.getPrice());
        holder.category.setText(products1.getCategory());
        holder.textViewDescription.setText(products1.getDescription());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewThumbnail;
        TextView textViewTitle;
        TextView textViewDescription,price,category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewThumbnail = itemView.findViewById(R.id.thumbnailImageView);
            textViewTitle = itemView.findViewById(R.id.titleTextView);
            price = itemView.findViewById(R.id.priceTextView);
            category = itemView.findViewById(R.id.categoryTextView);
            textViewDescription = itemView.findViewById(R.id.desTextView);

        }
    }
}
