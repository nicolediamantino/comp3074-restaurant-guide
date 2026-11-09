package ca.gbc.comp3074.diamantino_nicole.restaurantguide.data

import androidx.room.Dao
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.model.Restaurant
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface RestaurantDataStorage {
    @Insert
    suspend fun addRestaurant(restaurant: Restaurant)

    @Update
    suspend fun editRestaurant(restaurant: Restaurant)

    @Delete
    suspend fun deleteRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM restaurants")
    fun getAllRestaurants(): Flow<List<Restaurant>>
}