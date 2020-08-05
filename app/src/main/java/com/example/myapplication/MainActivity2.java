package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMain2Binding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "myApp";
    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String instanceState;
        if (savedInstanceState == null) {
            instanceState = "Первый запуск!";
        } else {
            instanceState = "Повторный запуск!";
        }
        showLog(instanceState + " - onCreate()");

    }

    @Override
    protected void onStart() {
        super.onStart();
        showLog("onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        showLog("Повторный запуск!! - onRestoreInstanceState()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLog("onResume()");
//        binding.textView6.setText(presenter.getChooseCity());
    }

    @Override
    protected void onPause() {
        super.onPause();
        showLog("onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        showLog("onSaveInstanceState()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLog("onStop()");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        showLog("onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLog("onDestroy()");
    }

    private void showLog(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        Log.d(TAG, s);
    }


}