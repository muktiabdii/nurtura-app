package com.example.nurtura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nurtura.data.datastore.PreferencesManager
import com.example.nurtura.data.repository.AuthRepositoryImpl
import com.example.nurtura.data.repository.UserRepositoryImpl
import com.example.nurtura.domain.usecase.AuthUseCase
import com.example.nurtura.domain.usecase.OnBoardingUseCase
import com.example.nurtura.domain.usecase.UserUseCase
import com.example.nurtura.ui.auth.AuthViewModel
import com.example.nurtura.ui.auth.LoginScreen
import com.example.nurtura.ui.auth.RegisterAdditionalInfoScreen
import com.example.nurtura.ui.auth.RegisterScreen
import com.example.nurtura.ui.home.MainScreen
import com.example.nurtura.ui.onboard.OnBoardingScreen
import com.example.nurtura.ui.splash.SplashScreen
import com.example.nurtura.ui.splash.SplashViewModel
import com.example.nurtura.ui.theme.NurturaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NurturaTheme {

                // insiate user
                val userRepo = UserRepositoryImpl(PreferencesManager(this))
                val userUseCase = UserUseCase(userRepo)

                // insiate on boarding
                val onBoardingUseCase = OnBoardingUseCase(PreferencesManager(this))
                val splashViewModel: SplashViewModel = viewModel(factory = SplashViewModel.Factory(userUseCase, onBoardingUseCase))

                // insiate auth
                val authRepo = AuthRepositoryImpl()
                val authUseCase = AuthUseCase(authRepo)
                val authViewModel: AuthViewModel = viewModel(factory = AuthViewModel.Factory(authUseCase, userUseCase))

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "splash",
                    ) {
                        composable("splash") {
                            SplashScreen(viewModel = splashViewModel, navController = navController)
                        }

                        composable("onboarding") {
                            OnBoardingScreen(viewModel = splashViewModel, navController = navController)
                        }

                        composable("login") {
                            LoginScreen(viewModel = authViewModel, navController = navController)
                        }

                        composable("register") {
                            RegisterScreen(viewModel = authViewModel, navController = navController)
                        }

                        composable("register-additional-info") {
                            RegisterAdditionalInfoScreen(viewModel = authViewModel, navController = navController)
                        }

                        composable("main") {
                            MainScreen(rootNavController = navController)
                        }
                    }
                }
            }
        }
    }
}
