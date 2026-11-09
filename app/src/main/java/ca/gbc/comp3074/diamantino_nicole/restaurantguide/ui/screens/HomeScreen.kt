package ca.gbc.comp3074.diamantino_nicole.restaurantguide.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "Restaurant Guide",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search restaurants or tags") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "My Restaurants",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        RestaurantCard(
            name = "Bella Italia",
            cuisine = "Italian",
            address = "123 King Street, Toronto",
            rating = 5,
            onClick = { onRestaurantClick("Bella Italia") }

        )

        RestaurantCard(
            name = "Sushi House",
            cuisine = "Japanese",
            address = "456 Queen Street, Toronto",
            rating = 4,
            onClick = { onRestaurantClick("Sushi House") }
        )

        RestaurantCard(
            name = "Green Garden",
            cuisine = "Vegetarian",
            address = "789 Bloor Street, Toronto",
            rating = 5,
            onClick = { onRestaurantClick("Green Garden") }
        )
        restaurants.forEach { restaurant ->
            RestaurantCard(
                name = restaurant.name,
                cuisine = restaurant.tags,
                address = restaurant.address,
                rating = restaurant.rating.toInt(),
                onClick = {onRestaurantClick(restaurant.name)}

            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onAddRestaurantClick()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Restaurant")

            Spacer(modifier = Modifier.height(16.dp))


        }
        Button(
            onClick = onAboutClick
        )
        {
            Text("About Us")

            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(
            onClick = onMapClick
        ) {
            Text("Map & Directions")

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

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

            Text(text = cuisine)
            Text(text = address)

            Text(
                text = "★".repeat(rating) +
                        "☆".repeat(5 - rating)
            )
        }
    }
}