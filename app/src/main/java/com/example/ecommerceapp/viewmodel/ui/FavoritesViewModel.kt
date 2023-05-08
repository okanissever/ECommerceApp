package com.example.ecommerceapp.viewmodel.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.datasource.room.ProductDatabase
import com.example.ecommerceapp.model.product.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = ProductDatabase(application).productDao()
    val productList = MutableLiveData<List<ProductItem>>()

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = dao.getAllFavorites()
            withContext(Dispatchers.Main){
                productList.value = list
            }
        }
    }

    fun deleteProduct(product : ProductItem){
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteFavorites(product)
            getAll()
        }

    }

}