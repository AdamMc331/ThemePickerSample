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
import com.adammcneilly.themepicker.ThemeType

@SuppressLint("NewApi")
@Composable
fun ThemePickerTheme(
    themeType: ThemeType,
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamic: Boolean = BuildCompat.isAtLeastS(),
    content: @Composable() () -> Unit
) {
    val systemTheme = when {
        darkTheme && dynamic -> dynamicDarkColorScheme(LocalContext.current)
        !darkTheme && dynamic -> dynamicLightColorScheme(LocalContext.current)
        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }

    val colorScheme = when (themeType) {
        ThemeType.LIGHT -> lightColorScheme()
        ThemeType.DARK -> darkColorScheme()
        ThemeType.SYSTEM_DEFAULT -> systemTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
