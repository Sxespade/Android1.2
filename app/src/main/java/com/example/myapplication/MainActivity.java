package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private com.example.myapplication.databinding.ActivityMainBinding binding;
    public static String PARCEL = "1";
    private final String KEY = "61e5cbccdc6985b69b92b24028a5dfc5";
    private final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=Moscow,RU&appid=";
    private TextView temp;
    final Handler handler = new Handler(); // Запоминаем основной поток
    WeatherFragment details = new WeatherFragment();
    String k;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Intent intent = new Intent();
                intent.setClass(this, MainActivity3.class);
                startActivity(intent);
                return true;
            case R.id.navigation_dashboard:
                Intent intent1 = new Intent();
                intent1.setClass(this, MainActivity4.class);
                startActivity(intent1);
                return true;
            case R.id.navigation_notifications:
                Toast.makeText(this, "Не добавлен", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        if (savedInstanceState == null) {

            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.act_1, details).commit();

            Button button4 = findViewById(R.id.button4);

            button4.setOnClickListener((v) -> {
                inetWeather();
            });

        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void inetWeather() {
        try {
            final URL uri = new URL(WEATHER_URL + KEY);
            new Thread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    HttpsURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpsURLConnection) uri.openConnection();
                        urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                        urlConnection.setReadTimeout(10000); // установка таймаута - 10 000 миллисекунд
                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                        String result = getLines(in);
                        // преобразование данных запроса в модель
                        Gson gson = new Gson();
                        final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                        // Возвращаемся к основному потоку
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                displayWeather(weatherRequest);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (null != urlConnection) {
                            urlConnection.disconnect();
                        }
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void displayWeather(WeatherRequest weatherRequest) {
        k = String.format("%.2f", weatherRequest.getMain().getTemp());
        TextView tv = findViewById(R.id.temper1);
        tv.setText(k);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu) {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity4.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}