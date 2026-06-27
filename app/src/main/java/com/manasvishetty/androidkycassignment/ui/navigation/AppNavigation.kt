package com.manasvishetty.androidkycassignment.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.manasvishetty.androidkycassignment.ui.accounts.AccountsScreen
import com.manasvishetty.androidkycassignment.ui.details.DetailsScreen
import com.manasvishetty.androidkycassignment.ui.camera.CameraScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.manasvishetty.androidkycassignment.ui.accounts.AccountsViewModel
sealed class Screen(val route: String) {
    object Accounts : Screen("accounts")
    object Details : Screen("details/{customerId}")
    object Camera : Screen("camera/{customerId}")
}

@Composable
fun AppNavigation(
    isDarkMode: Boolean,
    onThemeToggle: () -> Unit
) {
    val navController = rememberNavController()
    val accountsViewModel: AccountsViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Accounts.route
    ) {
        composable(Screen.Accounts.route) {
            AccountsScreen(
                navController = navController,
                viewModel = accountsViewModel,
                isDarkMode = isDarkMode,
                onThemeToggle = onThemeToggle
            )
        }

        composable(Screen.Camera.route) { backStackEntry ->
            val customerId =
                backStackEntry.arguments?.getString("customerId")?.toInt() ?: 0

            CameraScreen(
                customerId = customerId,
                navController = navController,
                viewModel = accountsViewModel
            )
        }

        composable(Screen.Details.route) { backStackEntry ->
            val customerId =
                backStackEntry.arguments?.getString("customerId")?.toInt() ?: 0

            DetailsScreen(
                customerId = customerId,
                navController = navController,
                viewModel = accountsViewModel
            )
        }
    }
}