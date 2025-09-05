package com.example.nurtura.ui.mydoc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import com.example.nurtura.cache.doctorList
import com.example.nurtura.ui.common.DoctorCard
import com.example.nurtura.ui.common.SearchBar
import com.example.nurtura.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDocScreen(navController: NavController) {

    val doctors = doctorList

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 30.dp)
    ) {

        // header
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {

                // background
                Image(
                    painter = painterResource(id = R.drawable.header_home),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillBounds
                )

                // content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // title and subtitle
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Untuk Bunda & Buah Hati ✨",
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                            textAlign = TextAlign.Start,
                            color = White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Rekomendasi dokter pilihan, penuh perhatian.",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_regular)),
                            textAlign = TextAlign.Start,
                            style = androidx.compose.ui.text.TextStyle(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                                )
                            )
                        )
                    }
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // search bar
                    Box(modifier = Modifier.weight(1f)) {
                        SearchBar(placeholder = "Temukan Dokter")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // notification
                    IconButton(
                        onClick = {  },
                        modifier = Modifier.size(48.dp),
                        content = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_notif),
                                contentDescription = "Notification",
                                modifier = Modifier.size(44.dp)
                            )
                        }
                    )
                }
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
