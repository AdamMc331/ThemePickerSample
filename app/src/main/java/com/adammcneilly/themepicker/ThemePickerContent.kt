package com.adammcneilly.themepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThemePickerContent(
    onThemeTypeSelected: (ThemeType) -> Unit,
    selectedThemeType: ThemeType,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ButtonRow(
            text = "Light",
            selected = selectedThemeType == ThemeType.LIGHT,
            onClick = {
                onThemeTypeSelected(ThemeType.LIGHT)
            },
        )

        ButtonRow(
            text = "Dark",
            selected = selectedThemeType == ThemeType.DARK,
            onClick = {
                onThemeTypeSelected(ThemeType.DARK)
            },
        )

        ButtonRow(
            text = "System Default",
            selected = selectedThemeType == ThemeType.SYSTEM_DEFAULT,
            onClick = {
                onThemeTypeSelected(ThemeType.SYSTEM_DEFAULT)
            },
        )
    }
}

@Composable
private fun ButtonRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val textToUse = if (selected) {
        "$text (current)"
    } else {
        text
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = textToUse,
        )
    }
}
