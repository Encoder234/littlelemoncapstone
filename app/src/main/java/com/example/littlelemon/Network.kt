package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase


@Entity
data class MenuItems(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String

)

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItems")
    fun getAll(): LiveData<List<MenuItems>>

    @Query("SELECT * FROM MenuItems")
    fun myGetAll(): List<MenuItems>

    @Insert
    fun insertAll(vararg menuItems: MenuItems)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItems) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItems::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}
