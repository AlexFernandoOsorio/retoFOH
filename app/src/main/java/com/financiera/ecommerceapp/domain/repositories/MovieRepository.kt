package com.financiera.ecommerceapp.domain.repositories

import com.financiera.ecommerceapp.domain.models.movies.MovieModel


interface MovieRepository {

    suspend fun getMovieListPopularFromRemote():List<MovieModel>

}
