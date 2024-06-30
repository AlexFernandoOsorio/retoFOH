package com.financiera.ecommerceapp.domain.usecases.movies

import com.financiera.ecommerceapp.core.utils.FlowResult
import com.financiera.ecommerceapp.domain.models.movies.MovieModel
import com.financiera.ecommerceapp.domain.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieListFromApiUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    // Caso de uso para obtener la lista de peliculas populares indexado por popularidad
    operator fun invoke() = flow<FlowResult<List<MovieModel>>> {
        emit(FlowResult.Loading())
        val movieList = movieRepository.getMovieListPopularFromRemote()
        emit(FlowResult.Success(movieList))
    }.catch {
        // En caso de error se emite un evento de error
        emit(FlowResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}