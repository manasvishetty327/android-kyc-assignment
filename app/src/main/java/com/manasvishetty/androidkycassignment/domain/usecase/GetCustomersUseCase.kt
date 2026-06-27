package com.manasvishetty.androidkycassignment.domain.usecase

import com.manasvishetty.androidkycassignment.data.model.Customer
import com.manasvishetty.androidkycassignment.domain.repository.CustomerRepositoryInterface
import javax.inject.Inject

class GetCustomersUseCase @Inject constructor(
    private val repository: CustomerRepositoryInterface
) {
    suspend operator fun invoke(): List<Customer> {
        return repository.getCustomers()
    }
}