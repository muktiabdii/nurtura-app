@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.nurtura.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nurtura.R
import com.example.nurtura.ui.theme.Accent
import com.example.nurtura.ui.theme.Black
import com.example.nurtura.ui.theme.Light
import com.example.nurtura.ui.theme.LightActive

// editable search bar
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    placeholder: String = "Temukan rekomendasi makanan hari ini",
    onSearchClick: () -> Unit = {},
    onValueChange: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue ->
            searchText = newValue
            onValueChange(newValue)
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Black,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.raleway_medium))
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = Accent,
                modifier = Modifier.size(24.dp)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = false
            )
            .background(
                color = Light,
                shape = RoundedCornerShape(20.dp)
            ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = LightActive,
            unfocusedContainerColor = Light,
            focusedTextColor = Black,
            unfocusedTextColor = Black
        ),
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.raleway_medium)),
            color = Black
        )
    )
}

// non editable search bar
@Composable
fun ClickableSearchBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        placeholder = {
            Text(
                text = "Temukan rekomendasi makanan hari ini",
                color = Black,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.raleway_medium))
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = Accent,
                modifier = Modifier.size(24.dp)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = false
            )
            .background(
                color = Light,
                shape = RoundedCornerShape(20.dp)
            ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Light,
            unfocusedContainerColor = Light,
            disabledBorderColor = Color.Transparent,
            disabledContainerColor = Light,
            disabledTextColor = Black
        ),
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
        readOnly = true,
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect { interaction ->
                        when (interaction) {
                            is PressInteraction.Press -> {
                                onClick()
                            }
                        }
                    }
                }
            }
    )
}
