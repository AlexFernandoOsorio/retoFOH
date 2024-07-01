package com.financiera.ecommerceapp.data.remote.mappers

import com.financiera.ecommerceapp.data.remote.response.candys.CandyListResponse
import com.financiera.ecommerceapp.domain.models.candys.CandyModel

fun CandyListResponse.toDomainCandyList(): List<CandyModel> {
    return this.items.map { candyDto ->
        CandyModel(
            name = candyDto.name,
            description = candyDto.description,
            price = candyDto.price
        )
    }
}
