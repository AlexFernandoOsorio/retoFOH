package com.financiera.ecommerceapp.data.remote.response.payments


import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("transactionId")
    val transactionId: String
)
