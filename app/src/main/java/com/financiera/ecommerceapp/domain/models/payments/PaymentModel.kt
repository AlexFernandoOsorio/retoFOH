package com.financiera.ecommerceapp.domain.models.payments

data class PaymentModel(
    val message: String,
    val success: Boolean,
    val transactionId: String
)
