package com.example.littlelemon

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


val allMenu: MutableState<List<MenuItems>> = mutableStateOf(globalMenu)



@Composable
fun Home(navController: NavHostController) {

    var filteredMenu by remember { mutableStateOf(emptyList<MenuItems>()) }
    var selectedCategory by remember { mutableStateOf<String>("all") }

    var searchStr by remember { mutableStateOf<String>("") }

    Log.d("-- selectedCategory --", selectedCategory.toString())

    // Update filteredMenu when globalMenu or selectedCategory changes
    DisposableEffect(selectedCategory) {
        if (selectedCategory.equals("all")) {
            filteredMenu = globalMenu
        } else {
            filteredMenu = allMenu.value.filter { it.category == selectedCategory }
        }
        onDispose {}
    }

    //For search
    DisposableEffect(searchStr) {
        if (searchStr.isNotEmpty()) {
            Log.d("--- searchPhrase ----> ", searchStr)
            filteredMenu = globalMenu.filter { it.title.contains(searchStr, ignoreCase = true) }
        } else {
            filteredMenu = globalMenu
        }
        onDispose { }
    }

    Column (modifier = Modifier.fillMaxSize() ) {
        HomeTop(navController);
        UpperDeck{search -> searchStr = search }
        MenuBreakdown{selectedType -> selectedCategory = selectedType }
        //Menu(globalMenu)

        Menu(filteredMenu)

        //for (item in globalMenu) {
        //    Text(text=item.title)
        //    Log.d("-- data --", item.title)
        //}
    }


} // Home


// Function to handle button click and log which button was clicked
fun buttonClickHandler(buttonText: String) {
    Log.d("ButtonClick--", "$buttonText clicked")

    var type = when (buttonText) {
        "Starters" -> FilterType.Starters
        "Mains" -> FilterType.Mains
        "Desserts" -> FilterType.Dessert
        "Drinks" -> FilterType.Drinks
        else ->  FilterType.All
    }

}