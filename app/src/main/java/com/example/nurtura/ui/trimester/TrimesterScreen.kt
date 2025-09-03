@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.nurtura.ui.trimester

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nurtura.R
import com.example.nurtura.cache.trimesterList
import com.example.nurtura.domain.model.Trimester
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.White

@Composable
fun TrimesterScreen(
    navController: NavController,
    trimesterNumber: Int
) {

    val trimester = trimesterList.find { it.trimesterNumber == trimesterNumber }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        item {

            // header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.header_home),
                    contentDescription = "Header Background",
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = (-10).dp),
                    contentScale = ContentScale.FillBounds
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back_darker),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable(onClick = { navController.popBackStack() })
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Progress Perjalanan Bunda dan Si Kecil\nTrimester $trimesterNumber",
                        color = White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // main card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = trimester?.title ?: "",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        textAlign = TextAlign.Center,
                        style = androidx.compose.ui.text.TextStyle(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                            )
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = trimester?.subtitle ?: "",
                        fontSize = 14.sp,
                        color = Black,
                        fontFamily = FontFamily(Font(R.font.raleway_regular)),
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Box(
                        modifier = Modifier
                            .size(160.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baby_home),
                            contentDescription = "Baby Illustration",
                            modifier = Modifier.size(160.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Trimester $trimesterNumber",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        textAlign = TextAlign.Center,
                        style = androidx.compose.ui.text.TextStyle(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                            )
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = trimester?.description ?: "",
                        fontSize = 14.sp,
                        color = Black,
                        fontFamily = FontFamily(Font(R.font.raleway_regular)),
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                }
            }

            // development section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Perkembangan Janin 👶",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                            style = androidx.compose.ui.text.TextStyle(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                                )
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        trimester?.fetalDevelopment?.forEach { point ->
                            Text(
                                text = point,
                                fontSize = 12.sp,
                                color = Black,
                                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                                lineHeight = 16.sp,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier.size(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baby_trimester),
                            contentDescription = "Baby Development",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            // tips section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "\uD83D\uDCA1 Tips untuk Bunda \uD83D\uDCA1",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        style = androidx.compose.ui.text.TextStyle(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                            )
                        ),
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = trimester?.tips ?: "",
                        fontSize = 14.sp,
                        color = Black,
                        fontFamily = FontFamily(Font(R.font.raleway_regular)),
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}
