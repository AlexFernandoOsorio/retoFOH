package com.financiera.ecommerceapp.data.remote.services

import com.financiera.ecommerceapp.data.remote.request.CompleteRequest
import com.financiera.ecommerceapp.data.remote.request.PaymentRequest
import com.financiera.ecommerceapp.data.remote.response.candys.CandyListResponse
import com.financiera.ecommerceapp.data.remote.response.movies.MovieListResponse
import com.financiera.ecommerceapp.data.remote.response.payments.CompleteResponse
import com.financiera.ecommerceapp.data.remote.response.payments.PaymentResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServiceEcommerce {

    @GET("3/movie/upcoming")
    suspend fun getMoviesPopular(): MovieListResponse

    @GET("3/candy/upcoming")
    suspend fun getCandysPopular(): CandyListResponse

    @POST("3/payment/transaction")
    suspend fun processPayment(@Body paymentRequest: PaymentRequest): PaymentResponse

    @POST("3/payment/complete")
    suspend fun completePayment(@Body completeRequest: CompleteRequest): CompleteResponse

}
