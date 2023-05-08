package com.example.ecommerceapp.common

import com.example.ecommerceapp.common.Constans.BASE_URL
import com.example.ecommerceapp.datasource.remote.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtils {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService by lazy { retrofit.create(ApiService::class.java) }
}