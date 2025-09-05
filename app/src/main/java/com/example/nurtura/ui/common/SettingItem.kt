package com.example.nurtura.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.Primary
import com.example.nurtura.ui.theme.White


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
            .padding(vertical = 12.dp)
            .clickable { onClick() },
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
