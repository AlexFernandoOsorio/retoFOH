package com.financiera.ecommerceapp.features.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.financiera.ecommerceapp.core.utils.FlowResult
import com.financiera.ecommerceapp.data.remote.request.CompleteRequest
import com.financiera.ecommerceapp.data.remote.request.PaymentRequest
import com.financiera.ecommerceapp.domain.models.payments.PaymentModel
import com.financiera.ecommerceapp.domain.usecases.payments.CompletePaymentToApiUseCase
import com.financiera.ecommerceapp.domain.usecases.payments.PushPaymentToApiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val pushPaymentToApiUseCase: PushPaymentToApiUseCase,
    private val completePaymentToApiUseCase: CompletePaymentToApiUseCase
) : ViewModel() {

    private var _paymentModel = MutableLiveData<PaymentModel>()
    var paymentModel: MutableLiveData<PaymentModel> = _paymentModel

    private var _errorMessage = MutableLiveData<String>()
    var errorMessage: MutableLiveData<String> = _errorMessage

    private var _isError = MutableLiveData<Boolean>()
    var isError: MutableLiveData<Boolean> = _isError

    private fun handleLoadingState() {
        _paymentModel.postValue(PaymentModel("", false, ""))
        _errorMessage.postValue("")
        isError.postValue(false)
    }

    private fun handleSuccessState(paymentResponse: FlowResult.Success<PaymentModel>) {
        _paymentModel.postValue(paymentResponse.data!!)
        _errorMessage.postValue("")
        isError.postValue(false)
    }

    private fun handleErrorState() {
        // Aquí puedes manejar el estado de error
    }

    fun pushPaymentToApi(
        cardNumber : String,
        expirationDate : String,
        cvv : String,
        email : String,
        name : String,
        documentType : String,
        documentNumber : String
    ) {
        viewModelScope.launch {
            val paymentRequest = PaymentRequest(cardNumber, expirationDate, cvv, email, name, documentType, documentNumber)
            val operationDateTime = System.currentTimeMillis().toString()
            val completeRequest = CompleteRequest(documentNumber,email,name,operationDateTime)
            val payment = pushPaymentToApiUseCase.invoke(paymentRequest)
            payment.collect { paymentResponse ->
                when (paymentResponse) {
                    is FlowResult.Loading -> handleLoadingState()
                    is FlowResult.Success -> {
                        completePaymentToApiUseCase.invoke(completeRequest).collect { completeResponse ->
                            when (completeResponse) {
                                is FlowResult.Loading -> handleLoadingState()
                                is FlowResult.Success -> handleSuccessState(paymentResponse)
                                is FlowResult.Error -> handleErrorState()
                            }
                        }
                    }
                    is FlowResult.Error -> handleErrorState()
                }
            }
        }
    }
}