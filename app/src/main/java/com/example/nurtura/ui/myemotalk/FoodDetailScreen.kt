@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.nurtura.ui.myemotalk

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Alt3
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.White

@Composable
fun FoodDetailScreen(
    navController: NavController,
    id: String,
    viewModel: MyEmoTalkViewModel
) {

    // state
    val food by viewModel.foodDetail.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadFoodDetail(id)
    }

    Box(modifier = Modifier.fillMaxSize().background(White)) {
        if (isLoading) {

            // loading indicator
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            food?.let { foodData ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
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
                                modifier = Modifier.fillMaxSize().offset(y = (-10).dp),
                                contentScale = ContentScale.FillBounds
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 10.dp, end = 10.dp, bottom = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                // back button
                                Image(
                                    painter = painterResource(id = R.drawable.ic_back_darker),
                                    contentDescription = "Back",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clickable { navController.popBackStack() }
                                )

                                // title
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "Nurtura Food Recommendation",
                                        color = White,
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = foodData.name ?: "",
                                        color = White,
                                        fontSize = 14.sp,
                                        fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                                        textAlign = TextAlign.Center,
                                        maxLines = 2,
                                        lineHeight = 18.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.widthIn(max = 200.dp)
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
                                    .height(160.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = foodData.image),
                                    contentDescription = null,
                                    modifier = Modifier.size(160.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }

                            Column(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "Bahan",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                                    color = Black
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                foodData.ingredients?.forEach { point ->
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
                                Text(
                                    text = "Cara Membuat",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                                    color = Black
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                foodData.steps?.forEachIndexed { index, point ->
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
            } ?: run {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Tidak ada data makanan",
                        color = Black,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_medium))
                    )
                }
            }
        }
    }
}
