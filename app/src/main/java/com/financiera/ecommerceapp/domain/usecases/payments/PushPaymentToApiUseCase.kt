package com.financiera.ecommerceapp.domain.usecases.payments

import com.financiera.ecommerceapp.core.utils.FlowResult
import com.financiera.ecommerceapp.data.remote.request.PaymentRequest
import com.financiera.ecommerceapp.domain.models.payments.PaymentModel
import com.financiera.ecommerceapp.domain.repositories.EcommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PushPaymentToApiUseCase @Inject constructor(
    private val ecommerceRepository: EcommerceRepository
){

    operator fun invoke(paymentRequest: PaymentRequest) = flow<FlowResult<PaymentModel>> {
        emit(FlowResult.Loading())
        val payment = ecommerceRepository.putPaymentToRemote(paymentRequest)
        emit(FlowResult.Success(payment))
    }.catch {
        emit(FlowResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}
