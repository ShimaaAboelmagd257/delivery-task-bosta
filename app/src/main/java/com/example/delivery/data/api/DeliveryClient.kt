package com.example.delivery.data.api

import com.example.delivery.data.model.CityDistrictsResponse
import com.example.delivery.util.AppLogger
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DeliveryClient @Inject constructor(private val deliveryService:DeliveryService):RemoteSource {


    override suspend fun getAllCitesDistricts():CityDistrictsResponse{
        AppLogger.d("DeliveryClientResponse status:${deliveryService.getAllCitiesDistricts().success} ")
        AppLogger.d("City Districts Response: ${deliveryService.getAllCitiesDistricts()}")
        return deliveryService.getAllCitiesDistricts()
    }


}
