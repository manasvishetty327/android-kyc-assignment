package com.manasvishetty.androidkycassignment.data.local


import android.content.Context

class KycStorage(context: Context) {

    private val prefs =
        context.getSharedPreferences(
            "kyc_prefs",
            Context.MODE_PRIVATE
        )

    fun saveKyc(
        customerId: Int,
        selfiePath: String
    ) {
        prefs.edit()
            .putBoolean("verified_$customerId", true)
            .putString("selfie_$customerId", selfiePath)
            .apply()
    }

    fun isVerified(customerId: Int): Boolean {
        return prefs.getBoolean(
            "verified_$customerId",
            false
        )
    }

    fun getSelfiePath(customerId: Int): String? {
        return prefs.getString(
            "selfie_$customerId",
            null
        )
    }
}