package com.manasvishetty.androidkycassignment.data.model

data class Customer(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val image: String,
    val phone: String,
    val email: String,
    val birthDate: String,
    val address: Address,
    val bank: Bank,
    val ifsc: String = "",
    val balance: Double = 0.0,
    val isVerified: Boolean = false,
    val selfiePath: String? = null,
    val accountType: String
)