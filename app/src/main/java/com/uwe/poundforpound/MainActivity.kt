package com.uwe.poundforpound

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.uwe.poundforpound.ui.theme.PoundForPoundTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PoundForPoundTheme {
                    val navController = rememberNavController()
                    NavGraph(navController)
            }
        }
    }
}

