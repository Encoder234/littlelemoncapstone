package com.example.littlelemon

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.lifecycleScope

import androidx.room.Room

import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

val karlaFontFamily = FontFamily(Font(R.font.karla_regular))
val markaFontFamily = FontFamily(Font(R.font.markazitext_regular))



lateinit var globalMenu : List<MenuItems>

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }


    val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            lifecycleScope.launch(Dispatchers.IO) {
                if (database.menuItemDao().isEmpty()) {
                    // add code here
                    Log.d("--- TEST 0 ---", "RUNNING")
                    val listOfMenu = fetchMenu()

                    Log.d("---- listOfMenu --- " , listOfMenu.toString() )

                    saveMenuToDatabase(listOfMenu)

                    Log.d("- AFTER DB SAVE - " , listOfMenu.toString() )
                } else {
                     globalMenu = database.menuItemDao().myGetAll()

                    Log.d("---- DB Content --- " , globalMenu.toString() )
                }
            }


            LittleLemonTheme {
               // val navController = rememberNavController()
                val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

                NavigationComposable(sharedPreferences);

                {/*var showWelcomeDialog by rememberSaveable { mutableStateOf(false) }
                var welcomeMessage by rememberSaveable { mutableStateOf("") }

                // Check SharedPreferences for saved data
                LaunchedEffect(Unit) {
                    val firstName = sharedPreferences.getString("firstName", "")
                    val lastName = sharedPreferences.getString("lastName", "")
                    val email = sharedPreferences.getString("email", "")
                    if (!firstName.isNullOrEmpty() && !lastName.isNullOrEmpty() && !email.isNullOrEmpty()) {
                        welcomeMessage = "Welcome Back $firstName $lastName"
                        showWelcomeDialog = true
                    }
                }

                if (showWelcomeDialog) {
                    Toast.makeText(this, welcomeMessage, Toast.LENGTH_LONG).show()

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
                } */}

            } //LittleLemonTheme
        } //setContent
    }// onCreate



    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        //private suspend fun fetchMenu() {
        //TODO("Retrieve data")
        // data URL: https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json

        val menuItemsJsonString = httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json").bodyAsText()

        Log.d("--- FETCH ---", menuItemsJsonString)

        val listOfMenuItems: MenuNetwork = Json.decodeFromString(menuItemsJsonString)

        return listOfMenuItems.menu
        //val gson = Gson()
        //val x : MenuNetwork = gson.fromJson(listOfMenuItems, MenuNetwork::class.java).toList()


    }// fetchMenu


    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }


} // class



