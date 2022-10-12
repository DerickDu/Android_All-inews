package com.laioffer.tinnews;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);


        // Add the following code inside onCreate
// // new a task, make the call<NewsResponse>
// add task to queue
// while(true) { retrofit keep check the queue }
// if queue has `task`, retrofit do task: call endpoint, parse json , etc
// once retrofit finish the task
// callback.onRsponse(response)
// if (task success) newsResponseCallback.onResponse()
// else newsResponseCallback.onFailure()

        NewsApi api = RetrofitClient.newInstance().create(NewsApi.class);
        // Call<NewsResponse>
        api.getTopHeadlines("us").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("getTopHeadlines", response.body().toString());
                } else {
                    Log.d("getTopHeadlines", response.toString());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d("getTopHeadlines", t.toString());
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}




