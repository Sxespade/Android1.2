package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import static com.example.myapplication.MainActivity.PARCEL;

public class WeatherFragment extends Fragment {

    private String TAG = "myApp";
    private String SITE = "https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0";
    private String SITE2 = "https://ru.wikipedia.org/wiki/%D0%9B%D0%BE%D0%BD%D0%B4%D0%BE%D0%BD";
    private String SITE3 = "https://ru.wikipedia.org/wiki/%D0%9D%D1%8C%D1%8E-%D0%99%D0%BE%D1%80%D0%BA";

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Intent intent = new Intent();
                intent.setClass(requireContext(), MainActivity3.class);
                startActivity(intent);
                return true;
            case R.id.navigation_dashboard:
                Intent intent1 = new Intent();
                intent1.setClass(requireContext(), MainActivity4.class);
                startActivity(intent1);
                return true;
            case R.id.navigation_notifications:
                Toast.makeText(requireContext(), "Не добавлен", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    };

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    RecyclerView recyclerView3;



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

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather, container, false);


        recyclerView = layout.findViewById(R.id.recycler_view);
        recyclerView2 = layout.findViewById(R.id.recycler_view2);
        recyclerView3 = layout.findViewById(R.id.recycler_view3);
        recyclerView = layout.findViewById(R.id.recycler_view);
        TextView cityNameView = layout.findViewById(R.id.city);
        Button button2 = layout.findViewById(R.id.button2);
        TextView prevDispCities = layout.findViewById(R.id.prev_displ_cities);

        Parcel parcel = getParcel();
        cityNameView.setText(parcel.getCity());

        String[] data = getResources().getStringArray(R.array.temps);
        initRecyclerView(data);


        String[] data2 = getResources().getStringArray(R.array.Cities);
        initRecyclerView2(data2);

        initPrevDispCity(prevDispCities, parcel);

        button2.setOnClickListener((v) -> {

            internetWikiAboutCity(parcel);

        });

        BottomNavigationView navView = layout.findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        return layout;
    }


    private void initPrevDispCity(TextView prevDispCities, Parcel parcel) {
        prevDispCities.setVisibility(View.GONE);
        recyclerView3.setVisibility(View.GONE);

        Log.d(TAG, "onCreateView: " + parcel.getCount());

        if (parcel.getCountPlus1() > 1) {
            initRecyclerView3(Parcel.getData3());
            prevDispCities.setVisibility(View.VISIBLE);
            recyclerView3.setVisibility(View.VISIBLE);
        }
    }

    private void internetWikiAboutCity(Parcel parcel) {
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
    }

    private void initRecyclerView(String[] data) {

        recyclerView.setHasFixedSize(true);


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


    private void initRecyclerView2(String[] data) {

        DividerItemDecoration itemDecoration = new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.decorator));
        recyclerView2.addItemDecoration(itemDecoration);


        recyclerView2.setHasFixedSize(true);

        CityWeatherAdapter adapter = new CityWeatherAdapter(data);
        recyclerView2.setAdapter(adapter);

    }

    private void initRecyclerView3(List<String> data) {

        DividerItemDecoration itemDecoration = new DividerItemDecoration(Objects.requireNonNull(getActivity()), LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.decorator));
        recyclerView3.addItemDecoration(itemDecoration);


        recyclerView3.setHasFixedSize(true);

        PrevDisplCitiesAdapter adapter = new PrevDisplCitiesAdapter(data);
        adapter.SetOnItemClickListener((view, position) -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainActivity.class);
            Parcel parcel = new Parcel();
            parcel.setCity(Parcel.data3.get(position));
            intent.putExtra(PARCEL, parcel);
            startActivity(intent);
        });
        recyclerView3.setAdapter(adapter);

    }

}
