package com.example.task.data.network

import com.example.task.data.model.ResponseApiItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("admin/car")
    fun getAllCars(): Call<List<ResponseApiItem>>
}