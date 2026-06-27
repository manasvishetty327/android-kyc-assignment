package com.manasvishetty.androidkycassignment.data.local

import android.content.Context

class ThemePreference(context: Context) {

    private val prefs =
        context.getSharedPreferences("theme_pref", Context.MODE_PRIVATE)

    fun saveTheme(isDark: Boolean) {
        prefs.edit()
            .putBoolean("is_dark", isDark)
            .apply()
    }

    fun isDarkMode(): Boolean {
        return prefs.getBoolean("is_dark", false)
    }
}