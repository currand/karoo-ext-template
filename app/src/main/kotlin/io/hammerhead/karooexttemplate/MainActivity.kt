package io.hammerhead.karooexttemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.hammerhead.karooext.KarooSystemService
import io.hammerhead.karooexttemplate.screens.MainScreen
import io.hammerhead.karooexttemplate.theme.AppTheme

class MainActivity : ComponentActivity() {
//    private val karooSystem = KarooSystemService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }
}
