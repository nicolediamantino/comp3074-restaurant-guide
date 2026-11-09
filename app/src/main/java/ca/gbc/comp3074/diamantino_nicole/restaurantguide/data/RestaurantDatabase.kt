

package ca.gbc.comp3074.diamantino_nicole.restaurantguide.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.gbc.comp3074.diamantino_nicole.restaurantguide.model.Restaurant

@Database(
    entities = [Restaurant::class],
    version = 1,
    exportSchema = false
)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDataStorage(): RestaurantDataStorage

    companion object {

        @Volatile
        private var INSTANCE: RestaurantDatabase? = null

        fun getDatabase(context: Context): RestaurantDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java,
                    "restaurant_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
