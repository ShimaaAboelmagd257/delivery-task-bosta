package com.example.delivery.data.repository

import com.example.delivery.data.model.CityDistrictsResponse

interface Repository {

    suspend fun getAllCitiesDistricts(): CityDistrictsResponse
}