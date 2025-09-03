package com.example.nurtura.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.Grey
import com.example.nurtura.ui.theme.Secondary
import com.example.nurtura.ui.theme.White
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Primary

@Composable
fun ScheduleCard(
    modifier: Modifier = Modifier,
    hari: String,
    tanggal: String,
    jam: String,
    available: Boolean
) {
    Card(
        modifier = modifier
            .width(75.dp)
            .height(75.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (available) Secondary else White
        ),
        border = BorderStroke(1.dp, color = if (available) Secondary else Grey),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = hari,
                color = if (available) Black else Grey,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                textAlign = TextAlign.Center
            )

            Text(
                text = tanggal,
                color = if (available) Black else Grey,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                textAlign = TextAlign.Center
            )

            Text(
                text = jam,
                color = if (available) Black else Grey,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                textAlign = TextAlign.Center
            )
        }
    }
}
