package com.adammcneilly.themepicker

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.adammcneilly.themepicker.ui.theme.ThemePickerTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeTypeKey = stringPreferencesKey("theme_type")

            val themeType = remember {
                mutableStateOf(ThemeType.SYSTEM_DEFAULT)
            }

            val coroutineScope = rememberCoroutineScope()

            SideEffect {
                coroutineScope.launch {
                    dataStore.data.map { preferences ->
                        preferences[themeTypeKey] ?: ThemeType.SYSTEM_DEFAULT.name
                    }.collect { theme ->
                        themeType.value = ThemeType.valueOf(theme)
                    }
                }
            }

            ThemePickerTheme(
                themeType.value,
            ) {
                Surface {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        ThemePickerContent(
                            modifier = Modifier
                                .padding(32.dp)
                                .align(Alignment.Center),
                            onThemeTypeSelected = { themeType ->
                                coroutineScope.launch {
                                    dataStore.edit { settings ->
                                        settings[themeTypeKey] = themeType.name
                                    }
                                }
                            },
                            selectedThemeType = themeType.value,
                        )
                    }
                }
            }
        }
    }
}
