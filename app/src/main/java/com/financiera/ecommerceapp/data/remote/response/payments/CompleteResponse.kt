package com.financiera.ecommerceapp.data.remote.response.payments


import com.google.gson.annotations.SerializedName

data class CompleteResponse(
    @SerializedName("resul_code")
    val resulCode: String
)
