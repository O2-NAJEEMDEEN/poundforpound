package com.uwe.poundforpound

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.uwe.poundforpound.ui.theme.fontFamily
import com.uwe.poundforpound.viewmodel.UserViewModel
import com.uwe.poundforpound.data.User
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun HomePage(
    userId: Int,
    navController: NavHostController,
    userViewModel: UserViewModel = viewModel()
) {
    // State to store user data
    val userState = remember { mutableStateOf<User?>(null) }

    // Fetch user data from the database
    LaunchedEffect(userId) {
        userViewModel.getUserById(userId) { user ->
            userState.value = user
        }
    }

    // Display the content only if user data is available
    val user = userState.value
    if (user != null) {
        // Calculate BMI
        val bmi = if (user.height!! > 0) user.weight?.div(((user.height / 100) * (user.height / 100))) else 0f

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(top = 60.dp, start = 16.dp, end = 16.dp)
        ) {
            // Header Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Profile Image
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFFFFAB40), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = user.name.take(2).uppercase(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = fontFamily,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))

                    // Greeting
                    Column {
                        Text(
                            text = "Hello, ${user.name}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontFamily = fontFamily
                        )
                        Text(
                            text = "Let’s push towards your goal!",
                            fontSize = 12.sp,
                            color = Color(0xFFB0BEC5),
                            fontFamily = fontFamily
                        )
                    }
                }

                // Notification Icon
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFF1E1E1E))
                )
            }

            // Calorie Tracker
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val currentDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(
                        Calendar.getInstance().time)
                    Text(
                        text = "Today, $currentDate",
                        color = Color(0xFFF1F4F8),
                        fontSize = 16.sp,
                        fontFamily = fontFamily,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_fire),
                        contentDescription = "Calories",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.height(13.dp))
                    val text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFFF1F4F8), fontWeight = FontWeight.Bold, fontSize = 26.sp, fontFamily = fontFamily)) {
                            append("1420")
                        }
                        withStyle(style = SpanStyle(color = Color(0xFF98AAB3), fontSize = 18.sp, fontFamily = fontFamily)) { // Change color of "/ 1680"
                            append(" / 1680")
                        }
                    }

                    Text(text = text)
                    Text(
                        text = "Calories",
                        color = Color(0xFFB0BEC5),
                        fontSize = 16.sp,
                        fontFamily = fontFamily
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(15.dp)
                            .background(Color(0xFF121212))
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(1420f / 1680f)
                                .height(15.dp)
                                .background(Color(0xFFFF5252))
                                .clip(RoundedCornerShape(8.dp))

                        )
                    }
                }
            }

            // Food Intake Prompt
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(65.dp)
                            .background(Color(0xFF9A26AD), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_food),
                            contentDescription = "Food Intake",
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Log your food intake",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF1F4F8),
                            fontFamily = fontFamily
                        )
                        Text(
                            text = "Keep track of your daily calories and nutrition.",
                            fontSize = 12.sp,
                            color = Color(0xFFB0BEC5),
                            fontFamily = fontFamily
                        )
                    }
                }
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Forward",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

            // Steps and Distance Stats
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                StatsCard(
                    title = "Steps",
                    todayValue = "11,657",
                    weekValue = "56,321"
                )
                StatsCard(
                    title = "Distance",
                    todayValue = "4.7KM",
                    weekValue = "18.4KM"
                )
            }

            // Weight, Height, and BMI Stats
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 13.dp, end = 13.dp, bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text("Stats", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = fontFamily)
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Forward",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Column(
                    modifier = Modifier.padding(vertical = 14.dp, horizontal = 16.dp).background(Color(0xFF121212))
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text("Weight", color = Color(0xFFB0BEC5), fontFamily = fontFamily)
                        Text("${user.weight}kg", color = Color(0xFFF1F4F8), fontFamily = fontFamily)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Height", color = Color(0xFFB0BEC5), fontFamily = fontFamily)
                        Text("${user.height}cm", color = Color(0xFFF1F4F8), fontFamily = fontFamily)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("BMI", color = Color(0xFFB0BEC5), fontFamily = fontFamily)
                        Text("%.1f".format(bmi), color = Color(0xFFF1F4F8), fontFamily = fontFamily)
                    }
                }
            }
        }
    } else {
        // Loading State
        Text(
            text = "Loading...",
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun StatsCard(title: String, todayValue: String, weekValue: String) {

        Card(
            modifier = Modifier
                .padding(horizontal = 4.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(title, color = Color(0xFFF1F4F8), fontSize = 16.sp, fontFamily = fontFamily)
                Row {
                    Text(todayValue, color = Color.Green, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(weekValue, color = Color.White, fontSize = 12.sp)
                }

            }
        }

}
