package com.example.currentweather.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.currentweather.R;
import com.example.currentweather.apiservice.ApiService;
import com.example.currentweather.datamodel.WeatherInfo;
import com.google.android.material.snackbar.Snackbar;

public class MainPageActivity extends AppCompatActivity implements ApiService.OnDataReceived {
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final EditText searchBox = findViewById(R.id.search_box);
        ImageButton searchButton = findViewById(R.id.search_button);
        final ApiService apiService = new ApiService(this,progressBar);
        //progressBar.setVisibility(View.INVISIBLE);
        relativeLayout = findViewById(R.id.relative_layout);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                apiService.getData(searchBox.getText().toString(), MainPageActivity.this);
            }
        });
//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ApiServiceThread thread=new ApiServiceThread(MainPageActivity.this,searchBox.getText().toString(),progressBar,MainPageActivity.this);
//                thread.start();
//                progressBar.setVisibility(View.VISIBLE);
//            }
//        });
//    }


//    @Override
//    public void onReceived(WeatherInfo weatherInfo) {
//        if (weatherInfo == null) {
//            Snackbar.make(relativeLayout, "Error", Snackbar.LENGTH_SHORT).show();
//        } else {
//            Intent intent = new Intent(MainPageActivity.this, WeatherInfoActivity.class);
//            intent.putExtra("temp", weatherInfo.getTemp())
//                    .putExtra("minTemp", weatherInfo.getMinTemp())
//                    .putExtra("maxTemp", weatherInfo.getMaxTemp())
//                    .putExtra("description", weatherInfo.getDescription())
//                    .putExtra("humidity", weatherInfo.getHumidity());
//            startActivity(intent);
//        }
//    }
    }

    @Override
    public void onReceived(WeatherInfo weatherInfo) {
        if (weatherInfo == null) {
            Snackbar.make(relativeLayout, "Error", Snackbar.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainPageActivity.this, WeatherInfoActivity.class);
            intent.putExtra("temp", weatherInfo.getTemp())
                    .putExtra("minTemp", weatherInfo.getMinTemp())
                    .putExtra("maxTemp", weatherInfo.getMaxTemp())
                    .putExtra("description", weatherInfo.getDescription())
                    .putExtra("humidity", weatherInfo.getHumidity());
            startActivity(intent);
        }
    }
}
