package com.example.xivrelictracker.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.xivrelictracker.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}