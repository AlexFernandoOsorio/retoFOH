package com.financiera.ecommerceapp.data.remote.response.candys


import com.google.gson.annotations.SerializedName

data class CandyDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String
)
