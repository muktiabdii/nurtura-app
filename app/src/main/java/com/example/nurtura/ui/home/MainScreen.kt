package com.example.nurtura.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nurtura.ui.common.BottomNavBar
import com.example.nurtura.ui.mydoc.MyDocScreen
import com.example.nurtura.ui.myemotalk.FoodDetailScreen
import com.example.nurtura.ui.myemotalk.FoodRecsScreen
import com.example.nurtura.ui.profile.ProfileScreen
import com.example.nurtura.ui.trimester.TrimesterScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf("home", "mydoc", "profile")) {
                BottomNavBar(navController = navController, selected = currentRoute ?: "home")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(navController = navController)
            }

            composable("mydoc") {
                MyDocScreen()
            }

            composable("profile") {
                ProfileScreen()
            }

            composable("trimester/{trimesterNumber}") { backStackEntry ->
                val trimesterNumber = backStackEntry.arguments?.getString("trimesterNumber")?.toIntOrNull() ?: 1
                TrimesterScreen(trimesterNumber = trimesterNumber, navController = navController)
            }

            composable("food-recs") {
                FoodRecsScreen(navController = navController)
            }

            composable("food-detail/{foodId}") {
                val foodId = it.arguments?.getString("foodId")?: ""
                FoodDetailScreen(id = foodId, navController = navController)
            }
        }
    }
}