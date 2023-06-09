package com.littlelemon.littlelemon

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.littlelemon.littlelemon.ui.theme.LittleLemonColor

@RequiresApi(Build.VERSION_CODES.M)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavHostController) {

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("little_lemon", Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString("FIRST_NAME", "") ?: ""
    val lastName = sharedPreferences.getString("LAST_NAME", "") ?: ""
    val email = sharedPreferences.getString("EMAIL", "") ?: ""

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.size(50.dp),
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little lemon Logo",
                modifier = Modifier.size(200.dp, 100.dp)
            )
            Box(modifier = Modifier.size(50.dp)) { }
        }

        Text(
            text = "Profile information",
            fontSize = 18.sp,
            color = LittleLemonColor.charcoal,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "First name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Last name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    sharedPreferences.edit(commit = true) {
                        putString("FIRST_NAME", "")
                        putString("LAST_NAME", "")
                        putString("EMAIL", "")
                    }
                    navController.navigate(OnBoarding.route)
                },
                colors = ButtonDefaults.buttonColors( LittleLemonColor.yellow ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, LittleLemonColor.red),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                Text(
                    text = "Log out",
                    fontSize = 18.sp,
                    color = LittleLemonColor.charcoal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.M)
@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    Profile(navController = navController)
}