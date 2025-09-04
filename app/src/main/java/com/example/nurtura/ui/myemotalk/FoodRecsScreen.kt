package com.example.nurtura.ui.myemotalk

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import com.example.nurtura.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nurtura.domain.model.Food
import com.example.nurtura.ui.common.FoodCard
import com.example.nurtura.ui.common.SearchBar
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.White

@Composable
fun FoodRecsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MyEmoTalkViewModel
) {
    // Collect state
    val isLoading by viewModel.isLoading.collectAsState()
    val foodList by viewModel.foodRecommendations.collectAsState()

    // Trigger load saat pertama kali screen dibuka
    LaunchedEffect(Unit) {
        viewModel.loadAllFoodRecommendations()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        if (isLoading) {
            // Loading indicator
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material3.CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(bottom = 20.dp)
            ) {
                // header
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.header_home),
                            contentDescription = "Header Background",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )

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

                        IconButton(
                            onClick = { navController.navigate("record") },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(30.dp)
                                .size(50.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_mic),
                                contentDescription = "Voice Search",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(24.dp)) }

                // search bar
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.size(54.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_back_lighter),
                                contentDescription = "back",
                                modifier = Modifier.size(54.dp),
                                contentScale = ContentScale.FillBounds
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Box(modifier = Modifier.weight(1f)) {
                            SearchBar(placeholder = "Temukan Makanan")
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(24.dp)) }

                // title
                item {
                    Text(
                        text = "Rekomendasi Makanan",
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        color = Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                // food grid
                items(foodList.chunked(2)) { rowItems ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        for (food in rowItems) {
                            FoodCard(
                                food = food,
                                modifier = Modifier.weight(1f),
                                onClick = { navController.navigate("food-detail/${food.id}") }
                            )
                        }
                        if (rowItems.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}
