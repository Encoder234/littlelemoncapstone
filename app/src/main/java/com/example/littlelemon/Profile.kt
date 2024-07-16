package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun Profile(navController: NavHostController, sharedPreferences: SharedPreferences) {


    Column (modifier = Modifier.fillMaxSize() ) {

        //var firstNameState = rememberSaveable { mutableStateOf("") }
        //var lastNameState = rememberSaveable { mutableStateOf("") }
        //var emailState = rememberSaveable { mutableStateOf("") }

        val firstName = sharedPreferences.getString("firstName", "")
        val lastName = sharedPreferences.getString("lastName", "")
        val email = sharedPreferences.getString("email", "")

        var showErrorDialog by rememberSaveable { mutableStateOf(false) }
        var errorMessage by rememberSaveable { mutableStateOf("") }
        var showWelcomeMessage by rememberSaveable { mutableStateOf(false) }
        var welcomeMessage by rememberSaveable { mutableStateOf("") }


        //checkIfAlreadyRegistered(navController,sharedPreferences);

        Header();
        //CustomText();
        Info();
        ShowInputFields(firstName.toString(), lastName.toString(), email.toString() );
        Spacer(modifier = Modifier.weight(1f)) // Spacer to push RegisterButton to the bottom
        /*LogoutButton(
            firstNameState = firstName,
            lastNameState = lastNameState,
            emailState = emailState,
            showErrorDialog = showErrorDialog,
            errorMessage = errorMessage,
            onShowErrorDialogChange = { showErrorDialog = it },
            onErrorMessageChange = { errorMessage = it },
            sharedPreferences = sharedPreferences,
            onWelcomeMessageChange = { welcomeMessage = it; showWelcomeMessage = true }
        )*/

        // Add Clear Data Button for demonstration
        ClearsharedPref(navController,sharedPreferences)

    } // Column();
} // Onboarding()


@Composable
fun ShowInputFields(firstNameState: String,
                    lastNameState: String,
                    emailState: String) {

    val outlinedTextFieldShape = RoundedCornerShape(8.dp)

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()), // Make the content scrollable
    ) {

        Text (text = "First name", textAlign = TextAlign.Left)
        OutlinedTextField(
            value = firstNameState,
            onValueChange = {  },
            textStyle = TextStyle(
                fontFamily = karlaFontFamily,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 20.dp, start = 5.dp, end = 20.dp)
                .border(1.dp, LittleLemonColor.charcoal, outlinedTextFieldShape)
        )

        Text (text = "Last name", textAlign = TextAlign.Left)
        OutlinedTextField(
            value = lastNameState,
            onValueChange = {  },

            textStyle = TextStyle(
                fontFamily = karlaFontFamily,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 20.dp, start = 5.dp, end = 20.dp)
                .border(1.dp, LittleLemonColor.charcoal, outlinedTextFieldShape)
        )


        Text (text = "Email", textAlign = TextAlign.Left)
        OutlinedTextField(
            value = emailState,
            onValueChange = { },
            //label = { Text("Email") },
            textStyle = TextStyle(
                fontFamily = karlaFontFamily,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp, bottom = 20.dp, start = 5.dp, end = 20.dp)
                .border(1.dp, LittleLemonColor.charcoal, outlinedTextFieldShape)
        )

    }
}


@Composable
fun LogoutButton(
    firstNameState: MutableState<String>,
    lastNameState: MutableState<String>,
    emailState: MutableState<String>,
    showErrorDialog: Boolean,
    errorMessage: String,
    onShowErrorDialogChange: (Boolean) -> Unit,
    onErrorMessageChange: (String) -> Unit,
    sharedPreferences: SharedPreferences,
    onWelcomeMessageChange: (String) -> Unit
) {
    val karlaFontFamily = FontFamily(Font(R.font.karla_regular))

    var showWelcomeDialog by remember { mutableStateOf(false) }
    var welcomeMessage by remember { mutableStateOf("") }

    Button(
        onClick = {
            // Check if any of the fields are empty
            if (firstNameState.value.isBlank() || lastNameState.value.isBlank() || emailState.value.isBlank()) {
                onErrorMessageChange("Registration unsuccessful. Please enter all data.")
                onShowErrorDialogChange(true)
            } else if (!isValidEmail(emailState.value)) {
                // Check if the email is valid
                onErrorMessageChange("Invalid email format. Please enter a valid email address.")
                onShowErrorDialogChange(true)
            } else {

                // Save data to SharedPreferences
                with(sharedPreferences.edit()) {
                    putString("firstName", firstNameState.value)
                    putString("lastName", lastNameState.value)
                    putString("email", emailState.value)
                    apply()
                }

                onWelcomeMessageChange("Welcome Back ${firstNameState.value} ${lastNameState.value}")
                showWelcomeDialog = true;
                welcomeMessage = "Registration successful!"
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Yellow, RoundedCornerShape(8.dp)) // Rounded corners with 8dp radius
            .border(1.dp, Color(0xFFFF4500), RoundedCornerShape(8.dp)), // Red-orange border with rounded corners
        contentPadding = PaddingValues(vertical = 12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow)

    ) {
        Text(
            "Register",
            style = TextStyle(
                fontFamily = karlaFontFamily,
                fontSize = 18.sp
            )
        )
    }

    // Display alert dialog if registration was unsuccessful
    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { onShowErrorDialogChange(false) },
            title = {
                Text("Error")
            },
            text = {
                Text(errorMessage)
            },
            confirmButton = {
                Button(
                    onClick = { onShowErrorDialogChange(false) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow)
                ) {
                    Text("OK")
                }
            }
        )
    }

    if (showWelcomeDialog) {
        AlertDialog(
            onDismissRequest = { showWelcomeDialog = false },
            title = { Text("") },
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
    }

}

@Composable
fun ClearsharedPref(navController: NavHostController, sharedPreferences: SharedPreferences) {

    var goodbye by rememberSaveable { mutableStateOf(false) }

    Button(
        onClick = {

            displaySharedPreferences(sharedPreferences);

            // Clear all data in SharedPreferences
            with(sharedPreferences.edit()) {
                clear()
                apply()
            }

            goodbye = true
            navController.navigate("Onboarding");

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Yellow, RoundedCornerShape(8.dp)) // Rounded corners with 8dp radius
            .border(1.dp, Color(LittleLemonColor.redorange.value), RoundedCornerShape(8.dp)), // Red-orange border with rounded corners
        contentPadding = PaddingValues(vertical = 12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = LittleLemonColor.yellow)

    ) {
        Text("Log out", style = TextStyle(
            fontFamily = karlaFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp, color = LittleLemonColor.black

        ))
    }

    if (goodbye)
        showToast(message = "Bye!")
}

