package com.example.projekt_spotify;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "theme_prefs";
    private static final String THEME_KEY = "theme_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load theme from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isNightMode = sharedPreferences.getBoolean(THEME_KEY, true);
        AppCompatDelegate.setDefaultNightMode(isNightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);

        Switch themeSwitch = findViewById(R.id.theme_switch);

        if (themeSwitch != null) {
            themeSwitch.setChecked(!isNightMode); // Set switch state based on current theme

            themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Toggle theme mode
                    AppCompatDelegate.setDefaultNightMode(isChecked ? AppCompatDelegate.MODE_NIGHT_NO : AppCompatDelegate.MODE_NIGHT_YES);

                    // Save the new theme mode to SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(THEME_KEY, !isChecked);
                    editor.apply();

                    // Restart activity to apply the new theme
                    recreate();
                }
            });
        }

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

        EdgeToEdge.enable(this);
        overridePendingTransition(R.anim.none, R.anim.none);
    }
}
