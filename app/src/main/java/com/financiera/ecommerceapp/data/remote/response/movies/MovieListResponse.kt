package com.financiera.ecommerceapp.data.remote.response.movies

data class MovieListResponse(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)
