package com.example.delivery.ui.citydistricts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.delivery.data.model.CityDistricts
import com.example.delivery.domain.usecase.CityDistrictsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityDistrictsViewModel @Inject constructor(private val cityDistrictsUseCase: CityDistrictsUseCase):ViewModel() {

    private val _state = MutableStateFlow<CityDistrictsStates<List<CityDistricts>>>(
        CityDistrictsStates.Loading
    )
    val state: StateFlow<CityDistrictsStates<List<CityDistricts>>> = _state
    init {
        fetchCityDistricts()
    }
    private fun fetchCityDistricts(){
        viewModelScope.launch {
            _state.value = CityDistrictsStates.Loading
            try {
                val response = cityDistrictsUseCase.invoke()
                _state.value = response
            }catch (e:Exception){
                _state.value = CityDistrictsStates.Error(e.message ?: "Error")
            }
        }
    }

     fun toggleExpansion(cityId:String){
        val currentState = _state.value
        if(currentState is CityDistrictsStates.Success){
            val updateList = currentState.data.map {
                if(it.cityId == cityId) it.copy(isExpanded = !it.isExpanded)
                else it.copy(isExpanded = false)
            }
            _state.value = CityDistrictsStates.Success(updateList)
        }
    }
}