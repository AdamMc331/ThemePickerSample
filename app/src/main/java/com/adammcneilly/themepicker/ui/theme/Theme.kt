package com.adammcneilly.themepicker.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.BuildCompat

@SuppressLint("NewApi")
@Composable
fun ThemePickerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamic: Boolean = BuildCompat.isAtLeastS(),
    content: @Composable() () -> Unit
) {
    val colors = when {
        darkTheme && dynamic -> dynamicDarkColorScheme(LocalContext.current)
        !darkTheme && dynamic -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}
