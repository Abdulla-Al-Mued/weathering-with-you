package com.example.weatheringwithyou;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.input.InputManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;

import com.example.weatheringwithyou.Db_con.ApiInterface;
import com.example.weatheringwithyou.ModelClass.main;
import com.example.weatheringwithyou.ModelClass.weather;
import com.example.weatheringwithyou.ModelClass.weatherData;
import com.example.weatheringwithyou.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView;
    ActivityMainBinding binding;
    private String url = "https://api.openweathermap.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        scrollView = findViewById(R.id.scrollable);

        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String currentDate = format.format(new Date());

        binding.date.setText(currentDate);

        fetchWeather("New york");

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(binding.searchBar.getText().toString().trim())){
                    binding.searchBar.setError("please enter city");
                    return;
                }

                fetchWeather(binding.searchBar.getText().toString().trim());

                binding.searchBar.setText("");
                hideKeyboard();
            }

        });

    }

    void hideKeyboard(){
        InputMethodManager inputManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(scrollView.getWindowToken(), 0);
    }

    private void fetchWeather(String cityName) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<weatherData> call = apiInterface.getData(cityName, "e2db9422b21d339408170d7458a60848", "metric");


        call.enqueue(new Callback<weatherData>() {
            @Override
            public void onResponse(Call<weatherData> call, Response<weatherData> response) {

                if(response.isSuccessful()){
                    weatherData weatherData = response.body();
                    main Main = weatherData.getMain();

                    binding.temp.setText(String.valueOf(Main.getTemp())+"\u2103");
                    binding.maxTemp.setText(String.valueOf(Main.getTemp_max())+"\u2103");
                    binding.minTemp.setText(String.valueOf(Main.getTemp_min())+"\u2103");
                    binding.pressure.setText(String.valueOf(Main.getPressure()));
                    binding.humidity.setText(String.valueOf(Main.getHumidity())+"%");
                    binding.city.setText(weatherData.getName());


                    List<weather> description = weatherData.getWeather();

                    for(weather data : description){
                        binding.description.setText(data.getDescription());
                    }

                }

            }

            @Override
            public void onFailure(Call<weatherData> call, Throwable t) {

            }
        });

    }
}