package com.uwe.poundforpound

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.uwe.poundforpound.ui.theme.fontFamily
import com.uwe.poundforpound.data.User
import com.uwe.poundforpound.viewmodel.UserViewModel

@Composable
fun RegisterScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = viewModel()
) {

    //Variables

    val emailValue = remember {
        mutableStateOf("")
    }

    val passwordValue = remember {
        mutableStateOf("")
    }

    val confirmPasswordValue = remember {
        mutableStateOf("")
    }


    val passwordError = remember {
        mutableStateOf(false)
    }

    val emailError = remember {
        mutableStateOf(false)
    }

    val nameValue = remember {
        mutableStateOf("")
    }

    val  isButtonEnabled = remember {
        derivedStateOf {
            nameValue.value.isNotEmpty() && emailValue.value.isNotEmpty() && passwordValue.value.isNotEmpty() && confirmPasswordValue.value.isNotEmpty()
        }
    }

    Column(
        modifier = Modifier
            .background(Color(0xFF121212))
            .fillMaxSize()
            .padding(top = 60.dp, start = 30.dp, end = 30.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.poundforpound_logo),
            contentDescription = "Pound for Pound Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(100.dp, 40.dp)
                .align(Alignment.CenterHorizontally),

        )
        Spacer(modifier = Modifier.height(150.dp))

        Text("Register",
            color = Color(0xFFE0E0E0),
            fontSize = 20.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold

        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Join the Movement, achieve your health goals today. Fill in the required details to get started.",
            color = Color(0xFFB0BEC5),
            fontSize = 12.sp,
            fontFamily = fontFamily,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )


        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nameValue.value,
            onValueChange = {
                nameValue.value = it
            },
            leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "Person", tint = Color(0xFFB0BEC5)) },
            placeholder = {
                Text("Full Name",
                    color = Color(0xFFB0BEC5),
                    fontSize = 14.sp,
                    fontFamily = fontFamily
                ) },
            modifier = Modifier.fillMaxWidth(),

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = if (emailError.value) Color(0xFF330000) else Color(0xFF1E1E1E),
                unfocusedContainerColor = if (emailError.value) Color(0xFF330000) else Color(0xFF1E1E1E),
                focusedBorderColor = if (emailError.value) Color.Red else Color.Transparent,
                unfocusedBorderColor =  if (emailError.value) Color.Red else Color.Transparent

            ),
            textStyle = TextStyle(fontSize = 14.sp, fontFamily = fontFamily),
            shape = RoundedCornerShape(50.dp),
            maxLines = 2
        )

        if (emailError.value) {
            Text(
                text = "Please input a name",
                color = Color.Red,
                fontFamily = fontFamily,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = emailValue.value,
            onValueChange = {
                emailValue.value = it
                emailError.value = !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
            },
            leadingIcon = { Icon(Icons.Outlined.Email, contentDescription = "Email", tint = Color(0xFFB0BEC5)) },
            placeholder = {
                Text("Email Address",
                color = Color(0xFFB0BEC5),
                fontSize = 14.sp,
                fontFamily = fontFamily
                ) },
            modifier = Modifier.fillMaxWidth(),

            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = if (emailError.value) Color(0xFF330000) else Color(0xFF1E1E1E),
                unfocusedContainerColor = if (emailError.value) Color(0xFF330000) else Color(0xFF1E1E1E),
                focusedBorderColor = if (emailError.value) Color.Red else Color.Transparent,
                unfocusedBorderColor =  if (emailError.value) Color.Red else Color.Transparent

            ),
            textStyle = TextStyle(fontSize = 14.sp, fontFamily = fontFamily),
            shape = RoundedCornerShape(50.dp),
            maxLines = 2
        )

        if (emailError.value) {
            Text(
                text = "Please input a valid email address",
                color = Color.Red,
                fontFamily = fontFamily,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = passwordValue.value,
            onValueChange = {
                passwordValue.value = it
            },
            leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "Password", tint = Color(0xFFB0BEC5)) },
            placeholder = {
                Text("Password",
                    color = Color(0xFFB0BEC5),
                    fontSize = 14.sp,
                    fontFamily = fontFamily
                ) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF1E1E1E),
                unfocusedContainerColor = Color(0xFF1E1E1E),
                disabledContainerColor = Color(0xFF1E1E1E),
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            textStyle = TextStyle(fontSize = 14.sp, fontFamily = fontFamily),
            shape = RoundedCornerShape(50.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = confirmPasswordValue.value,
            onValueChange = {
                confirmPasswordValue.value = it
                passwordError.value = it != passwordValue.value
            },
            leadingIcon = { Icon(Icons.Outlined.Lock, contentDescription = "Confirm Password", tint = Color(0xFFB0BEC5)) },
            placeholder = {
                Text("Confirm Password",
                    color = Color(0xFFB0BEC5),
                    fontSize = 14.sp,
                    fontFamily = fontFamily
                ) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = if (passwordError.value) Color(0xFF330000) else Color(0xFF1E1E1E),
                unfocusedContainerColor = if (passwordError.value) Color(0xFF330000) else Color(0xFF1E1E1E),
                disabledContainerColor = Color(0xFF1E1E1E),
                focusedBorderColor = if (passwordError.value) Color.Red else Color.Transparent,
                unfocusedBorderColor =  if (passwordError.value) Color.Red else Color.Transparent

            ),
            textStyle = TextStyle(fontSize = 14.sp, fontFamily = fontFamily),
            shape = RoundedCornerShape(50.dp),
            singleLine = true
        )

        if (passwordError.value) {
            Text(
                text = "Passwords doesn't match with the first",
                color = Color.Red,
                fontFamily = fontFamily,
                fontSize = 10.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                /* Handle Register */
                val newUser = User( name = nameValue.value, email = emailValue.value, password = passwordValue.value)
                userViewModel.registerUser(newUser) { success ->
                    if (success) {
                        navController.navigate("login")
                    }
                }

            },
            enabled = isButtonEnabled.value,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00C853),
                disabledContainerColor = Color(0xFF00c853).copy(alpha = 0.4f)
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Register",
                color = Color(0xFF121212),
                fontSize = 16.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(vertical = 5.dp)
            )
        }

        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = Color(0xFFB0BEC5),
                fontSize = 12.sp,
                fontFamily = fontFamily
            )) {
                append("Don't have an account? ")
            }
            // Add the "Log in" part with a string annotation for click handling
            pushStringAnnotation(tag = "LOGIN", annotation = "login")
            withStyle(style = SpanStyle(
                color = Color(0xFF00C853),
                fontSize = 12.sp,
                fontFamily = fontFamily
            )) {
                append("Log in")
            }
            pop()
        }

        // Text composable to display the clickable text
        Text(
            text = annotatedText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .clickable {
                    // Handle navigation explicitly for "Log in" annotation
                    annotatedText.getStringAnnotations(tag = "LOGIN", start = 0, end = annotatedText.length)
                        .firstOrNull()?.let {
                            navController.navigate("login") // Navigate to the login page
                        }
                }
        )

    }
}