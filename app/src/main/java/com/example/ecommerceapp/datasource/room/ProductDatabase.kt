package com.example.ecommerceapp.datasource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecommerceapp.model.product.ProductItem

@Database(entities = [ProductItem::class], version = 1)
abstract class ProductDatabase : RoomDatabase(){

    abstract fun productDao() : ProductDao

    companion object{
        @Volatile private var instance : ProductDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }
        private fun makeDatabase(context: Context) = Room.
        databaseBuilder(context.applicationContext,ProductDatabase::class.java,"productdatabase").build()
    }
}