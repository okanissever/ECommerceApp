package com.example.ecommerceapp.viewmodel.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.datasource.room.ProductDatabase
import com.example.ecommerceapp.model.product.ProductItem
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    val dao = ProductDatabase(application).productDao()

    fun addProduct(product : ProductItem){
        viewModelScope.launch {
            dao.addFavorites(product)
        }
    }

}