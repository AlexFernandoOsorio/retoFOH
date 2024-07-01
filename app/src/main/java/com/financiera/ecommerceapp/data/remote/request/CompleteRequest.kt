package com.financiera.ecommerceapp.data.remote.request


import com.google.gson.annotations.SerializedName

data class CompleteRequest(
    @SerializedName("dni")
    val dni: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("operation_date")
    val operationDate: String
)
