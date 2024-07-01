package com.financiera.ecommerceapp.domain.usecases.payments

import com.financiera.ecommerceapp.core.utils.FlowResult
import com.financiera.ecommerceapp.data.remote.request.CompleteRequest
import com.financiera.ecommerceapp.domain.models.payments.CompleteModel
import com.financiera.ecommerceapp.domain.repositories.EcommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CompletePaymentToApiUseCase @Inject constructor(
    private val ecommerceRepository: EcommerceRepository
) {
    operator fun invoke(completeRequest: CompleteRequest) = flow<FlowResult<CompleteModel>> {
        emit(FlowResult.Loading())
        val complete = ecommerceRepository.completePaymentToRemote(completeRequest)
        emit(FlowResult.Success(complete))
    }.catch {
        emit(FlowResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}
