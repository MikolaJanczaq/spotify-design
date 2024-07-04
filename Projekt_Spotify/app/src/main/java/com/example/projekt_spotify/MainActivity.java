package com.example.projekt_spotify;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.none, R.anim.none);

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);

        bottomNav.setSelectedItemId(R.id.navigation_home);

        bottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(this, MainActivity.class));
                    break;
                case R.id.navigation_search:
                    startActivity(new Intent(this, Search.class).addFlags(FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case R.id.navigation_library:
                    startActivity(new Intent(this, Library.class).addFlags(FLAG_ACTIVITY_CLEAR_TOP));
                    break;
            }
            return true;
        });
    }
}