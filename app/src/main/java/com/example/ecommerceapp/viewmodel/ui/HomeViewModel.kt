package com.example.ecommerceapp.viewmodel.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.common.ApiUtils
import com.example.ecommerceapp.model.product.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    val productList = MutableLiveData<List<ProductItem>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private var job : Job? = null

    
    private val service = ApiUtils.apiService

    fun getAllProduct(){
        job = viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                loading.value = true
            }
            try {
                val response = service.getAllProducts()
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        response.body()?.let {
                            productList.value = emptyList()
                            productList.value = it
                        }
                    }else{
                        error.value = true
                    }
                    loading.value = false
                }
            }
            catch (e:Exception){
                e.printStackTrace()
                error.postValue(true)
            }
        }

    }

    fun getJewelery(){
        job = viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                loading.value = true
            }
            try {
                val response = service.getJewelery()
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        response.body()?.let {
                            productList.value = emptyList()
                            productList.value = it
                        }
                    }else{
                        error.value = true
                    }
                    loading.value = false
                }
            }
            catch (e:Exception){
                e.printStackTrace()
                error.postValue(true)
            }
        }
    }

    fun getElectronics(){
        job = viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                loading.value = true
            }
            try {
                val response = service.getElectronics()
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        response.body()?.let {
                            productList.value = emptyList()
                            productList.value = it
                        }
                    }else{
                        error.value = true
                    }
                    loading.value = false
                }
            }
            catch (e:Exception){
                e.printStackTrace()
                error.postValue(true)
            }
        }
    }

    fun getMensClothing(){
        job = viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                loading.value = true
            }
            try {
                val response = service.getMensClothing()
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        response.body()?.let {
                            productList.value = emptyList()
                            productList.value = it
                        }
                    }else{
                        error.value = true
                    }
                    loading.value = false
                }
            }
            catch (e:Exception){
                e.printStackTrace()
                error.postValue(true)
            }
        }
    }

    fun getWomensClothing(){
        job = viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main){
                loading.value = true
            }
            try {
                val response = service.getWomensClothing()
                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        response.body()?.let {
                            productList.value = emptyList()
                            productList.value = it
                        }
                    }else{
                        error.value = true
                    }
                    loading.value = false
                }
            }
            catch (e:Exception){
                e.printStackTrace()
                error.postValue(true)
            }
        }
    }


}