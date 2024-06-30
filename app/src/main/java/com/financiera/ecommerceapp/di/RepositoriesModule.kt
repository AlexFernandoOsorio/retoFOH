package com.financiera.ecommerceapp.di

import com.financiera.ecommerceapp.data.remote.services.ApiServiceMovie
import com.financiera.ecommerceapp.data.repositories.MovieRepositoryimpl
import com.financiera.ecommerceapp.domain.repositories.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun providesMovieRepository(
        apiServiceMovie: ApiServiceMovie
    ): MovieRepository {
        return MovieRepositoryimpl(apiServiceMovie)
    }
}
