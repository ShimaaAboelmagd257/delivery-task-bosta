package com.example.delivery.ui.citydistricts


sealed class CityDistrictsStates<out T> {
    object Loading : CityDistrictsStates<Nothing>()
    data class Success<out T>(val data: T): CityDistrictsStates<T>()
    data class Error (val error:String): CityDistrictsStates<Nothing>()
}
