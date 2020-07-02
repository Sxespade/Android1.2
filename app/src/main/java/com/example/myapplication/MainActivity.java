package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private String TEXT = "TEXT";
    private String SITE = "https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0";
    private String SITE2 = "https://ru.wikipedia.org/wiki/%D0%9B%D0%BE%D0%BD%D0%B4%D0%BE%D0%BD";
    private String SITE3 = "https://ru.wikipedia.org/wiki/%D0%9D%D1%8C%D1%8E-%D0%99%D0%BE%D1%80%D0%BA";
    public static String PARCEL = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = com.example.myapplication.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        Parcel parcel = (Parcel) getIntent().getExtras().getSerializable(TEXT);
//        binding.city.setText(parcel.getCity());


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Если устройство перевернули в альбомную ориентацию, то надо эту activity закрыть
            finish();
            return;
        }

        if (savedInstanceState == null) {
            // Если эта activity запускается первый раз (с каждым новым гербом первый раз)
            // то перенаправим параметр фрагменту
            WeatherFragment details = new WeatherFragment();
            details.setArguments(getIntent().getExtras());
            // Добавим фрагмент на activity
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.act_1, details).commit();
        }

//        binding.button2.setOnClickListener((v) -> {
//
//            switch (Parcel.getInstance().getCity()) {
//                case "Moscow": {
//                    String url = SITE;
//                    Uri uri = Uri.parse(url);
//                    Intent browser = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(browser);
//                    break;
//                }
//                case "London": {
//                    String url = SITE2;
//                    Uri uri = Uri.parse(url);
//                    Intent browser = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(browser);
//                    break;
//                }
//                case "New-York": {
//                    String url = SITE3;
//                    Uri uri = Uri.parse(url);
//                    Intent browser = new Intent(Intent.ACTION_VIEW, uri);
//                    startActivity(browser);
//                    break;
//                }
//            }
//        });
    }
}