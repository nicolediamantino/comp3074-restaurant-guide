package ca.gbc.comp3074.diamantino_nicole.restaurantguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Box
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.ui.screens.HomeScreen
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.ui.screens.AboutScreen
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.ui.screens.RestaurantDetailsScreen
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.ui.theme.RestaurantGuideTheme
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.ui.screens.AddEditRestaurantScreen
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.ui.screens.MapScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RestaurantGuideTheme {

                var currentScreen by remember {
                    mutableStateOf("Home")
                }

                        var selectedRestaurant by remember {
                            mutableStateOf("Bella Italia")
                        }
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {

                            when (currentScreen) {
                                "Home" -> HomeScreen(
                                     onAboutClick = {
                                        currentScreen = "About"
                                     },
                                    onRestaurantClick = { restaurantName ->
                                        selectedRestaurant = restaurantName
                                        currentScreen = "Details"
                                    },
                                    onAddRestaurantClick = {
                                           currentScreen = "add"
                                        }
                                )
                                        "About" -> AboutScreen(
                                            onBack = { currentScreen = "Home"
                                            }
                                        ) //changes to map screen
                            "Details" -> RestaurantDetailsScreen(
                                restaurantName = selectedRestaurant,
                                onBackClick = {
                                    currentScreen = "Home"
                                },
                                onMapClick = {
                                    currentScreen = "Map"
                                }
                            )


                            "Map" -> MapScreen(
                                restaurantName = selectedRestaurant,
                                onBack = {
                                    currentScreen = "Details"

                                }

                                )
                                "add" -> AddEditRestaurantScreen()
                            }

                        }
                    }
                }
            }
        }
    }







