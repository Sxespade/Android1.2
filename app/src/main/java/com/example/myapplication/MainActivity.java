package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = com.example.myapplication.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Parcel parcel = (Parcel) getIntent().getExtras().getSerializable(TEXT);
        binding.city.setText(parcel.getCity());

        binding.button2.setOnClickListener((v) -> {
            if (Parcel.getInstance().getCity().equals("Moscow")) {
                String url = SITE;
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            } else if (Parcel.getInstance().getCity().equals("London")) {
                String url = SITE2;
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            } else if (Parcel.getInstance().getCity().equals("New-York")) {
                String url = SITE3;
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            }
        });
    }
}