package com.littlelemon.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.littlelemon.littlelemon.ui.theme.LittleLemonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoarding(navController: NavHostController) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("little_lemon", Context.MODE_PRIVATE)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little lemon Logo",
            modifier = Modifier.size(200.dp, 100.dp)
        )
        Text(
            text = "Let's get to know you",
            fontSize = 24.sp,
            color = LittleLemonColor.cloud,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(LittleLemonColor.green)
                .fillMaxWidth()
                .padding(24.dp),
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(text = "First name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "Last name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            Spacer(modifier = Modifier.height(200.dp))
            Button(
                onClick = {
                    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                        Toast.makeText(context, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        sharedPreferences.edit(commit = true) {
                            putString("FIRST_NAME", firstName)
                            putString("LAST_NAME", lastName)
                            putString("EMAIL", email)
                        }
                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_LONG)
                            .show()
                        navController.navigate(Home.route)
                    }
                },
                colors = ButtonDefaults.buttonColors( LittleLemonColor.yellow ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, LittleLemonColor.red)
            ) {
                Text(
                    text = "Register",
                    fontSize = 18.sp,
                    color = LittleLemonColor.charcoal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    val navController = rememberNavController()
    OnBoarding(navController = navController)
}