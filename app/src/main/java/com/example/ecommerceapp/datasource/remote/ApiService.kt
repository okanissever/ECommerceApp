package com.example.ecommerceapp.datasource.remote

import com.example.ecommerceapp.model.product.ProductItem

import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("products")
    suspend fun getAllProducts() : Response<List<ProductItem>>

    @GET("products/category/jewelery")
    suspend fun getJewelery() : Response<List<ProductItem>>

    @GET("products/category/electronics")
    suspend fun getElectronics() : Response<List<ProductItem>>

    @GET("products/category/men's clothing")
    suspend fun getMensClothing() : Response<List<ProductItem>>

    @GET("products/category/women's clothing")
    suspend fun getWomensClothing() : Response<List<ProductItem>>

}