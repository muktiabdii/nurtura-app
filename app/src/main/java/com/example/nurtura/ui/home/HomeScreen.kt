package com.example.nurtura.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nurtura.R
import com.example.nurtura.cache.UserData
import com.example.nurtura.cache.doctorList
import com.example.nurtura.ui.common.AskNurturaCard
import com.example.nurtura.ui.common.ClickableSearchBar
import com.example.nurtura.ui.common.DoctorCard
import com.example.nurtura.ui.common.TrimesterCard
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.White

@Composable
fun HomeScreen(navController: NavController) {

    val doctors = doctorList

    val trimesterNumber = UserData.pregnancyAge

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        // header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            ) {

                // background
                Image(
                    painter = painterResource(id = R.drawable.header_home),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(45.dp)
                                .clip(CircleShape)
                                .clickable{ navController.navigate("profile") }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ic_notif),
                            contentDescription = "Notification",
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // title and subtitle
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Selamat Datang, Bunda ✨ !",
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                            textAlign = TextAlign.Center,
                            color = Black
                        )
                        Text(
                            text = "Apa kabarmu hari ini? Semoga selalu bahagia yaah ~",
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_regular)),
                            textAlign = TextAlign.Center,
                            color = Black
                        )
                    }
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // search bar
                ClickableSearchBar(onClick = { navController.navigate("food-recs") })

                // nurtura card
                AskNurturaCard(
                    onFoodRecsClick = { navController.navigate("food-recs") },
                    onAskNurturaClick = { navController.navigate("record") }
                )

                // trimester
                TrimesterCard(
                    currentTrimester = trimesterNumber,
                    onReadMoreClick = { navController.navigate("trimester/$trimesterNumber") }
                )

                // another content
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.6f)
                            .padding(end = 8.dp)
                    ) {
                        Text(
                            text = "Sahabat Kesehatan Ibu & Si Kecil 👩‍🍼🌸",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                            color = Black
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Cari dokter yang siap mengerti Bunda di setiap langkah 💗",
                            fontSize = 13.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_regular)),
                            lineHeight = 20.sp,
                            color = Black
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.image_home),
                        contentDescription = "Banner",
                        modifier = Modifier
                            .weight(0.4f)
                            .aspectRatio(1.2f),
                        contentScale = ContentScale.Fit
                    )
                }

                // doctor recommendation
                Text(
                    text = "Rekomendasi dokter pilihan 👩🏼‍⚕️",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                    style = androidx.compose.ui.text.TextStyle(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF273951), Color(0xFF5980B7))
                        )
                    )
                )
            }
        }

        // doctor card
        items(doctors.chunked(2)) { rowDoctors ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowDoctors.forEach { doctor ->
                    Box(modifier = Modifier.weight(1f)) {
                        DoctorCard(
                            doctorName = doctor.name,
                            doctorImage = doctor.image,
                            clinic = doctor.clinic,
                            rating = doctor.rating,
                            patientCount = doctor.patientCount,
                            onBookClick = { navController.navigate("doctor-detail/${doctor.id}") }
                        )
                    }
                }
                if (rowDoctors.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
