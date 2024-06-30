package com.financiera.ecommerceapp.data.repositories

import com.financiera.ecommerceapp.data.remote.mappers.toDomainMovieList
import com.financiera.ecommerceapp.data.remote.services.ApiServiceMovie
import com.financiera.ecommerceapp.domain.models.movies.MovieModel
import com.financiera.ecommerceapp.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieRepositoryimpl @Inject constructor(
    private val apiServiceMovie: ApiServiceMovie
) : MovieRepository {

    override suspend fun getMovieListPopularFromRemote(): List<MovieModel> {
        return apiServiceMovie.getMoviesPopular().toDomainMovieList()
    }
}
