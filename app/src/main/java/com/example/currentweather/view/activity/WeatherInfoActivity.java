package com.example.currentweather.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.currentweather.R;
import com.example.currentweather.datamodel.WeatherInfo;

public class WeatherInfoActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextView temp;
    private TextView minTemp;
    private TextView maxTemp;
    private TextView description;
    private TextView humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        setupViews();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        description.setText(getIntent().getStringExtra("description"));
        temp.setText(String.valueOf(getIntent().getIntExtra("temp",0)));
        minTemp.setText(String.valueOf(getIntent().getIntExtra("minTemp",0)));
        maxTemp.setText(String.valueOf(getIntent().getIntExtra("maxTemp",0)));
        humidity.setText(String.valueOf(getIntent().getIntExtra("humidity",0)));
    }

    public void setupViews(){
        backButton=findViewById(R.id.back_button);
        temp=findViewById(R.id.temp);
        minTemp=findViewById(R.id.min_temp);
        maxTemp=findViewById(R.id.max_temp);
        description=findViewById(R.id.description);
        humidity=findViewById(R.id.humidity);
    }
}
