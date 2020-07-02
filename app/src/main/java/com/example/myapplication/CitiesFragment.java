package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.myapplication.databinding.ActivityMain2Binding;
import static com.example.myapplication.MainActivity.PARCEL;


public class CitiesFragment extends Fragment {
    boolean isExistCoatOfArms;
    Parcel currentParcel;
    ActivityMain2Binding binding;
    private static final String TAG = "myApp2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isExistCoatOfArms = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentParcel = (Parcel) savedInstanceState.getSerializable("CurrentCity");
        } else {
            currentParcel = new Parcel();
            currentParcel.setCity(getResources().getStringArray(R.array.Cities)[0]);
        }
        if (isExistCoatOfArms) {
            showCoatOfArms(currentParcel);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.button.setOnClickListener((v) -> {
            currentParcel.setCity(binding.spinner2.getSelectedItem().toString());
            showCoatOfArms(currentParcel);
        });

    }

    private void showCoatOfArms(Parcel parcel) {
        if (isExistCoatOfArms) {
            WeatherFragment detail;
            detail = WeatherFragment.create(parcel);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.weather_fragment, detail);  // замена фрагмента
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainActivity.class);
            //+ и передадим туда Parcel
            parcel.setCity(binding.spinner2.getSelectedItem().toString());
            intent.putExtra(PARCEL, parcel);
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("CurrentCity", currentParcel);
        super.onSaveInstanceState(outState);
    }

    private void initList(View view) {
        binding.button.setOnClickListener((v) -> {
            showCoatOfArms(currentParcel);
        });
    }
}
