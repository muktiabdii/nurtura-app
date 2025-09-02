package com.example.nurtura.ui.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nurtura.R
import com.example.nurtura.ui.common.ActionButton
import com.example.nurtura.ui.common.InputFormField
import com.example.nurtura.ui.theme.*

@Composable
fun RegisterAdditionalInfoScreen(
    viewModel: AuthViewModel,
    navController: NavController
) {

    val context = LocalContext.current
    val registerState by viewModel.registerUiState.collectAsState()

    when (registerState.state) {
        is State.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate("login") {
                    popUpTo("register") { inclusive = true }
                }
                viewModel.resetRegisterState()
            }
        }

        is State.Error -> {
            val message = (registerState.state as State.Error).message
            LaunchedEffect(message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                viewModel.resetRegisterState()
            }
        }

        else -> Unit
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {

        // background
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = null,
            modifier = Modifier
                .size(500.dp)
                .offset(x = (-250).dp, y = (-250).dp)
                .align(Alignment.TopStart),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = null,
            modifier = Modifier
                .size(500.dp)
                .offset(x = (250).dp, y = (250).dp)
                .align(Alignment.BottomEnd),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // back button
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { viewModel.previousStep() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // konten sesuai step
            when (registerState.step) {
                2 -> {

                    // title
                    Text(
                        text = "Halo, Bunda. Di Trimester berapa Bunda sekarang?",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                            color = Black,
                            textAlign = TextAlign.Center,
                            lineHeight = 28.sp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // deskripsi
                    Text(
                        text = "Data ini akan dipakai untuk menentukan progress si Kecil yah~",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_regular)),
                            color = Black,
                            textAlign = TextAlign.Center,
                            lineHeight = 18.sp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // trimester input
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = "Usia Kehamilan (Trimester)",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                                    color = Black
                                )
                            )
                            InputFormField(
                                value = registerState.pregnancyAge,
                                onValueChange = { viewModel.updatePregnancyAge(it) },
                                placeholder = "contoh : Trimester 2"
                            )
                        }

                        // health notes input
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = "Catatan kesehatan (Riwayat Penyakit)",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                                    color = Black
                                )
                            )
                            InputFormField(
                                value = registerState.healthNotes,
                                onValueChange = { viewModel.updateHealthNotes(it) },
                                placeholder = "contoh : Darah Tinggi"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // button lanjut
                    ActionButton(
                        text = "Lanjut",
                        onClick = { viewModel.nextStep() },
                        modifier = Modifier.padding(bottom = 40.dp)
                    )
                }

                3 -> {

                    // title
                    Text(
                        text = "Halo, Bunda. Sedang menetap dimana sekarang?",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                            color = Black,
                            textAlign = TextAlign.Center,
                            lineHeight = 28.sp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // deskripsi
                    Text(
                        text = "Data ini akan dipakai untuk menemukan dokter spesialis di sekitar Bunda yah ~",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_regular)),
                            color = Black,
                            textAlign = TextAlign.Center,
                            lineHeight = 18.sp
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // location input
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = "Tempat Tinggal",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                                    color = Black
                                )
                            )
                            InputFormField(
                                value = registerState.location,
                                onValueChange = { viewModel.updateLocation(it) },
                                placeholder = "contoh : Kota Malang"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    // register button
                    ActionButton(
                        text = "Selesai",
                        onClick = { viewModel.register() },
                        modifier = Modifier.padding(bottom = 40.dp),
                        isLoading = registerState.state is State.Loading
                    )
                }
            }
        }
    }
}
