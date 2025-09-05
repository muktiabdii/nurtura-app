package com.example.nurtura.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nurtura.R
import com.example.nurtura.cache.trimesterList
import com.example.nurtura.ui.theme.Accent
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.Primary
import com.example.nurtura.ui.theme.White

@Composable
fun TrimesterCard(
    currentTrimester: Int,
    modifier: Modifier = Modifier,
    onReadMoreClick: (Int) -> Unit = {}
) {

    val currentData = trimesterList.firstOrNull { it.trimesterNumber == currentTrimester } ?: trimesterList[0]

    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        shape = RoundedCornerShape(size = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentData.title,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                textAlign = TextAlign.Center,
                style = androidx.compose.ui.text.TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                    )
                )
            )

            Spacer(Modifier.height(height = 8.dp))

            Text(text = currentData.subtitle,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                color = Black,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(height = 24.dp))

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baby_home),
                    contentDescription = null,
                    modifier = Modifier
                        .size(size = 120.dp)
                        .clickable { onReadMoreClick(currentTrimester) },
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(Modifier.height(24.dp))

            Text(text = "Trimester $currentTrimester",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                style = androidx.compose.ui.text.TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                    )
                )
            )

            Spacer(Modifier.height(16.dp))

            Text(text = currentData.description,
                fontSize = 14.sp,
                color = Black,
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )

            Text(
                text = "Baca Selengkapnya",
                fontSize = 13.sp,
                fontFamily = FontFamily(Font(R.font.raleway_medium)),
                color = Primary,
                modifier = Modifier
                    .clickable { onReadMoreClick(currentTrimester) }
                    .padding(8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}
