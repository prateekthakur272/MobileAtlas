package dev.prateekthakur.mobileatlas.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.prateekthakur.mobileatlas.ui.screens.countries.CountriesScreen
import dev.prateekthakur.mobileatlas.ui.screens.country.CountryScreen

@SuppressLint("StaticFieldLeak")
lateinit var navController: NavController

@Composable
fun AppNavHost() {
    val controller = rememberNavController()
    navController = controller
    NavHost(navController = controller, startDestination = "/") {
        composable("/"){
            CountriesScreen()
        }
        composable("country/{iso2}"){ state ->
            val iso2 = state.arguments?.getString("iso2")!!
            CountryScreen(iso2)
        }
    }
}