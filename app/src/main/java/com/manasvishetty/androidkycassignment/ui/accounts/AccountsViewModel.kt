package com.manasvishetty.androidkycassignment.ui.accounts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.manasvishetty.androidkycassignment.data.local.CustomerCache
import com.manasvishetty.androidkycassignment.data.local.KycStorage
import com.manasvishetty.androidkycassignment.data.model.Customer
import com.manasvishetty.androidkycassignment.domain.usecase.GetCustomersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    application: Application,
    private val getCustomersUseCase: GetCustomersUseCase
) : AndroidViewModel(application) {

    private val storage = KycStorage(application)
    private val customerCache = CustomerCache(application)

    private val pageSize = 10

    private val _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> = _currentPage

    fun nextPage(totalItems: Int) {
        if (_currentPage.value * pageSize < totalItems) {
            _currentPage.value++
        }
    }

    fun previousPage() {
        if (_currentPage.value > 1) {
            _currentPage.value--
        }
    }

    fun getPageSize() = pageSize

    private val _customers =
        MutableStateFlow<List<Customer>>(emptyList())
    val customers: StateFlow<List<Customer>> = _customers

    private val _isLoading =
        MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error =
        MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchCustomers()
    }

    private fun fetchCustomers() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val cachedCustomers = customerCache.getCustomers()

                val apiCustomers =
                    cachedCustomers ?: getCustomersUseCase().also {
                        customerCache.saveCustomers(it)
                    }

                _customers.value = apiCustomers.map { customer ->
                    val verified = storage.isVerified(customer.id)
                    val savedSelfie = storage.getSelfiePath(customer.id)

                    customer.copy(
                        isVerified = verified,
                        image = savedSelfie ?: customer.image
                    )
                }

                _isLoading.value = false

            } catch (e: Exception) {
                _error.value = "Failed to load customers"
                _isLoading.value = false
            }
        }
    }

    fun verifyCustomer(
        customerId: Int,
        selfiePath: String
    ) {
        storage.saveKyc(
            customerId = customerId,
            selfiePath = selfiePath
        )

        _customers.value = _customers.value.map { customer ->
            if (customer.id == customerId) {
                customer.copy(
                    isVerified = true,
                    image = selfiePath
                )
            } else {
                customer
            }
        }
    }
}