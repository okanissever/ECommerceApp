package com.example.ecommerceapp.viewmodel.shared

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.product.ProductItem

class SharedViewModel : ViewModel() {
    val selectedProducts = MutableLiveData<List<ProductItem>>(mutableListOf())
    var price = MutableLiveData(0.0)

    fun addProduct(product: ProductItem) {
        val updatedList = selectedProducts.value?.toMutableList() ?: mutableListOf()
        updatedList.add(product)
        selectedProducts.value = updatedList
        Log.d("SharedViewModel", "Ürün eklendi: $product")
        price.value = price.value?.let { it + (product.price?.toDouble() ?: 0.0) } ?: 0.0
    }

    fun removeProduct(product: ProductItem){
        val updatedList = selectedProducts.value?.toMutableList() ?: mutableListOf()
        if (updatedList.remove(product)) {
            selectedProducts.value = updatedList
            val productPrice = product.price?.toDouble() ?: 0.0
            price.value = (price.value ?: 0.0) - productPrice
            Log.d("SharedViewModel", "Ürün silindi: $product, yeni toplam: ${price.value}")
        } else {
            Log.d("SharedViewModel", "Ürün silinemedi: $product")
        }
    }

}