package com.manasvishetty.androidkycassignment.data.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.manasvishetty.androidkycassignment.data.model.Customer

class CustomerCache(context: Context) {

    private val prefs =
        context.getSharedPreferences("customer_cache", Context.MODE_PRIVATE)

    private val gson = Gson()

    companion object {
        private const val CUSTOMERS_KEY = "customers"
        private const val TIMESTAMP_KEY = "timestamp"
        private const val CACHE_DURATION = 60 * 60 * 1000 // 1 hour
    }

    fun saveCustomers(customers: List<Customer>) {
        val json = gson.toJson(customers)

        prefs.edit()
            .putString(CUSTOMERS_KEY, json)
            .putLong(TIMESTAMP_KEY, System.currentTimeMillis())
            .apply()
    }

    fun getCustomers(): List<Customer>? {
        val savedTime = prefs.getLong(TIMESTAMP_KEY, 0)

        if (System.currentTimeMillis() - savedTime > CACHE_DURATION) {
            return null
        }

        val json = prefs.getString(CUSTOMERS_KEY, null) ?: return null

        val type = object : TypeToken<List<Customer>>() {}.type

        return gson.fromJson(json, type)
    }

    fun clearCache() {
        prefs.edit().clear().apply()
    }
}