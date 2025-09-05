package com.example.nurtura.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.nurtura.R
import com.example.nurtura.ui.theme.White

@Composable
fun SplashScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel
) {
    var startAnimation by remember { mutableStateOf(false) }
    val logoAlpha = remember { Animatable(0f) }
    val logoOffsetY = remember { Animatable(0f) }
    val textAlpha = remember { Animatable(0f) }

    val isOnBoardingShown by viewModel.isOnBoardingShown().collectAsState(initial = false)
    val userUid by viewModel.getUserUidFlow().collectAsState(initial = null)

    LaunchedEffect(true) {
        startAnimation = true

        // step 1: logo fade in di tengah
        logoAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1200)
        )

        delay(800)

        // step 2: logo naik ke atas sedikit sambil text fade in
        launch {
            logoOffsetY.animateTo(
                targetValue = -30f,
                animationSpec = tween(durationMillis = 800)
            )
        }

        // step 3: text fade in bersamaan dengan logo naik
        delay(200)
        textAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )

        delay(1500)

        // navigation logic
        if (!isOnBoardingShown) {
            navController.navigate("onboarding") {
                popUpTo("splash") { inclusive = true }
            }
        }
        else {
            if (userUid.isNullOrEmpty()) {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            else {
                viewModel.loadUser(userUid!!)
                navController.navigate("main") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        contentAlignment = Alignment.Center
    ) {

        // background
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = null,
            modifier = Modifier
                .size(500.dp)
                .offset(x = (-250).dp, y = (-250).dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop
        )

        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = null,
            modifier = Modifier
                .size(500.dp)
                .offset(x = (250).dp, y = (250).dp)
                .align(Alignment.BottomEnd),
            contentScale = ContentScale.Crop
        )

        // logo dan text
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
                    .alpha(logoAlpha.value)
                    .offset(y = logoOffsetY.value.dp)
            )

            Text(
                text = "Nurtura",
                fontSize = 50.sp,
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                style = androidx.compose.ui.text.TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF233247), Color(0xFF5980B7))
                    )
                ),
                modifier = Modifier.alpha(textAlpha.value)
            )
        }
    }
}