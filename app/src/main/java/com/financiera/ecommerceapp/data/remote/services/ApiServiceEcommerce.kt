package com.financiera.ecommerceapp.data.remote.services

import com.financiera.ecommerceapp.core.utils.GlobalConstants.END_POINT_CANDYS
import com.financiera.ecommerceapp.core.utils.GlobalConstants.END_POINT_COMPLETE
import com.financiera.ecommerceapp.core.utils.GlobalConstants.END_POINT_MOVIES
import com.financiera.ecommerceapp.core.utils.GlobalConstants.END_POINT_TRANSACTION
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

    @GET(END_POINT_MOVIES)
    suspend fun getMoviesPopular(): MovieListResponse

    @GET(END_POINT_CANDYS)
    suspend fun getCandysPopular(): CandyListResponse

    @POST(END_POINT_TRANSACTION)
    suspend fun processPayment(@Body paymentRequest: PaymentRequest): PaymentResponse

    @POST(END_POINT_COMPLETE)
    suspend fun completePayment(@Body completeRequest: CompleteRequest): CompleteResponse

}
