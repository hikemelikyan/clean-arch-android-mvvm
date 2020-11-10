package com.hmelikyan.newsletter.root.shared.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesHelperImpl
@Inject constructor(
    @ApplicationContext context: Context
) : SharedPreferencesHelper {

    companion object {
        private const val TOKEN = "Token"
        private const val FIREBASE_TOKEN = "FirebaseToken"
        private const val DEVICE_ID = "DeviceID"
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences("Configs", MODE_PRIVATE)

    override fun saveAuthToken(data: String) {
        preferences.edit(true) { putString(TOKEN, data) }
    }

    override fun getAuthToken(): String? {
        return preferences.getString(TOKEN, null)
    }

    override fun saveDeviceId(data: String) {
        preferences.edit(true) {
            putString(DEVICE_ID, data)
        }
    }

    override fun getDeviceId(): String? {
        return preferences.getString(DEVICE_ID, null)
    }

    override fun saveUserInfo(data: String) {
    }

    override fun getUserInfo() {

    }

    override fun saveFirebaseToken(data: String) {
        preferences.edit(true) {
            putString(FIREBASE_TOKEN, data)
        }
    }

    override fun getFirebaseToken(): String? {
        return preferences.getString(FIREBASE_TOKEN,null)
    }

}