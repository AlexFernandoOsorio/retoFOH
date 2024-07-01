package com.financiera.ecommerceapp.domain.usecases.candys

import com.financiera.ecommerceapp.core.utils.FlowResult
import com.financiera.ecommerceapp.domain.models.candys.CandyModel
import com.financiera.ecommerceapp.domain.repositories.EcommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCandyListFromApiUseCase @Inject constructor(
    private val ecommerceRepository: EcommerceRepository
){
    operator fun invoke() = flow<FlowResult<List<CandyModel>>> {
        emit(FlowResult.Loading())
        val candyList = ecommerceRepository.getCandyListPopularFromRemote()
        emit(FlowResult.Success(candyList))
    }.catch {
        emit(FlowResult.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}
