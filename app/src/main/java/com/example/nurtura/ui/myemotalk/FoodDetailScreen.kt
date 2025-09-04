@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.nurtura.ui.myemotalk

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nurtura.R
import com.example.nurtura.cache.UserData
import com.example.nurtura.domain.model.Food
import com.example.nurtura.ui.theme.Alt3
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.White

@Composable
fun FoodDetailScreen(
    navController: NavController,
    id: Int
) {

    val food = UserData.foodRecommendations[id]

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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back_darker),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable(onClick = { navController.popBackStack() })
                    )

                    // title and subtitle
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Nurtura Food Recommendation",
                            color = White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                            lineHeight = 20.sp,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = food?.name ?: "",
                            color = White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                            lineHeight = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Star Icon",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            // main card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = CardDefaults.cardColors(containerColor = White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(160.dp),
                    contentAlignment = Alignment.Center
                ) {

                    // gambar
                    Image(
                        painter = painterResource(id = food?.image ?: 0),
                        contentDescription = null,
                        modifier = Modifier
                            .size(160.dp),
                        contentScale = ContentScale.Fit
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {

                    // ingredients
                    Text(
                        text = "Bahan",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                        textAlign = TextAlign.Start,
                        color = Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    food?.ingredients?.forEach { point ->
                        Text(
                            text = "• $point",
                            fontSize = 14.sp,
                            color = Alt3,
                            fontFamily = FontFamily(Font(R.font.raleway_medium)),
                            lineHeight = 20.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // steps
                    Text(
                        text = "Cara Membuat",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                        textAlign = TextAlign.Start,
                        color = Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    food?.steps?.forEachIndexed { index, point ->
                        Text(
                            text = "${index + 1}. $point",
                            fontSize = 14.sp,
                            color = Alt3,
                            fontFamily = FontFamily(Font(R.font.raleway_medium)),
                            lineHeight = 20.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
