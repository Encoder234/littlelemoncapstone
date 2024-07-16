package com.example.littlelemon

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable

fun Home(navController: NavHostController) {

    Column (modifier = Modifier.fillMaxSize() ) {
        HomeTop(navController);
        UpperDeck();
        MenuBreakdown();

    }

} // Home



// Function to handle button click and log which button was clicked
fun buttonClickHandler(buttonText: String) {
    Log.d("ButtonClick--", "$buttonText clicked")
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    //Home();
}