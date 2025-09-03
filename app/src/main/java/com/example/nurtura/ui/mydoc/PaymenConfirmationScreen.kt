package com.example.nurtura.ui.mydoc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.nurtura.cache.doctorList
import com.example.nurtura.ui.common.PaymentRow
import com.example.nurtura.ui.theme.*
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentConfirmationScreen(
    id: Int,
    navController: NavController
) {
    var uploadedFileName by remember { mutableStateOf<String?>(null) }
    val doctor = doctorList.find { it.id == id }
    val clipboardManager = LocalClipboardManager.current
    val textToCopy = "0110 2222 1111 000"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Bar
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
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_darker),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(onClick = { navController.popBackStack() })
                )

                Spacer(modifier = Modifier.weight(1f))

                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pesan Doktor",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        lineHeight = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }

        // Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Doctor Info Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Doctor Image
                        Box(
                            modifier = Modifier
                                .width(130.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(8.dp))
                        ) {
                            Image(
                                painter = painterResource(id = doctor?.image ?: 0),
                                contentDescription = "Doctor Photo",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = doctor?.name ?: "",
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                                color = Black
                            )
                            Text(
                                text = doctor?.clinic ?: "",
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.raleway_medium)),
                                color = Black
                            )
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = Accent
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "Proses Pembayaran",
                                    fontFamily = FontFamily(Font(R.font.raleway_bold)),
                                    color = White,
                                    fontSize = 11.sp,
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Payment Details
            Text(
                text = "Rincian Pembayaran",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                color = Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Payment Summary Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Alt1
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    PaymentRow("Subtotal", "Rp. 350.000")
                    PaymentRow("Diskon", "Rp. 0")
                    PaymentRow("Total", "Rp. 350.000", isTotal = true)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Bank Transfer Details
            Text(
                text = "Rekening Pembayaran",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                color = Black
            )

            Text(
                text = "Silahkan lakukan pembayaran melalui nomor rekening berikut",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                color = Alt2,
                lineHeight = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Bank Account Card
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .width(60.dp)
                        .height(40.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Light
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bri),
                        contentDescription = "BRI Logo",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "Bank BRI \nNURTURA FROM CARE TO NURTURE",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_medium)),
                        color = Black,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Account Number Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Alt1
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "0110 2222 1111 000",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        color = Accent
                    )

                    Button(
                        onClick = { clipboardManager.setText(AnnotatedString(textToCopy)) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = White,
                            contentColor = Primary
                        ),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, Accent)
                    ) {
                        Text(
                            text = "copy",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.raleway_bold)),
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Upload Payment Proof
            Text(
                text = "Upload Bukti Pembayaran",
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                color = Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Upload Area
            Card(
                onClick = { /* Handle click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Alt1
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = uploadedFileName ?: "Silahkan upload bukti pembayaran (file berupa jpg)",
                        fontSize = 10.sp,
                        color = if (uploadedFileName != null) Black else Alt2,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Image(
                        painter = painterResource(id = R.drawable.ic_upload),
                        contentDescription = "Upload",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Info Text and Confirm Payment Card (Full-screen background)
        Column(
            modifier = Modifier
                .fillMaxWidth() // Fill entire width
                .background(Alt1)
                .padding(16.dp), // Background spans full width
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Jika sudah selesai melakukan pembayaran, silahkan klik tombol di bawah agar kami dapat segera menyelesaikan proses transaksi",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.raleway_medium)),
                color = Accent,
                textAlign = TextAlign.Start,
                lineHeight = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )

            // Confirm Payment Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* Handle click */ },
                colors = CardDefaults.cardColors(
                    containerColor = Grey
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Konfirmasi Pembayaran",
                    color = White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
