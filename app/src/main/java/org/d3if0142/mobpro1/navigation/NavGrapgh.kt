package org.d3if0142.mobpro1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if0142.mobpro1.ui.screen.DetailScreen
import org.d3if0142.mobpro1.ui.screen.KEY_ID_CATATAN
import org.d3if0142.mobpro1.ui.screen.MainScreen

@Composable
fun SetupNavGrapgh(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            MainScreen(navController)
        }
        composable(route = Screen.FormBaru.route){
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_CATATAN){type = NavType.LongType}
            )
        ) {navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_CATATAN)
            DetailScreen(navController, id)
        }
    }
}