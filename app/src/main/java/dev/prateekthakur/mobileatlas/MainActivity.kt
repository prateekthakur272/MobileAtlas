package dev.prateekthakur.mobileatlas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import dev.prateekthakur.mobileatlas.ui.navigation.AppNavHost
import dev.prateekthakur.mobileatlas.ui.screens.countries.CountriesScreen
import dev.prateekthakur.mobileatlas.ui.theme.MobileAtlasTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileAtlasTheme {
                AppNavHost()
            }
        }
    }
}
