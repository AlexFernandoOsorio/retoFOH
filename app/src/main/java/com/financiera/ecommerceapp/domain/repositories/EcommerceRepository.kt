package com.financiera.ecommerceapp.domain.repositories

import com.financiera.ecommerceapp.data.remote.request.CompleteRequest
import com.financiera.ecommerceapp.data.remote.request.PaymentRequest
import com.financiera.ecommerceapp.domain.models.candys.CandyModel
import com.financiera.ecommerceapp.domain.models.movies.MovieModel
import com.financiera.ecommerceapp.domain.models.payments.CompleteModel
import com.financiera.ecommerceapp.domain.models.payments.PaymentModel


interface EcommerceRepository {

    suspend fun getMovieListPopularFromRemote():List<MovieModel>

    suspend fun getCandyListPopularFromRemote():List<CandyModel>

    suspend fun putPaymentToRemote(paymentRequest: PaymentRequest): PaymentModel

    suspend fun completePaymentToRemote(completeRequest: CompleteRequest): CompleteModel

}
