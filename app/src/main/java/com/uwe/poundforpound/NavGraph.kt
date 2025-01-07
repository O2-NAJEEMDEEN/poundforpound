package com.uwe.poundforpound

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("login") {
            LoginScreen(
                navController = navController,
                onLoginSuccess = { userId ->
                    // Navigate to BodyMetricsScreen with the logged-in user's ID
                    navController.navigate("bodyMetrics/$userId")
                },
            )
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable(
            "bodyMetrics/{userId}",
            arguments = listOf(navArgument("userId") {  type = NavType.IntType; defaultValue = -1})
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId")!!
            BodyMetricsScreen(
                userId = userId,
                navController = navController
            )
        }
    }
}
