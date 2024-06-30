package com.financiera.ecommerceapp.di

import com.financiera.ecommerceapp.domain.repositories.MovieRepository
import com.financiera.ecommerceapp.domain.usecases.movies.GetMovieListFromApiUseCase
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
    fun providesGetMovieListFromApiUseCase(repository: MovieRepository): GetMovieListFromApiUseCase {
        return GetMovieListFromApiUseCase(repository)
    }
}
