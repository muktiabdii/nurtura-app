package com.example.nurtura.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Grey
import com.example.nurtura.ui.theme.Normal
import com.example.nurtura.ui.theme.White

@Composable
fun BottomNavBar(
    navController: NavController,
    selected: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = White)
            .drawWithContent {
                drawContent()
                drawIntoCanvas { canvas ->
                    val paint = Paint()
                    val frameworkPaint = paint.asFrameworkPaint()

                    val shadowColor = Color.Black.copy(alpha = 0.04f)
                    frameworkPaint.color = shadowColor.toArgb()
                    frameworkPaint.setShadowLayer(
                        3.dp.toPx(),
                        0f,
                        1.5.dp.toPx(),
                        shadowColor.toArgb()
                    )

                    canvas.drawRect(
                        left = 0f,
                        top = 0f,
                        right = size.width,
                        bottom = 6.dp.toPx(),
                        paint = paint
                    )
                }
            }
            .padding(top = 13.dp, bottom = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // nav item
            NavItem(
                icon = R.drawable.ic_home,
                label = "home",
                isSelected = selected == "home",
                onClick = {
                    if (selected != "home") {
                        navController.navigate("home") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            NavItem(
                icon = R.drawable.ic_mydoc,
                label = "mydoc",
                isSelected = selected == "mydoc",
                onClick = {
                    if (selected != "mydoc") {
                        navController.navigate("mydoc") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
            NavItem(
                icon = R.drawable.ic_profile,
                label = "profile",
                isSelected = selected == "profile",
                onClick = {
                    if (selected != "profile") {
                        navController.navigate("profile") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun NavItem(
    icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeColor = Normal
    val inactiveColor = Grey
    val tintColor = if (isSelected) activeColor else inactiveColor

    Column(
        modifier = modifier
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(32.dp),
            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(tintColor)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            lineHeight = 28.sp,
            fontFamily = FontFamily(Font(R.font.raleway_bold)),
            color = tintColor,
            textAlign = TextAlign.Center
        )
    }
}