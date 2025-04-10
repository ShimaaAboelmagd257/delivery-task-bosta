package com.example.delivery.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityWithDistrictsResponse(
    val success: Boolean,
    val message: String,
    val data: List<CityWithDistricts>
):Parcelable

@Parcelize
data class CityWithDistricts(
    val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val cityCode: String,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
    val districts: List<District>,
    var isExpanded: Boolean = false
):Parcelable

@Parcelize
data class District(
    val zoneId: String,
    val zoneName: String,
    val zoneOtherName: String,
    val districtId: String,
    val districtName: String,
    val districtOtherName: String,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean
):Parcelable

