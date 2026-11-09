
package ca.gbc.comp3074.diamantino_nicole.restaurantguide.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import ca.gbc.comp3074.diamantino_nicole.restaurantguide.model.Restaurant

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onRestaurantClick: (String) -> Unit,
    onAboutClick: () -> Unit,
    onMapClick: () -> Unit,
    onAddRestaurantClick: () -> Unit,
    restaurants: List<Restaurant> = emptyList()
){

    // Stores the text typed in the search bar
    var searchQuery by remember { mutableStateOf("") }

    // Sample restaurants using the team's Restaurant model
    val samplerestaurants = listOf(
        Restaurant(
            restaurantId = 1L,
            name = "Bella Italia",
            address = "123 King Street, Toronto",
            tags = "Italian, Pasta",
            rating = 5f
        ),
        Restaurant(
            restaurantId = 2L,
            name = "Sushi House",
            address = "456 Queen Street, Toronto",
            tags = "Japanese, Sushi",
            rating = 4f
        ),
        Restaurant(
            restaurantId = 3L,
            name = "Green Garden",
            address = "789 Bloor Street, Toronto",
            tags = "Vegetarian",
            rating = 5f
        )
    )
    // Combine sample restaurants with saved restaurants
    val allRestaurants = sampleRestaurants + restaurants
  
    // Filter restaurants by name or tags
    val filteredRestaurants = allrestaurants.filter { restaurant ->
        restaurant.name.contains(searchQuery, ignoreCase = true) ||
                restaurant.tags.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // Page title
        Text(
            text = "Restaurant Guide",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search restaurants or tags") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Restaurant list title
        Text(
            text = "My Restaurants",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display restaurants matching the search
        if (filteredRestaurants.isEmpty()) {

            Text(
                text = "No restaurants found.",
                style = MaterialTheme.typography.bodyMedium
            )

        } else {

            filteredRestaurants.forEach { restaurant ->

                RestaurantCard(
                    name = restaurant.name,
                    cuisine = restaurant.tags,
                    address = restaurant.address,
                    rating = restaurant.rating.toInt(),
                    onClick = {
                        onRestaurantClick(restaurant.name)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Add Restaurant button
        Button(
            onClick = onAddRestaurantClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Restaurant")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // About Us button
        Button(
            onClick = onAboutClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("About Us")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Map & Directions button
        Button(
            onClick = onMapClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Map & Directions")
        }
    }
}

// Restaurant Card
@Composable
fun RestaurantCard(
    name: String,
    cuisine: String,
    address: String,
    rating: Int,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = cuisine,
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = address,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Display restaurant rating as stars
            val safeRating = rating.coerceIn(0, 5)

            Text(
                text = "★".repeat(safeRating) +
                        "☆".repeat(5 - safeRating)
            )
        }
    }
}
