package com.financiera.ecommerceapp.di

import com.financiera.ecommerceapp.core.utils.GlobalConstants.BASE_URL
import com.financiera.ecommerceapp.data.remote.services.ApiServiceMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiServiceMovie(retrofit: Retrofit): ApiServiceMovie {
        return provideRetrofit().create(ApiServiceMovie::class.java)
    }
}
