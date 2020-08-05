package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity4 extends AppCompatActivity {

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
        setContentView(R.layout.activity_main4);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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