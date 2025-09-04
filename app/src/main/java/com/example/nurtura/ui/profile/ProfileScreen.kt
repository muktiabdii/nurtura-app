@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.nurtura.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nurtura.R
import com.example.nurtura.cache.UserData
import com.example.nurtura.ui.theme.Accent
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.Grey
import com.example.nurtura.ui.theme.Light
import com.example.nurtura.ui.theme.Primary
import com.example.nurtura.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    val user = UserData

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        item {
            // Header Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                // Background header image
                Image(
                    painter = painterResource(id = R.drawable.header_profile),
                    contentDescription = "Header background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                // Profile content overlay
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Top bar with icons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Profile picture
                        Image(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .size(45.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop,
                            colorFilter = ColorFilter.tint(White)
                        )

                        Spacer(modifier = Modifier.width(18.dp))

                        // Name and location
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

                        // Action buttons
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
                                    modifier = Modifier.size(18.dp) // sesuaikan ukuran ikon
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(Color(0xFFF8F8F8), RoundedCornerShape(5.dp))
                                    .clickable { /* Handle edit */ },
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

                    // Pregnancy info card
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

            // Settings section
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

                    // Notification setting
                    SettingItem(
                        icon = R.drawable.ic_notif_stroke,
                        title = "Notifikasi",
                        hasSwitch = true,
                        onSwitchChange = { /* Handle switch */ }
                    )

                    // Other menu items
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
                        onClick = { /* Handle delete account */ }
                    )

                    SettingItem(
                        icon = R.drawable.ic_logout,
                        title = "Keluar",
                        titleColor = Color.Red,
                        iconTint = Color.Red,
                        onClick = { /* Handle logout */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SettingItem(
    icon: Int,
    title: String,
    titleColor: Color = Color.Black,
    iconTint: Color = Color.Gray,
    hasSwitch: Boolean = false,
    initialSwitchState: Boolean = false,
    onSwitchChange: (Boolean) -> Unit = {},
    onClick: () -> Unit = {}
) {
    var switchChecked by remember { mutableStateOf(initialSwitchState) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = iconTint,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.raleway_medium)),
            color = titleColor,
            modifier = Modifier.weight(1f)
        )

        if (hasSwitch) {
            Switch(
                checked = switchChecked,
                onCheckedChange = {
                    switchChecked = it
                    onSwitchChange(it)
                },
                modifier = Modifier.scale(0.7f),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = White,
                    checkedTrackColor = Primary.copy(alpha = 0.5f),
                    uncheckedThumbColor = White,
                    uncheckedTrackColor = Color(0xFFE0E0E0)
                )
            )


        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "Navigate",
                tint = Black,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
