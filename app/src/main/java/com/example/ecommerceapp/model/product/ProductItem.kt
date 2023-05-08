package com.example.ecommerceapp.model.product


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "product_table")
data class ProductItem(
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String?,
    @PrimaryKey
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("title")
    val title: String?
) : Parcelable