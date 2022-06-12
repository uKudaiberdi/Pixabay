package com.example.pixabay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.pixabay.adapter.ImageAdapter;
import com.example.pixabay.databinding.ActivityMainBinding;
import com.example.pixabay.network.model.Hit;
import com.example.pixabay.network.model.PixabayModel;
import com.example.pixabay.network.model.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
RetrofitService retrofitService;
ActivityMainBinding binding;
ImageAdapter adapter;

public static final String KEY = "27811797-f4785bfc630173b1f28672c4a";
private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        retrofitService = new RetrofitService();
        initClickers();
        binding.swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                count++;
                String word = binding.wordEd.getText().toString();
                getImageFromApi(word,count,10);
                binding.swipeView.setRefreshing(false);
            }
        });
    }

    private void initClickers() {

        binding.applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.prBar.setVisibility(View.VISIBLE);
                count = 1;
                String word = binding.wordEd.getText().toString();
                getImageFromApi(word,count,10);
            }
        });

    }
    private void getImageFromApi(String word,int page,int perPage) {
        retrofitService.getApi().getImages(KEY, word,page,perPage).enqueue(new Callback<PixabayModel>() {
            @Override
            public void onResponse(Call<PixabayModel> call, Response<PixabayModel> response) {
                if (response.isSuccessful()){
                    adapter = new ImageAdapter( (ArrayList<Hit>) response.body().getHits());
                    binding.recyclerView.setAdapter(adapter);
                    binding.prBar.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onFailure(Call<PixabayModel> call, Throwable t) {
                Log.e("jojo", "onFailure: "+t.getMessage() );
            }
        });
    }
}