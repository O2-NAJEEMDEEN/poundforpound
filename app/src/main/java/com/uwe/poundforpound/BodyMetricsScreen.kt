package com.uwe.poundforpound

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.uwe.poundforpound.ui.theme.fontFamily
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun BodyMetricsScreen(
    userId: Int,
    navController: NavHostController
){

    val context = LocalContext.current

    // State for each field
    val selectedDate = remember { mutableStateOf("Select") }
    val selectedGender = remember { mutableStateOf("Select") }
    val selectedWeight = remember { mutableStateOf("Select") }
    val selectedHeight = remember { mutableStateOf("Select") }

    // State to manage dialogs
    val showDatePicker = remember { mutableStateOf(false) }
    val showGenderPicker = remember { mutableStateOf(false) }
    val showWeightPicker = remember { mutableStateOf(false) }
    val showHeightPicker = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(Color(0xFF121212))
            .fillMaxSize()
            .padding(top = 60.dp, start = 30.dp, end = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.poundforpound_logo),
            contentDescription = "Pound for Pound Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(100.dp, 40.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Let’s Personalize Your Journey",
            color = Color(0xFFE0E0E0),
            fontSize = 20.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold

        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "We’ll use this information to tailor your goals and recommendations to suit your body and lifestyle.",
            color = Color(0xFFB0BEC5),
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )


        Spacer(modifier = Modifier.height(35.dp))

        Column(
            modifier = Modifier
                .background(Color(0xFF1E1E1E))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Age Picker
            SelectionRow("Age", selectedDate.value) {
                showDatePicker.value = true
            }

            // Gender Picker
            SelectionRow("Gender", selectedGender.value) {
                showGenderPicker.value = true
            }

            // Weight Picker
            SelectionRow("Weight", selectedWeight.value) {
                showWeightPicker.value = true
            }

            // Height Picker
            SelectionRow("Height", selectedHeight.value) {
                showHeightPicker.value = true
            }
        }
    }
    // Dialogs
    if (showDatePicker.value) {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                val formattedDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(
                    Calendar.getInstance().apply { set(year, month, day) }.time
                )
                selectedDate.value = formattedDate
                showDatePicker.value = false
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showGenderPicker.value) {
        GenderPickerDialog(
            selectedGender = selectedGender,
            onDismiss = { showGenderPicker.value = false }
        )
    }

    if (showWeightPicker.value) {
        WeightPickerDialog(
            selectedWeight = selectedWeight,
            onDismiss = { showWeightPicker.value = false }
        )
    }

    if (showHeightPicker.value) {
        HeightPickerDialog(
            selectedHeight = selectedHeight,
            onDismiss = { showHeightPicker.value = false }
        )
    }
}

@Composable
fun SelectionRow(title: String, value: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color(0xFFB0BEC5),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold
        )

        Row {
            Text(
                text = value,
                fontSize = 16.sp,
                color = Color(0xFF2979FF),
                fontFamily = fontFamily
            )
        }

    }
}

@Composable
fun GenderPickerDialog(selectedGender: MutableState<String>, onDismiss: () -> Unit) {
    val genders = listOf("Male", "Female")
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Select Gender") },
        text = {
            Column {
                genders.forEach { gender ->
                    Text(
                        text = gender,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedGender.value = gender
                                onDismiss()
                            }
                            .padding(8.dp),
                        fontSize = 16.sp
                    )
                }
            }
        },
        confirmButton = {}
    )
}

@Composable
fun WeightPickerDialog(selectedWeight: MutableState<String>, onDismiss: () -> Unit) {
    val weights = (30..200).toList()
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Select Weight (Kg)") },
        text = {
            Column {
                weights.forEach { weight ->
                    Text(
                        text = "$weight Kg",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedWeight.value = "$weight Kg"
                                onDismiss()
                            }
                            .padding(8.dp),
                        fontSize = 16.sp
                    )
                }
            }
        },
        confirmButton = {}
    )
}

@Composable
fun HeightPickerDialog(selectedHeight: MutableState<String>, onDismiss: () -> Unit) {
    val heightsInCm = (100..250).toList()
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Select Height") },
        text = {
            Column {
                heightsInCm.forEach { height ->
                    Text(
                        text = "$height cm",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedHeight.value = "$height cm"
                                onDismiss()
                            }
                            .padding(8.dp),
                        fontSize = 16.sp
                    )
                }
            }
        },
        confirmButton = {}
    )
}



