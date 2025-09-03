package com.example.nurtura.ui.mydoc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nurtura.R
import com.example.nurtura.cache.doctorList
import com.example.nurtura.ui.common.DoctorStatsCard
import com.example.nurtura.ui.common.ScheduleCard
import com.example.nurtura.ui.theme.Alt2
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDetailScreen(
    navController: NavController,
    id: Int
) {
    val doctor = doctorList.find { it.id == id }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
    ) {

        // header
        item {
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

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Pesan Doktor",
                            color = White,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
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

            Spacer(modifier = Modifier.height(20.dp))
        }

        // doctor image
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp)
                    .width(160.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = doctor?.image ?: 0),
                    contentDescription = "Doctor Photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // doctor name & clinic
        item {
            Text(
                text = doctor?.name ?: "",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = doctor?.clinic ?: "",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.raleway_medium)),
                color = Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        // doctor stats
        item {
            DoctorStatsCard(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                patientCount = doctor?.patientCount ?: 0,
                rating = doctor?.rating ?: 0f,
                experience = doctor?.experience ?: 0
            )

            Spacer(modifier = Modifier.height(32.dp))
        }

        // schedule
        item {
            Text(
                text = "Informasi Jadwal Praktek Tersedia",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                color = Black,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                doctor?.schedule?.forEach { schedule ->
                    ScheduleCard(
                        hari = schedule.day,
                        tanggal = schedule.date,
                        jam = schedule.time,
                        available = schedule.available
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }

        // button
        item {
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Alt2
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Pesan Dokter",
                    color = White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
