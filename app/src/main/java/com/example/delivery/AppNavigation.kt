package com.example.delivery

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.delivery.ui.citydistricts.CityDistrictsView
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application()


@Composable
fun AppNavigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "delivery"){
        composable("delivery"){ CityDistrictsView() }

    }
}