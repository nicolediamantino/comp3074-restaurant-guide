package ca.gbc.comp3074.diamantino_nicole.restaurantguide.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")

data class Restaurant(
    @PrimaryKey(autoGenerate = true)
    val restaurantId: Long = 0L,
    val name: String = "",
    val address: String = "",
    val phoneNo: String = "",
    val description: String = "",
    val tags: String = "",
    val rating: Float = 0f
)
