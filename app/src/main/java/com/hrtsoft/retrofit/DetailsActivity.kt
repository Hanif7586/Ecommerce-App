package com.hrtsoft.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

val titleTextView:TextView=findViewById(R.id.titleTextView);
        val priceTextView:TextView=findViewById(R.id.priceTextView);
        val categoryTextView:TextView=findViewById(R.id.categoryTextView);
        val descriptionTextView:TextView=findViewById(R.id.descriptionTextView);
        val thumbnailImageView:ImageView=findViewById(R.id.thumbnailImageView);

        // Get data from the Intent
        val title = intent.getStringExtra("title")
        val price = intent.getStringExtra("price")
        val category = intent.getStringExtra("category")
        val description = intent.getStringExtra("description")
        val thumbnail = intent.getStringExtra("thumbnail")

        // Set the data to your UI elements
        titleTextView.text = title
        priceTextView.text = price
        categoryTextView.text = category
        descriptionTextView.text = description



        // Load thumbnail using Picasso
        Picasso.get().load(thumbnail).into(thumbnailImageView)
    }
}

