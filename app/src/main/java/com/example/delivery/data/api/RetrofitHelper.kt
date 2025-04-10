package com.example.delivery.data.api

import android.util.Log
import com.example.delivery.utl.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer undefined")
                .method(original.method(), original.body())
                .build()
         chain.proceed(request)
        }.build()


    val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.Base_Url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
        //    .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}
