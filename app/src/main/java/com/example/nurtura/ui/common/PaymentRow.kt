package com.example.nurtura.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Black

@Composable
fun PaymentRow(
    label: String,
    amount: String,
    isTotal: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontFamily = if (isTotal) FontFamily(Font(R.font.raleway_bold)) else FontFamily(Font(R.font.raleway_medium)),
            color = Black
        )
        Text(
            text = amount,
            fontSize = 14.sp,
            fontFamily = if (isTotal) FontFamily(Font(R.font.raleway_bold)) else FontFamily(Font(R.font.raleway_medium)),
            color = Black
        )
    }
}