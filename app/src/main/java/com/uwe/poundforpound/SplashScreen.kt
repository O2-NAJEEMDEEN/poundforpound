package com.uwe.poundforpound

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.uwe.poundforpound.ui.theme.DarkGray
import com.uwe.poundforpound.ui.theme.fontFamily

@Composable
fun SplashScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(Color(0xFF121212))
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Centered logo and text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.poundforpound_logo),
                contentDescription = "Pound for Pound Logo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.size(150.dp, 40.dp).padding(bottom = 5.dp),
            )

            Text(
                text = "Pound for Pound",
                fontFamily = fontFamily,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFFE0E0E0),
                modifier = Modifier.padding(top = 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(
                onClick = { navController.navigate("register") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853)),
                modifier = Modifier.weight(1f)
            ) {
                Text("Register",
                    color = DarkGray,
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { navController.navigate("login") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(2.dp, Color(0xFF00C853)),
                modifier = Modifier.weight(1f)
            ) {

                Text("Login",
                    color = Color(0xFF00C853),
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle Google Login */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF262626)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = "Google Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(25.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text("Continue with Google",
                    color = Color(0xFFE0E0E0),
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { /* Handle Apple Login */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF262626)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.apple_logo),
                    contentDescription = "Apple Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(25.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Text("Continue with Apple",
                    color = Color(0xFFE0E0E0),
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

        }
    }
}