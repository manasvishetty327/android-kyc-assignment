package com.manasvishetty.androidkycassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.manasvishetty.androidkycassignment.ui.navigation.AppNavigation
import com.manasvishetty.androidkycassignment.ui.theme.AndroidkycassignmentTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.*
import com.manasvishetty.androidkycassignment.data.local.ThemePreference
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val themePreference = ThemePreference(this)

            var isDarkMode by remember {
                mutableStateOf(themePreference.isDarkMode())
            }

            AndroidkycassignmentTheme(
                darkTheme = isDarkMode
            ) {
                AppNavigation(
                    isDarkMode = isDarkMode,
                    onThemeToggle = {
                        isDarkMode = !isDarkMode
                        themePreference.saveTheme(isDarkMode)
                    }
                )
            }
        }
    }
}