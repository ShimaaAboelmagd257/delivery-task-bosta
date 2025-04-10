package com.example.delivery.data.api

import com.example.delivery.data.model.CityWithDistrictsResponse

interface RemoteSource {

    suspend fun getAllCitesWithDistricts(): CityWithDistrictsResponse
}