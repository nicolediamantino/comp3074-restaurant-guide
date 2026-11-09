package ca.gbc.comp3074.diamantino_nicole.restaurantguide.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.data.RestaurantDataStorage
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.data.RestaurantDatabase
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.model.Restaurant
import kotlinx.coroutines.launch
import kotlin.math.log

@Preview(showBackground = true)
@Composable
internal fun AddEditRestaurantPreview(){
    AddEditRestaurantScreen()
}

@Composable
fun AddEditRestaurantScreen(){
    var restaurantName by rememberSaveable() { mutableStateOf("") }
    var address by rememberSaveable() { mutableStateOf("") }
    var phone by rememberSaveable() { mutableStateOf("") }
    var description by rememberSaveable() { mutableStateOf("") }
    var tags by rememberSaveable() { mutableStateOf("") }
    var rating by remember() { mutableIntStateOf(0) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val restaurantStorage = remember((context)) {
        RestaurantDatabase.getDatabase(context).restaurantDataStorage()
    }


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add/Edit Restaurant")

        OutlinedTextField(
            value = restaurantName,
            onValueChange = { restaurantName = it },
            label = { Text("Restaurant Name") }
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") }
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") }
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            minLines = 3
        )

        OutlinedTextField(
            value = tags,
            onValueChange = { tags = it },
            label = { Text("Tags") },
            minLines = 3
        )


        Spacer(modifier = Modifier.height(16.dp))

        Text("Restaurant Rating")

        Row() {
            for (star in 1..5) {
                Icon(
                    imageVector = if (star <= rating)
                        Icons.Filled.Star
                    else
                        Icons.Outlined.Star,
                    contentDescription = "Rating $star",
                    tint = if (star <= rating)
                        Color(0xFFFFC107)
                    else
                        Color.Gray,

                    modifier = Modifier
                        .clickable { rating = star }
                        .padding(8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (restaurantName.isBlank() || address.isBlank()) {
                    Toast.makeText(
                        context, "Please enter restaurant name and address",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val restaurant = Restaurant(
                        name = restaurantName,
                        address = address,
                        phoneNo = phone,
                        description = description,
                        tags = tags,
                        rating = rating.toFloat()
                    )

                    coroutineScope.launch {
                        try {
                            restaurantStorage.addRestaurant(restaurant)

                            Toast.makeText(
                                context, "Restaurant saved successfully!", Toast.LENGTH_SHORT
                            ).show()
                        } catch (e: Exception) {
                            Log.e("RestaurantGuide", "Error saving restaurant", e)

                            Toast.makeText(context, "Error saving restaurant", Toast.LENGTH_SHORT).show()
                        }
                    }
                    Log.d("RestaurantGuide", restaurant.toString())


                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Restaurant")
        }

    }

}