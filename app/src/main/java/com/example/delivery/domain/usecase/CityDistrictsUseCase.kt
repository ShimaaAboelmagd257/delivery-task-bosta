package com.example.delivery.domain.usecase

import com.example.delivery.data.model.CityDistricts
import com.example.delivery.data.repository.Repository
import com.example.delivery.ui.theme.citydistricts.CityDistrictsStates
import com.example.delivery.util.AppLogger
import javax.inject.Inject

class CityDistrictsUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke():CityDistrictsStates<List<CityDistricts>>{
        return try {
            val response =   repository.getAllCitiesDistricts()
            if (response.success && response.data.isNotEmpty()){
                AppLogger.d("CityDistrictsUseCase success:${response.data} ")
                CityDistrictsStates.Success(response.data)
            }else{
                AppLogger.d("CityDistrictsUseCase response:${response.message} ")
                CityDistrictsStates.Error(response.message)

            }
        }catch (e:Exception){
            AppLogger.d("CityDistrictsUseCase Exception:${e.message} ")
            CityDistrictsStates.Error("ERROR: ${e.message}")

        }
    }
}