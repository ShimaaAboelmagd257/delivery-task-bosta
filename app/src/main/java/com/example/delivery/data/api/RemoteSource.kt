package com.example.delivery.data.api

import com.example.delivery.data.model.CityDistrictsResponse

interface RemoteSource {

    suspend fun getAllCitesDistricts(): CityDistrictsResponse
}