package com.littlelemon.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavigation(navController: NavHostController) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("little_lemon", Context.MODE_PRIVATE)
    val logged = sharedPreferences.getString("EMAIL", "")?.isNotBlank() ?: false

    NavHost(
        navController = navController,
        startDestination = if (logged) Home.route else OnBoarding.route
    ) {
        composable(Home.route) { Home(navController = navController) }
        composable(OnBoarding.route) { OnBoarding(navController = navController) }
        composable(Profile.route) { Profile(navController = navController) }
    }
}