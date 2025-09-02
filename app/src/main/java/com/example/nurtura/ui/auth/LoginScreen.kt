package com.example.nurtura.ui.auth

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
import com.example.nurtura.ui.common.ActionButton
import com.example.nurtura.ui.common.InputFormField
import com.example.nurtura.ui.theme.*

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {

    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    when (loginState) {
        is State.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate("main") {
                    popUpTo("login") { inclusive = true }
                }

                viewModel.resetLoginState()
            }
        }

        is State.Error -> {
            val message = (loginState as State.Error).message
            LaunchedEffect(message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                viewModel.resetLoginState()
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

        // header
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = "Selamat Datang Bunda",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                    color = Black,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Ayo lanjutkan perjalanan sehat bersama Nurtura",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_regular)),
                    color = Black,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

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
                                text = "Login",
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
                                    navController.navigate("register") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Sign Up",
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
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "masukkan email",
                    leadingIcon = R.drawable.ic_email
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
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "masukkan password",
                    leadingIcon = R.drawable.ic_lock,
                    isPassword = true
                )

                // forgot password
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Lupa password?",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                            color = Accent
                        ),
                        modifier = Modifier.clickable { navController.navigate("forgot_password") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // login button
            ActionButton(
                text = "Login",
                onClick = { viewModel.login(email, password) },
                isLoading = loginState is State.Loading
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Atau Login dengan",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_medium)),
                    color = Black
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // google login button
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = "Google",
                modifier = Modifier
                    .size(30.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // sign up
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Belum memiliki akun? ",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_medium)),
                        color = Black
                    )
                )
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                        color = Primary
                    ),
                    modifier = Modifier.clickable {
                        navController.navigate("register") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
