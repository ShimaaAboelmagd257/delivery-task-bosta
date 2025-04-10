package com.example.delivery.data.api

import com.example.delivery.data.model.CityWithDistrictsResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DeliveryClient @Inject constructor(private val deliveryService:DeliveryService):RemoteSource {


    override suspend fun getAllCitesWithDistricts():CityWithDistrictsResponse{
        return deliveryService.getAllCities()
    }


}
