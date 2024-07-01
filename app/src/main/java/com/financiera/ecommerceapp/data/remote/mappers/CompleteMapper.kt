package com.financiera.ecommerceapp.data.remote.mappers

import com.financiera.ecommerceapp.data.remote.response.payments.CompleteResponse
import com.financiera.ecommerceapp.domain.models.payments.CompleteModel

fun CompleteResponse.toDomainComplete(): CompleteModel {
    return CompleteModel(
        resulCode = this.resulCode
    )
}
