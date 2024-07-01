package com.financiera.ecommerceapp.di

import com.financiera.ecommerceapp.data.remote.services.ApiServiceEcommerce
import com.financiera.ecommerceapp.data.repositories.EcommerceRepositoryimpl
import com.financiera.ecommerceapp.domain.repositories.EcommerceRepository
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
        apiServiceMovie: ApiServiceEcommerce
    ): EcommerceRepository {
        return EcommerceRepositoryimpl(apiServiceMovie)
    }
}
