package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

                var showWelcomeDialog by rememberSaveable { mutableStateOf(false) }
                var welcomeMessage by rememberSaveable { mutableStateOf("") }

                // Check SharedPreferences for saved data
                LaunchedEffect(Unit) {
                    val firstName = sharedPreferences.getString("firstName", "")
                    val lastName = sharedPreferences.getString("lastName", "")
                    val email = sharedPreferences.getString("email", "")
                    if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() && !email.isNullOrEmpty()) {
                        welcomeMessage = "Welcome Back $firstName $lastName $email"
                        showWelcomeDialog = true
                    }
                }

                if (showWelcomeDialog) {
                    AlertDialog(
                        onDismissRequest = { showWelcomeDialog = false },
                        title = { Text("Welcome") },
                        text = { Text(welcomeMessage) },
                        confirmButton = {
                            Button(
                                onClick = { showWelcomeDialog = false },
                                colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow)
                            ) {
                                Text("OK")
                            }
                        }
                    )

                    NavHost(navController = navController, startDestination = Home.route) {
                        composable(Home.route) {
                            Home(navController)
                        }
                    }

                } else {

                    NavHost(navController = navController, startDestination = Home.route) {
                        composable(Home.route) {
                            Onboarding(navController, sharedPreferences)
                        }
                    }
                }

            }
        }
    }
}
