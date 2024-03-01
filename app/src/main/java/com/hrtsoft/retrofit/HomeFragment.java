package com.hrtsoft.retrofit;

import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {


    private View view;
    private ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private List<products> productsList;
    private Adapter adapter;
    ImageSlider imageSlider;
    ImageView callid,notifacation;
    SearchView searchView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider =view.findViewById(R.id.image_slider);
        ArrayList<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.one, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.two,ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.three,ScaleTypes.FIT));

        imageSlider.setImageList(imageList);

        callid=view.findViewById(R.id.callID);
        notifacation=view.findViewById(R.id.notifacation);

        notifacation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Notification",Toast.LENGTH_SHORT).show();
            }
        });

        callid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "01737378582";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        //searchview

        searchView=view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method, calling a method to filter the recycler view.
                filter(newText);
                return true;
            }
        });



        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/") // Change this to your actual base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ApiInterface
        apiInterface = retrofit.create(ApiInterface.class);

        recyclerView = view.findViewById(R.id.recyclerviewid);
        if (recyclerView != null) {
            GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
            recyclerView.setLayoutManager(layoutManager);

        } else {
            Log.e("HomeFragment", "RecyclerView is null");
        }

        // Make API call using Retrofit
        Call<Model> call = apiInterface.getResponse();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
                if (response.isSuccessful()) {
                    productsList = response.body().getProducts();

                    // Set up RecyclerView adapter
                    adapter = new Adapter(requireContext(), productsList); // Fix here
                    recyclerView.setAdapter(adapter);
                } else {
                    // API call failed
                 }
            }

            @Override
            public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
                // API call failure
                Log.e("API Call", "Error: " + t.getMessage());
             }
        });
        return view;
    }


    private void filter(String text) {
        // creating a new array list to filter the data.
        ArrayList<products> filteredList = new ArrayList<>();

        // running a loop to compare elements.
        for (products item : productsList) {
            // checking if the entered string matches with any item in the recycler view.
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched, adding it to the filtered list.
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            // if no item is added to the filtered list, displaying a toast message.
            Toast.makeText(getContext(), "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // passing the filtered list to the adapter class.
            adapter.filterList(filteredList);
        }
    }


}
