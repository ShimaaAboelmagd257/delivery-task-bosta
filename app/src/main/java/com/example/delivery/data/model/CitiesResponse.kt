package com.example.delivery.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CitiesResponse (
    val success: Boolean,
    val message: String,
    val data: List<City>
): Parcelable

@Parcelize
data class City(
    val id: String,
    val name: String,
    val nameAr: String,
    val alias: String,
    val hub: Hub,
    val code: String,
    val sector: Int,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
    val showAsDropOffCity: Boolean,
    val showAsPickupCity: Boolean
): Parcelable

@Parcelize
data class Hub(
    val id: String,
    val name: String
):Parcelable
