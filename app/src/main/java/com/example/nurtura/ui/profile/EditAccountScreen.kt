package com.example.nurtura.ui.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nurtura.R
import com.example.nurtura.cache.UserData
import com.example.nurtura.ui.common.ActionButton
import com.example.nurtura.ui.common.InputFormField
import com.example.nurtura.ui.theme.*

@Composable
fun EditAccountScreen(navController: NavController) {

    val user = UserData

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

        // header
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "Edit Profile",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    color = Black,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(60.dp))

            // login/ sign up toggle
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(43.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .height(30.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        // login tab
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .background(
                                    color = Accent,
                                    shape = RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Akun",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                                    color = Color.White
                                )
                            )
                        }

                        // sign up tab
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .clickable {
                                    navController.navigate("edit-personal-data") {
                                        popUpTo("edit-account") { inclusive = true }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Data Diri",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                                    color = Grey
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                // username
                Text(
                    text = "Username",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                        color = Accent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                InputFormField(
                    value = user.name,
                    onValueChange = {  },
                    placeholder = ""
                )

                Spacer(modifier = Modifier.height(2.dp))

                // email
                Text(
                    text = "Email",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                        color = Accent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                InputFormField(
                    value = user.email,
                    onValueChange = {  },
                    placeholder = "masukkan email"
                )

                Spacer(modifier = Modifier.height(2.dp))

                // password
                Text(
                    text = "Password",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                        color = Accent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                InputFormField(
                    value = "",
                    onValueChange = {  },
                    placeholder = "masukkan password",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(2.dp))

                // password konfirmasi
                Text(
                    text = "Konfirmasi Password",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                        color = Accent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                InputFormField(
                    value = "",
                    onValueChange = {  },
                    placeholder = "masukkan konfirmasi password",
                    isPassword = true
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // save button
            ActionButton(
                text = "Simpan",
                onClick = {  }
            )
        }
    }
}
