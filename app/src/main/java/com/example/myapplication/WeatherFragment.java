package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.myapplication.MainActivity.PARCEL;

public class WeatherFragment extends Fragment {

    private String TEXT = "TEXT";
    private String SITE = "https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0";
    private String SITE2 = "https://ru.wikipedia.org/wiki/%D0%9B%D0%BE%D0%BD%D0%B4%D0%BE%D0%BD";
    private String SITE3 = "https://ru.wikipedia.org/wiki/%D0%9D%D1%8C%D1%8E-%D0%99%D0%BE%D1%80%D0%BA";
    RecyclerView recyclerView;

    public static WeatherFragment create(Parcel parcel) {
        WeatherFragment f = new WeatherFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARCEL, parcel);
        f.setArguments(args);
        return f;
    }

    public Parcel getParcel() {
        Parcel parcel = null;
        if (getArguments() != null) {
            parcel = (Parcel) getArguments().getSerializable(PARCEL);
        }
        return parcel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather, container, false);



        recyclerView = layout.findViewById(R.id.recycler_view);
        TextView cityNameView = layout.findViewById(R.id.city);
        Button button2 = layout.findViewById(R.id.button2);
        Parcel parcel = getParcel();
        cityNameView.setText(parcel.getCity());

        String[] data = getResources().getStringArray(R.array.temps);
        initRecyclerView(data);


        button2.setOnClickListener((v) -> {
            System.out.println("getIntent().getExtras().toString()");

            switch (parcel.getCity()) {
                case "Moscow": {
                    String url = SITE;
                    Uri uri = Uri.parse(url);
                    Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(browser);
                    break;
                }
                case "London": {
                    String url = SITE2;
                    Uri uri = Uri.parse(url);
                    Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(browser);
                    break;
                }
                case "New-York": {
                    String url = SITE3;
                    Uri uri = Uri.parse(url);
                    Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(browser);
                    break;
                }
            }
        });


        return layout;
    }

    private void initRecyclerView(String[] data) {

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        WeatherAdapter adapter = new WeatherAdapter(data);
        recyclerView.setAdapter(adapter);

    }

}
