package com.financiera.ecommerceapp.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.financiera.ecommerceapp.core.utils.FlowResult
import com.financiera.ecommerceapp.domain.models.movies.MovieModel
import com.financiera.ecommerceapp.domain.usecases.movies.GetMovieListFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieListFromApiUseCase: GetMovieListFromApiUseCase
) : ViewModel() {

    //Inicializo las variables  a utilizar en el fragment
    private var _moviesListModel = MutableLiveData<List<MovieModel>>()
    var moviesListModel: MutableLiveData<List<MovieModel>> = _moviesListModel

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: MutableLiveData<String> = _errorMessage

    private var _isError = MutableLiveData<Boolean>()
    var isError: MutableLiveData<Boolean> = _isError

    fun getMovieListPopularFromApi() {
        viewModelScope.launch {
            _moviesListModel.postValue(emptyList())
            val movieList = getMovieListFromApiUseCase.invoke()
            movieList.collect { movieResponse ->
                when (movieResponse) {
                    is FlowResult.Loading -> {
                        _moviesListModel.postValue(emptyList())
                        _errorMessage.postValue("")
                        isError.postValue(false)
                    }

                    is FlowResult.Success -> {
                        //Se completa la lista con las peliculas obtenidas de la API
                        _moviesListModel.postValue(movieResponse.data!!.toList())
                        _errorMessage.postValue("")
                        isError.postValue(false)
                    }

                    is FlowResult.Error -> {

                    }
                }
            }
        }
    }
}
