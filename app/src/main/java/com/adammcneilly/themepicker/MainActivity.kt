package com.adammcneilly.themepicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.themepicker.ui.theme.ThemePickerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemePickerTheme {
                Surface {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        ThemePickerContent(
                            modifier = Modifier
                                .padding(32.dp)
                                .align(Alignment.Center),
                        )
                    }
                }
            }
        }
    }
}
