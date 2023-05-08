package com.example.ecommerceapp.datasource.room

import androidx.room.*
import com.example.ecommerceapp.model.product.ProductItem

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorites(product : ProductItem)

    @Query("SELECT * FROM product_table")
    suspend fun getAllFavorites() : List<ProductItem>

    @Delete
    suspend fun deleteFavorites(product : ProductItem)

}