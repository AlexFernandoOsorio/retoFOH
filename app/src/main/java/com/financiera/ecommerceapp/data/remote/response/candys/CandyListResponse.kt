package com.financiera.ecommerceapp.data.remote.response.candys


import com.google.gson.annotations.SerializedName

data class CandyListResponse(
    @SerializedName("items")
    val items: List<CandyDto>
)
