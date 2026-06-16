package com.tuhin.vyora.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.tuhin.vyora.R;

public class SettingsFragment extends Fragment {

    private static final String PREFS_NAME = "theme_prefs";
    private static final String KEY_THEME = "selected_theme";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        view.findViewById(R.id.btnThemeDark).setOnClickListener(v -> updateTheme(AppCompatDelegate.MODE_NIGHT_YES));
        view.findViewById(R.id.btnThemeLight).setOnClickListener(v -> updateTheme(AppCompatDelegate.MODE_NIGHT_NO));
        view.findViewById(R.id.btnThemeSystem).setOnClickListener(v -> updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM));

        return view;
    }

    private void updateTheme(int mode) {
        AppCompatDelegate.setDefaultNightMode(mode);
        saveThemePreference(mode);
    }

    private void saveThemePreference(int mode) {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(KEY_THEME, mode).apply();
    }
}
