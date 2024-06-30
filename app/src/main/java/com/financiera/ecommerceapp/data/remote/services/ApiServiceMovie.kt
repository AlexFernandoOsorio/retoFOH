package com.financiera.ecommerceapp.data.remote.services

import com.financiera.ecommerceapp.data.remote.response.movies.MovieListResponse
import com.financiera.ecommerceapp.data.remote.response.moviesDetail.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceMovie {

    @GET("3/movie/upcoming")
    suspend fun getMoviesPopular(): MovieListResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMoviesById(
        @Path("movie_id") movieId: String,
        @Query("api_key") apiKey: String
    ): MovieDetailDto

}
