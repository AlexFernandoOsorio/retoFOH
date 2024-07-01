package com.financiera.ecommerceapp.features.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.financiera.ecommerceapp.core.utils.FlowResult
import com.financiera.ecommerceapp.domain.models.candys.CandyModel
import com.financiera.ecommerceapp.domain.usecases.candys.GetCandyListFromApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getCandyListFromApiUseCase: GetCandyListFromApiUseCase
) : ViewModel() {

    private var _candyListModel = MutableLiveData<List<CandyModel>>()
    var candyListModel: MutableLiveData<List<CandyModel>> = _candyListModel

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: MutableLiveData<String> = _errorMessage

    private var _isError = MutableLiveData<Boolean>()
    var isError: MutableLiveData<Boolean> = _isError

    var montoTotal: MutableLiveData<Double> = MutableLiveData(0.0)
    fun getCandyListPopularFromApi() {
        viewModelScope.launch {
            _candyListModel.postValue(emptyList())
            val candyList = getCandyListFromApiUseCase.invoke()
            candyList.collect { candyResponse ->
                when (candyResponse) {
                    is FlowResult.Loading -> {
                        _candyListModel.postValue(emptyList())
                        _errorMessage.postValue("")
                        isError.postValue(false)
                    }

                    is FlowResult.Success -> {
                        _candyListModel.postValue(candyResponse.data!!.toList())
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
