package com.example.nurtura.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nurtura.data.remote.api.CloudinaryService
import com.example.nurtura.data.repository.CloudinaryRepositoryImpl
import com.example.nurtura.data.repository.GeminiRepositoryImpl
import com.example.nurtura.data.repository.MyEmoTalkRepositoryImpl
import com.example.nurtura.domain.repository.GeminiRepository
import com.example.nurtura.domain.usecase.CloudinaryUseCase
import com.example.nurtura.domain.usecase.GeminiUseCase
import com.example.nurtura.domain.usecase.MyEmoTalkUseCase
import com.example.nurtura.ui.common.BottomNavBar
import com.example.nurtura.ui.mydoc.DoctorDetailScreen
import com.example.nurtura.ui.mydoc.MyDocScreen
import com.example.nurtura.ui.mydoc.MyDocViewModel
import com.example.nurtura.ui.mydoc.PaymentConfirmationScreen
import com.example.nurtura.ui.myemotalk.FoodDetailScreen
import com.example.nurtura.ui.myemotalk.FoodRecsScreen
import com.example.nurtura.ui.myemotalk.MyEmoTalkViewModel
import com.example.nurtura.ui.myemotalk.RecordScreen
import com.example.nurtura.ui.profile.ProfileScreen
import com.example.nurtura.ui.trimester.TrimesterScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    // inisiasi
    val cloudinaryRepo = CloudinaryRepositoryImpl(service = CloudinaryService.instance, context = LocalContext.current)
    val cloudinaryUseCase = CloudinaryUseCase(cloudinaryRepo)
    val cloudinaryViewModel: MyDocViewModel = viewModel(factory = MyDocViewModel.Factory(cloudinaryUseCase))

    val geminiRepo = GeminiRepositoryImpl()
    val geminiUseCase = GeminiUseCase(geminiRepo)

    val myEmoTalkRepo = MyEmoTalkRepositoryImpl(context = LocalContext.current)
    val myEmoTalkUseCase = MyEmoTalkUseCase(myEmoTalkRepo)
    val myEmoTalkViewModel: MyEmoTalkViewModel = viewModel(factory = MyEmoTalkViewModel.Factory(myEmoTalkUseCase, geminiUseCase))

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
                MyDocScreen(navController = navController)
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
                FoodDetailScreen(id = foodId, navController = navController, viewModel = myEmoTalkViewModel)
            }

            composable("doctor-detail/{doctorId}") {
                val doctorId = it.arguments?.getString("doctorId")?.toIntOrNull() ?: 1
                DoctorDetailScreen(id = doctorId, navController = navController)
            }

            composable("payment-confirmation/{doctorId}") {
                val doctorId = it.arguments?.getString("doctorId")?.toIntOrNull() ?: 1
                PaymentConfirmationScreen(id = doctorId, navController = navController, viewModel = cloudinaryViewModel)
            }

            composable("record") {
                RecordScreen(viewModel = myEmoTalkViewModel, navController = navController)
            }
        }
    }
}