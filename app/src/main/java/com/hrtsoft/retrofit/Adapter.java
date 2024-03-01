package com.hrtsoft.retrofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<products> products;
    private Context context; // Add a context variable

    public Adapter(Context context, List<products> products) {
        this.context = context;
        this.products = products;
    }
    public void filterList(ArrayList<products> filterList) {
        // below line is to add our filtered
        // list in our products array list.
        products = filterList;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        products products1 = products.get(position);

        // Use Picasso or Glide for efficient image loading
        Picasso.get().load(products1.getThumbnail()).into(holder.imageViewThumbnail);

        holder.textViewTitle.setText(products1.getTitle());
        holder.price.setText(products1.getPrice());
        holder.category.setText(products1.getCategory());
        holder.textViewDescription.setText(products1.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if context is not null before using it
                if (context != null) {
                    // Open DetailsActivity and pass data
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("title", products1.getTitle());
                    intent.putExtra("price", products1.getPrice());
                    intent.putExtra("category", products1.getCategory());
                    intent.putExtra("description", products1.getDescription());
                    intent.putExtra("thumbnail", products1.getThumbnail());

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewThumbnail;
        TextView textViewTitle;
        TextView textViewDescription, price, category;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewThumbnail = itemView.findViewById(R.id.thumbnailImageView);
            textViewTitle = itemView.findViewById(R.id.titleTextView);
            price = itemView.findViewById(R.id.priceTextView);
            category = itemView.findViewById(R.id.categoryTextView);
            textViewDescription = itemView.findViewById(R.id.desTextView);
            cardView = itemView.findViewById(R.id.detailsID);
        }
    }
}
