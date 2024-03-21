package org.d3if0142.mobpro1.navigation

sealed class Screen (val route: String){
    data object Home: Screen("mainscreen")
}