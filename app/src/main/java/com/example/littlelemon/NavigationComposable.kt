package com.example.littlelemon

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComposable(sharedPreferences: SharedPreferences) {
    val navController = rememberNavController()
    var startDes = rememberSaveable { mutableStateOf("") }
    var showWelcomeDialog = rememberSaveable { mutableStateOf(false) }

    startDes.value = checkIfAlreadyRegistered(showWelcomeDialog , sharedPreferences)

    Log.d("--Destination --", startDes.value)

    NavHost(navController = navController, startDestination = startDes.value) {
        composable("Home") {
            Home(navController = navController)
        }

        composable("Onboarding") {
            Onboarding(navController = navController, sharedPreferences)
        }

        composable("Profile") {
            Profile(navController = navController, sharedPreferences)
        }
    }
}

@Composable
fun checkIfAlreadyRegistered(showWelcomeDialog: MutableState<Boolean>, sharedPreferences: SharedPreferences): String {

    var welcomeMessage by rememberSaveable { mutableStateOf("") }

    // Check SharedPreferences for saved data
    /*LaunchedEffect(Unit) {
        val firstName = sharedPreferences.getString("firstName", "")
        val lastName = sharedPreferences.getString("lastName", "")
        val email = sharedPreferences.getString("email", "")
        if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() && !email.isNullOrEmpty()) {
            welcomeMessage = "Welcome Back $firstName $lastName"
            showWelcomeDialog.value = true
        }
    }*/

    val firstName = sharedPreferences.getString("firstName", "")
    val lastName = sharedPreferences.getString("lastName", "")
    val email = sharedPreferences.getString("email", "")
    if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() && !email.isNullOrEmpty()) {
        welcomeMessage = "Welcome Back $firstName $lastName"
        showWelcomeDialog.value = true
    }

    if (showWelcomeDialog.value) {
        showToast(welcomeMessage)
        return "Home";
    } else {
        return "Onboarding";
    }
}

