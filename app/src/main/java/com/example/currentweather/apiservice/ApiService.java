package com.example.currentweather.apiservice;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.currentweather.datamodel.WeatherInfo;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class ApiService {
    private ProgressBar progressBar;
    private Context context;
    public ApiService(Context context,ProgressBar progressBar){
        this.context=context;
        this.progressBar=progressBar;
    }


    public void getData(String cityName, final OnDataReceived onDataReceived){
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=5379384526559586eb5f96d8d47752da",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                WeatherInfo weatherInfo=parseJson(response);
                onDataReceived.onReceived(weatherInfo);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error );
                onDataReceived.onReceived(null);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(8000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    public WeatherInfo parseJson(JSONObject response){
        try {
            WeatherInfo weatherInfo=new WeatherInfo();
            JSONObject weather = response.getJSONArray("weather").getJSONObject(0);
            weatherInfo.setDescription(weather.getString("main"));
            JSONObject main=response.getJSONObject("main");
            weatherInfo.setTemp((int)main.getDouble("temp"))
                    .setMinTemp((int)main.getDouble("temp_min"))
                    .setMaxTemp((int)main.getDouble("temp_max"))
                    .setHumidity(main.getInt("humidity"));
            return weatherInfo;
        }catch (Exception e){}
        return null;
    }


    public interface OnDataReceived{
        void onReceived(WeatherInfo weatherInfo);
    }
}
