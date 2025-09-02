package com.example.nurtura.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.White

@Composable
fun OnBoardingContent(currentPage: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // title
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = when (currentPage) {
                    0 -> "Selamat Datang di Nurtura👋!"
                    1 -> "Ceritalah, Nurtura memahami 🎤!"
                    else -> "Aman, Praktis, Terorganisir 🤝!"
                },
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                color = Black,
                textAlign = TextAlign.Start,
                lineHeight = 36.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // description
            Text(
                text = when (currentPage) {
                    0 -> "From care to nurture, with Nurtura. Teman setia perjalanan kehamilan Bunda, hadir mendukung setiap langkah."
                    1 -> "Rekam suara dan biarkan Nurtura mendeteksi suasana hati Bunda. Bantu jaga kesehatan mental dengan cara yang sederhana."
                    else -> "Butuh konsultasi? Pesan dokter langsung lewat Nurtura, tanpa ribet pindah aplikasi. Praktis dan terorganisir untuk Bunda."
                },
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Start,
                lineHeight = 24.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // ilustration
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = when (currentPage) {
                        0 -> R.drawable.image_onboard_1
                        1 -> R.drawable.image_onboard_2
                        else -> R.drawable.image_onboard_3
                    }
                ),
                contentDescription = null,
                modifier = Modifier.size(250.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
