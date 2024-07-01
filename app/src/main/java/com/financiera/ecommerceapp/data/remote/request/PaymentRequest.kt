package com.financiera.ecommerceapp.data.remote.request

data class PaymentRequest(
    val cardNumber: String,
    val expirationDate: String,
    val cvv: String,
    val email: String,
    val name: String,
    val documentType: String,
    val documentNumber: String
)
