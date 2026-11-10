
package ca.gbc.comp3074.diamantino_nicole.restaurantguide.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun RestaurantDetailsScreen(
    restaurantName: String,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit = {},
    onMapClick: () -> Unit
) {
    val context = LocalContext.current

    val address = when (restaurantName) {
        "Bella Italia" -> "123 King Street, Toronto"
        "Sushi House" -> "456 Queen Street, Toronto"
        "Green Garden" -> "789 Bloor Street, Toronto"
        else -> "Address unavailable"
    }

    val phone = when (restaurantName) {
        "Bella Italia" -> "416-555-1234"
        "Sushi House" -> "416-555-5678"
        "Green Garden" -> "416-555-9012"
        else -> "Phone unavailable"
    }

    val description = when (restaurantName) {
        "Bella Italia" -> "Italian restaurant"
        "Sushi House" -> "Japanese restaurant"
        "Green Garden" -> "Vegetarian restaurant"
        else -> "No description"
    }

    val tags = when (restaurantName) {
        "Bella Italia" -> "Italian, Pasta"
        "Sushi House" -> "Japanese, Sushi"
        "Green Garden" -> "Vegetarian, Healthy"
        else -> "No tags"
    }

    val rating = when (restaurantName) {
        "Bella Italia" -> 5
        "Sushi House" -> 4
        "Green Garden" -> 5
        else -> 0
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Restaurant Details",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Name: $restaurantName")
        Text("Address: $address")
        Text("Phone: $phone")
        Text("Description: $description")
        Text("Tags: $tags")
        Text("Rating: ${"★".repeat(rating)}${"☆".repeat(5 - rating)}")

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onEditClick) {
            Text("Edit Restaurant")
        }

        Button(onClick = {}) {
            Text("Delete Restaurant")
        }

        Button(
            onClick = {
                val mapUrl = Uri.parse(
                    "https://www.google.com/maps/search/?api=1&query=" +
                        Uri.encode(address)
                )
                val intent = Intent(Intent.ACTION_VIEW, mapUrl)
                context.startActivity(intent)
            }
        ) {
            Text("View Map")
        }

        Button(onClick = onMapClick) {
            Text("Get Directions")
        }

        Button(onClick = {}) {
            Text("Share via Email")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = onBackClick) {
            Text("Back to Home")
        }
    }
}
