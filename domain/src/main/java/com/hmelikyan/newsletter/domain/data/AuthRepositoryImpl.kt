package com.hmelikyan.newsletter.domain.data

import com.hmelikyan.newsletter.data.Data
import com.hmelikyan.newsletter.root.utils.SharedPreferencesHelper
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val data:Data,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : AuthRepository {
    override fun logIn():String {
        return "${data.getRootValue()} Asddsdssdsdds"
    }
}