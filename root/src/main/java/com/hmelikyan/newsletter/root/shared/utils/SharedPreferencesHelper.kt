package com.hmelikyan.newsletter.root.shared.utils

interface SharedPreferencesHelper {
    fun saveAuthToken(data:String)
    fun getAuthToken():String?
    fun saveDeviceId(data:String)
    fun getDeviceId():String?
    fun saveUserInfo(data:String)
    fun getUserInfo()
    fun saveFirebaseToken(data:String)
    fun getFirebaseToken():String?
}