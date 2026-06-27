package com.manasvishetty.androidkycassignment.domain.repository

import com.manasvishetty.androidkycassignment.data.model.Customer

interface CustomerRepositoryInterface {
    suspend fun getCustomers(): List<Customer>
}