package com.example.delivery.data.repository


import com.example.delivery.data.api.RemoteSource
import com.example.delivery.data.model.CityDistrictsResponse
import com.example.delivery.util.AppLogger
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteSource: RemoteSource):Repository {

   override suspend fun getAllCitiesDistricts():CityDistrictsResponse{
       AppLogger.d("RepositoryImpl data:${remoteSource.getAllCitesDistricts().data} ")

       return remoteSource.getAllCitesDistricts()
    }
}