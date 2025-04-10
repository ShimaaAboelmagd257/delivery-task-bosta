package com.example.delivery.util

import android.util.Log

object AppLogger {
    private const val Global_Tag = "MyDeliveryTags"

    fun d(message: String){
        Log.d(Global_Tag,message)
    }
    fun e(message: String,throwable: Throwable? = null){
        Log.d(Global_Tag,message,throwable)
    }

}