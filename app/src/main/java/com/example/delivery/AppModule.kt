package com.example.delivery

import com.example.delivery.data.api.DeliveryService
import com.example.delivery.data.api.RetrofitHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitHelper.retrofitInstance
    }

    @Provides
    @Singleton
   fun deliveryService(retrofit: Retrofit): DeliveryService {
      return  RetrofitHelper.retrofitInstance.create(DeliveryService::class.java)
    }




}





