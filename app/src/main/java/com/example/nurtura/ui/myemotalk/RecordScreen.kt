@file:OptIn(ExperimentalAnimationApi::class)

package com.example.nurtura.ui.myemotalk

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.math.sin
import kotlin.random.Random
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Accent
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.White

@Composable
fun RecordScreen(
    viewModel: MyEmoTalkViewModel,
    navController: NavController
) {

    val context = LocalContext.current
    val isRecording by viewModel.isRecording.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val foodId by viewModel.foodId.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.startRecording(context) 
        }

        else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(android.Manifest.permission.RECORD_AUDIO)
    }

    LaunchedEffect(foodId) {
        foodId?.let { id ->
            navController.navigate("food-detail/$id") {
                popUpTo("record") { inclusive = true }
            }
            viewModel.resetFoodId()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {

        // header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.TopCenter)
        ) {
            // background header
            Image(
                painter = painterResource(id = R.drawable.header_home),
                contentDescription = "Header Background",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            // text header
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp, top = 40.dp)
            ) {
                Text(
                    text = "Ask Nurtura",
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    color = White
                )
            }

            // mic button
            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(start = 30.dp, top = 30.dp, end = 50.dp, bottom = 30.dp)
                    .size(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mic),
                    contentDescription = "Voice Search",
                    modifier = Modifier
                        .size(100.dp)
                        .graphicsLayer(alpha = 0.99f)
                        .drawWithCache {
                            val brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                            )
                            onDrawWithContent {
                                drawContent()
                                drawRect(brush, blendMode = BlendMode.SrcAtop)
                            }
                        }
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // sound wave
            AnimatedSoundWaves(
                isRecording = isRecording,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Aku lagi sedih banget, enaknya makan apa ya . . .",
                color = Black,
                fontFamily = FontFamily(Font(R.font.raleway_medium)),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            IconButton(
                onClick = { if (!isLoading) viewModel.stopRecordingAndSend() },
                modifier = Modifier.size(65.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = Accent,
                        strokeWidth = 4.dp,
                        modifier = Modifier.size(40.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_stop),
                        contentDescription = "Stop Recording",
                        modifier = Modifier.size(54.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }
}

@Composable
fun AnimatedSoundWaves(
    isRecording: Boolean,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "soundWaves")

    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "waveOffset"
    )

    var waveHeights by remember { mutableStateOf(generateWaveHeights()) }

    LaunchedEffect(isRecording) {
        if (isRecording) {
            while (isRecording) {
                kotlinx.coroutines.delay(150)
                waveHeights = generateWaveHeights()
            }
        }
    }

    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val barWidth = 7.dp.toPx()
        val barSpacing = 8.dp.toPx()
        val totalBarWidth = barWidth + barSpacing
        val barCount = (canvasWidth / totalBarWidth).toInt()

        if (isRecording) {
            for (i in 0 until barCount) {
                val x = i * totalBarWidth + barWidth / 2
                val heightMultiplier = if (i < waveHeights.size) waveHeights[i] else 0.3f
                val barHeight = canvasHeight * heightMultiplier

                val animatedHeight = barHeight * (0.5f + 0.5f * sin(
                    (waveOffset + i * 30) * Math.PI / 180
                ).toFloat())

                val startY = (canvasHeight - animatedHeight) / 2
                val endY = startY + animatedHeight

                drawLine(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                    ),
                    start = Offset(x, startY),
                    end = Offset(x, endY),
                    strokeWidth = barWidth,
                    cap = StrokeCap.Round
                )
            }
        } else {
            for (i in 0 until barCount) {
                val x = i * totalBarWidth + barWidth / 2
                val staticHeight = canvasHeight * 0.15f
                val startY = (canvasHeight - staticHeight) / 2
                val endY = startY + staticHeight

                drawLine(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                    ),
                    start = Offset(x, startY),
                    end = Offset(x, endY),
                    strokeWidth = barWidth,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

private fun generateWaveHeights(): List<Float> {
    return List(20) {
        Random.nextFloat() * 0.8f + 0.2f
    }
}
