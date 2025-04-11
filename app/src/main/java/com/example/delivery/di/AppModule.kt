package com.example.delivery.di

import com.example.delivery.data.api.DeliveryClient
import com.example.delivery.data.api.DeliveryService
import com.example.delivery.data.api.RemoteSource
import com.example.delivery.data.api.RetrofitHelper
import com.example.delivery.data.repository.Repository
import com.example.delivery.data.repository.RepositoryImpl
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
   fun deliveryService(): DeliveryService {
      return  RetrofitHelper.retrofitInstance.create(DeliveryService::class.java)
    }
    @Provides
    fun provideRepository(
     remoteSource: RemoteSource,
    ): Repository {
        return RepositoryImpl(remoteSource)
    }


        @Provides
    fun provideRemoteSource(): RemoteSource {
        return DeliveryClient(deliveryService())
    }


}





