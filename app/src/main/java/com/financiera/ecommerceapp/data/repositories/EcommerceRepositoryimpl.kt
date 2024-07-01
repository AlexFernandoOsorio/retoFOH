package com.financiera.ecommerceapp.data.repositories

import com.financiera.ecommerceapp.data.remote.mappers.toDomainCandyList
import com.financiera.ecommerceapp.data.remote.mappers.toDomainComplete
import com.financiera.ecommerceapp.data.remote.mappers.toDomainMovieList
import com.financiera.ecommerceapp.data.remote.mappers.toDomainPayment
import com.financiera.ecommerceapp.data.remote.request.CompleteRequest
import com.financiera.ecommerceapp.data.remote.request.PaymentRequest
import com.financiera.ecommerceapp.data.remote.services.ApiServiceEcommerce
import com.financiera.ecommerceapp.domain.models.candys.CandyModel
import com.financiera.ecommerceapp.domain.models.movies.MovieModel
import com.financiera.ecommerceapp.domain.models.payments.CompleteModel
import com.financiera.ecommerceapp.domain.models.payments.PaymentModel
import com.financiera.ecommerceapp.domain.repositories.EcommerceRepository
import javax.inject.Inject

class EcommerceRepositoryimpl @Inject constructor(
    private val apiService: ApiServiceEcommerce
) : EcommerceRepository {

    override suspend fun getMovieListPopularFromRemote(): List<MovieModel> {
        return apiService.getMoviesPopular().toDomainMovieList()
    }

    override suspend fun getCandyListPopularFromRemote(): List<CandyModel> {
        return apiService.getCandysPopular().toDomainCandyList()
    }

    override suspend fun putPaymentToRemote(paymentRequest: PaymentRequest): PaymentModel {
        return apiService.processPayment(paymentRequest).toDomainPayment()
    }

    override suspend fun completePaymentToRemote(completeRequest: CompleteRequest): CompleteModel {
        return apiService.completePayment(completeRequest).toDomainComplete()
    }
}
