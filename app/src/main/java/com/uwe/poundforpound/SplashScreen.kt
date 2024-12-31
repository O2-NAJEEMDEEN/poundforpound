package com.uwe.poundforpound

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("P4P", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium, color = Color(0xFF00C853))

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(
                onClick = { navController.navigate("register") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Register", color = Color.Black)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { navController.navigate("login") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF00C853)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Login", color = Color(0xFF00C853))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle Google Login */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue with Google", color = Color(0xFF121212))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Handle Apple Login */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue with Apple", color = Color(0xFF121212))
        }
    }
}