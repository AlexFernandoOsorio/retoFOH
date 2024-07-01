package com.financiera.ecommerceapp.data.remote.mappers

import com.financiera.ecommerceapp.data.remote.response.payments.PaymentResponse
import com.financiera.ecommerceapp.domain.models.payments.PaymentModel

fun PaymentResponse.toDomainPayment(): PaymentModel {
    return PaymentModel(
        message = this.message,
        success = this.success,
        transactionId = this.transactionId
    )
}
