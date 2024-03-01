package com.hrtsoft.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);


        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/") // Change this to your actual base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the ApiInterface
        apiInterface = retrofit.create(ApiInterface.class);

        recyclerView = view.findViewById(R.id.recyclerviewid);
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        } else {
            Log.e("HomeFragment", "RecyclerView is null");
        }

        // Make API call using Retrofit
        Call<Model> call = apiInterface.getResponse();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
                if (response.isSuccessful()) {
                    // API call successful, get the data
                    productsList = response.body().getProducts();

                    // Set up RecyclerView adapter
                    adapter = new Adapter(productsList);
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
}
