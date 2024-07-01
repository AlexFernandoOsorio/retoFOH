package com.financiera.ecommerceapp.data.repositories

import com.financiera.ecommerceapp.coroutineRule.CoroutineTestRule
import com.financiera.ecommerceapp.data.remote.mappers.toDomainCandyList
import com.financiera.ecommerceapp.data.remote.mappers.toDomainMovieList
import com.financiera.ecommerceapp.data.remote.request.CompleteRequest
import com.financiera.ecommerceapp.data.remote.request.PaymentRequest
import com.financiera.ecommerceapp.data.remote.response.candys.CandyListResponse
import com.financiera.ecommerceapp.data.remote.response.movies.MovieListResponse
import com.financiera.ecommerceapp.data.remote.response.payments.CompleteResponse
import com.financiera.ecommerceapp.data.remote.response.payments.PaymentResponse
import com.financiera.ecommerceapp.data.remote.services.ApiServiceEcommerce
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EcommerceRepositoryimplTest {

    @MockK
    private lateinit var apiService: ApiServiceEcommerce

    private lateinit var repository: EcommerceRepositoryimpl

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = EcommerceRepositoryimpl(apiService)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetMovieListPopularFromRemote() = coroutineTestRule.runBlockingTest {
        //Given
        val mockMovies = MovieListResponse(1, emptyList(), 1, 1)
        coEvery { apiService.getMoviesPopular() } returns mockMovies

        //When
        val result = repository.getMovieListPopularFromRemote()

        //Then
        coVerify { apiService.getMoviesPopular() }
        assertEquals(mockMovies.toDomainMovieList(), result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetCandyListPopularFromRemote() = coroutineTestRule.runBlockingTest {
        //Given
        val mockCandys = CandyListResponse(emptyList())
        coEvery { apiService.getCandysPopular() } returns mockCandys

        //When
        val result = repository.getCandyListPopularFromRemote()

        //Then
        coVerify { apiService.getCandysPopular() }
        assertEquals(mockCandys.toDomainCandyList(), result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testPutPaymentToRemote() = coroutineTestRule.runBlockingTest {
        //Given
        val mockPaymentRequest = PaymentRequest(
            "213213123",
            "123123",
            "123123",
            "prueba@gmail.com",
            "prueba",
            "DNI",
            "123123222",
            )
        val mockPaymentResponse = PaymentResponse("prueba",true,"231321231123")
        coEvery { apiService.processPayment(mockPaymentRequest) } returns mockPaymentResponse

        //When
        val result = repository.putPaymentToRemote(mockPaymentRequest)

        //Then
        assert(result.success)
    }

    @Test
    fun testCompletePaymentToRemote() = coroutineTestRule.runBlockingTest {
        //Given
        val mockCompleteRequest = CompleteRequest(
            "213213123",
            "fernandoxdk@gmail.com",
            "fernando",
            "23131313131"
        )
        val mockCompleteResponse = CompleteResponse(
            "0"
        )
        //When
        coEvery { apiService.completePayment(mockCompleteRequest) } returns mockCompleteResponse
        val result = repository.completePaymentToRemote(mockCompleteRequest)

        assert(result.resulCode == "0")
    }
}
