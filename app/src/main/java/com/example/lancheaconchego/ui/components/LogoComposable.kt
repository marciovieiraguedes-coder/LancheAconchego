package com.example.lancheaconchego.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lancheaconchego.ui.theme.AmberGoldLight
import com.example.lancheaconchego.ui.theme.EspressoDark
import com.example.lancheaconchego.ui.theme.MutedBrown
import com.example.lancheaconchego.ui.theme.TerracottaDark
import com.example.lancheaconchego.ui.theme.TerracottaLight
import com.example.lancheaconchego.ui.theme.TerracottaPrimary

@Composable
fun LogoComposable(
    modifier: Modifier = Modifier,
    iconSize: Dp = 42.dp,
    showSlogan: Boolean = true,
    isDarkTheme: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Mascot Icon Badge with Warm Gradient
        Box(
            modifier = Modifier
                .size(iconSize)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(TerracottaLight, TerracottaPrimary, TerracottaDark)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🥟",
                fontSize = (iconSize.value * 0.55).sp
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Lanche ",
                    fontWeight = FontWeight.Black,
                    fontSize = if (iconSize > 40.dp) 18.sp else 16.sp,
                    color = if (isDarkTheme) AmberGoldLight else EspressoDark
                )
                Text(
                    text = "Aconchego",
                    fontWeight = FontWeight.Black,
                    fontSize = if (iconSize > 40.dp) 18.sp else 16.sp,
                    color = TerracottaPrimary
                )
            }
            if (showSlogan) {
                Text(
                    text = "O Sabor do Aconchego",
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = if (isDarkTheme) Color(0xFFFDE68A).copy(alpha = 0.8f) else MutedBrown
                )
            }
        }
    }
}
