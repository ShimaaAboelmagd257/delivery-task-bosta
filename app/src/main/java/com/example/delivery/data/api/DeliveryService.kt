package com.example.delivery.data.api

import com.example.delivery.data.model.CityWithDistrictsResponse
import retrofit2.http.GET

interface DeliveryService {

    @GET("cities/getAllDistricts")
    suspend fun getAllCities():CityWithDistrictsResponse
}