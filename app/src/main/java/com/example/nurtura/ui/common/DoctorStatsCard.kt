package com.example.nurtura.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nurtura.ui.theme.Secondary
import com.example.nurtura.ui.theme.White
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Black

@Composable
fun DoctorStatsCard(
    modifier: Modifier = Modifier,
    patientCount: Int,
    rating: Float,
    experience: Int
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Secondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // patient count
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Pasien",
                    color = White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "+$patientCount",
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.width(12.dp))
            DividerVertical()
            Spacer(modifier = Modifier.width(12.dp))

            // top rating
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Top Rating",
                    color = White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star Rating",
                        tint = Color(0xFFF9D159),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$rating",
                        color = Black,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))
            DividerVertical()
            Spacer(modifier = Modifier.width(12.dp))

            // experience years
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Exp. Years",
                    color = White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "$experience years",
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DividerVertical() {
    Box(
        modifier = Modifier
            .height(40.dp)
            .width(1.dp)
            .background(Black)
    )
}
