package com.financiera.ecommerceapp.di

import com.financiera.ecommerceapp.domain.repositories.EcommerceRepository
import com.financiera.ecommerceapp.domain.usecases.candys.GetCandyListFromApiUseCase
import com.financiera.ecommerceapp.domain.usecases.movies.GetMovieListFromApiUseCase
import com.financiera.ecommerceapp.domain.usecases.payments.CompletePaymentToApiUseCase
import com.financiera.ecommerceapp.domain.usecases.payments.PushPaymentToApiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGetMovieListFromApiUseCase(repository: EcommerceRepository): GetMovieListFromApiUseCase {
        return GetMovieListFromApiUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetCandyListFromApiUseCase(repository: EcommerceRepository): GetCandyListFromApiUseCase {
        return GetCandyListFromApiUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesPushPaymentToApiUseCase(repository: EcommerceRepository): PushPaymentToApiUseCase {
        return PushPaymentToApiUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesCompletePaymentToApiUseCase(repository: EcommerceRepository): CompletePaymentToApiUseCase {
        return CompletePaymentToApiUseCase(repository)
    }
}
