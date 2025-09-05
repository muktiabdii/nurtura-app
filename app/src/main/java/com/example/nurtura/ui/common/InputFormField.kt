package com.example.nurtura.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.Grey
import com.example.nurtura.ui.theme.Light

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFormField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: Int? = null,
    isPassword: Boolean = false
) {
    val visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.raleway_medium))
                )
            )
        },
        leadingIcon = if (leadingIcon != null) {
            {
                Image(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null
                )
            }
        } else null,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(14.dp),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        textStyle = TextStyle(
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.raleway_medium))
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Light,
            unfocusedContainerColor = Light,
            focusedIndicatorColor = Grey,
            unfocusedIndicatorColor = Grey,
            focusedTextColor = Black,
            unfocusedTextColor = Black,
            unfocusedPlaceholderColor = Grey,
            focusedPlaceholderColor = Grey
        )
    )
}
