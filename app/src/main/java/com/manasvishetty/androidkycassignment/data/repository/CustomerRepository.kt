package com.manasvishetty.androidkycassignment.data.repository

import com.manasvishetty.androidkycassignment.data.model.Customer
import com.manasvishetty.androidkycassignment.data.remote.RetrofitInstance
import com.manasvishetty.androidkycassignment.domain.repository.CustomerRepositoryInterface

import kotlin.random.Random

class CustomerRepository : CustomerRepositoryInterface {

    private val ifscCodes = listOf(
        "HDFC0CAGSBK",
        "SBIN0000001",
        "ICIC0000001",
        "PUNB0244200",
        "UTIB0000001"
    )
    val accountTypes = listOf(
        "Savings",
        "Current",
        "NRI"
    )

    override suspend fun getCustomers(): List<Customer> {
        return RetrofitInstance.api.getUsers().users.map { customer ->
            customer.copy(
                balance = Random.nextDouble(1000.0, 100000.0),
                ifsc = ifscCodes.random(),
                accountType = accountTypes[customer.id % accountTypes.size]
            )
        }
    }
}