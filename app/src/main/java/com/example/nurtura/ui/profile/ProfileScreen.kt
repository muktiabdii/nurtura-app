@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.nurtura.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.example.nurtura.ui.common.ProfileDialog
import com.example.nurtura.ui.common.SettingItem
import com.example.nurtura.ui.theme.Accent
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.Light
import com.example.nurtura.ui.theme.Primary
import com.example.nurtura.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    rootNavController: NavController,
    viewModel: UserViewModel
) {

    var showDialogLogout by remember { mutableStateOf(false) }
    var showDialogDelete by remember { mutableStateOf(false) }
    val user = UserData

    if (showDialogLogout) {
        ProfileDialog(
            title = "Apakah kamu yakin untuk Logout dari Nurtura?",
            action = "Logout",
            onConfirmDelete = {
                viewModel.logout()
                rootNavController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                }
                showDialogLogout = false
            },
            onCancel = { showDialogLogout = false },
            onDismissRequest = { showDialogLogout = false }
        )
    }

    if (showDialogDelete) {
        ProfileDialog(
            title = "Apakah kamu yakin untuk menghapus akun Nurtura?",
            action = "Hapus Akun",
            onConfirmDelete = {
                viewModel.deleteAccount()
                rootNavController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                    showDialogDelete = false
                }
            },
            onCancel = { showDialogDelete = false },
            onDismissRequest = { showDialogDelete = false }
        )
    }

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
                    .height(280.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.header_profile),
                    contentDescription = "Header background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                // profile
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .size(45.dp)
                                .clip(CircleShape)
                                .clickable{ navController.navigate("edit-account") },
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.tint(White)
                        )

                        Spacer(modifier = Modifier.width(18.dp))

                        // name and location
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = user.name,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                                color = Black
                            )

                            Text(
                                text = user.location,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                                color = Black
                            )
                        }

                        // action buttons
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(Color(0xFFF8F8F8), RoundedCornerShape(5.dp))
                                    .clickable { /* Handle share */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_share),
                                    contentDescription = "Share",
                                    tint = Primary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(Color(0xFFF8F8F8), RoundedCornerShape(5.dp))
                                    .clickable { navController.navigate("edit-account") },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_edit),
                                    contentDescription = "Edit",
                                    tint = Primary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    // pregnancy info card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Light
                        ),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Saat ini, Bunda Berada di",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                                color = Accent,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = "Trimester ${user.pregnancyAge}",
                                fontSize = 24.sp,
                                fontFamily = FontFamily(Font(R.font.raleway_bold)),
                                textAlign = TextAlign.Center,
                                style = androidx.compose.ui.text.TextStyle(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color(0xFF5980B7), Color(0xFF273951))
                                    )
                                )
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Lakukan daily check secara berkala ya, Bun 💗",
                                fontSize = 11.sp,
                                fontFamily = FontFamily(Font(R.font.raleway_regular)),
                                color = Black,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))

            // settings section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Light
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Pengaturan",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.raleway_semi_bold)),
                        color = Black,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // notification setting
                    SettingItem(
                        icon = R.drawable.ic_notif_stroke,
                        title = "Notifikasi",
                        hasSwitch = true,
                        onSwitchChange = { /* Handle switch */ }
                    )

                    // other menu items
                    SettingItem(
                        icon = R.drawable.ic_faq,
                        title = "FAQ",
                        onClick = { /* Handle FAQ */ }
                    )

                    SettingItem(
                        icon = R.drawable.ic_help,
                        title = "Bantuan",
                        onClick = { /* Handle help */ }
                    )

                    SettingItem(
                        icon = R.drawable.ic_security,
                        title = "Keamanan",
                        onClick = { /* Handle security */ }
                    )

                    SettingItem(
                        icon = R.drawable.ic_delete_acc,
                        title = "Hapus akun",
                        onClick = { showDialogDelete = true }
                    )

                    SettingItem(
                        icon = R.drawable.ic_logout,
                        title = "Keluar",
                        titleColor = Color(0xFFC80000),
                        iconTint = Color(0xFFC80000),
                        onClick = { showDialogLogout = true }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
