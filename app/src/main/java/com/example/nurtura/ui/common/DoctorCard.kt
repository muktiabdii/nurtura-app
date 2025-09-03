package com.example.nurtura.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.Light
import com.example.nurtura.ui.theme.Primary
import com.example.nurtura.ui.theme.White

@Composable
fun DoctorCard(
    modifier: Modifier = Modifier,
    doctorName: String,
    doctorImage: Int,
    clinic: String,
    rating: Float,
    patientCount: Int,
    onBookClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        border = BorderStroke(4.dp, Primary),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Doctor Image
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Light),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = doctorImage),
                    contentDescription = "Doctor Photo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Doctor Name
            Text(
                text = doctorName,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                color = Black,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Clinic Name
            Text(
                text = clinic,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                color = Black,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Rating and Patient Count Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Rating Section
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFF9D159),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = rating.toString(),
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        color = Black
                    )
                }

                // Patient Count Section
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Patients",
                        modifier = Modifier
                            .size(16.dp)
                            .graphicsLayer(alpha = 0.99f)
                            .drawWithCache {
                                val brush = Brush.verticalGradient(
                                    colors = listOf(Color(0xFF638ECB), Color(0xFFCFDCEF))
                                )
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(brush, blendMode = androidx.compose.ui.graphics.BlendMode.SrcAtop)
                                }
                            },
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "+$patientCount",
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        color = Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Book Button
            Button(
                onClick = onBookClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Pesan Dokter",
                    color = White,
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
