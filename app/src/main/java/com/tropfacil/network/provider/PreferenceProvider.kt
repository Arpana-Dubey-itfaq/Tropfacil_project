package com.tropfacil.network.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


const val PREF_IS_USER_LOGGED_IN = "user_logged_in"
const val PREF_GUEST_USER = "guest_user"
const val PREF_GUEST_USER_RELAUNCH_HOME_SCREEN = "guest_user_relaunch_home_screen"
const val PREF_FIRST_TIME_APP_OPEN = "first_time_app_open"

const val PREF_IDENTIFIER_KEY = "identifier_kkey"
const val PREF_EMAIL = "email"
const val PREF_USERID = "user_id"
const val PREF_TOKEN = "user_token"
const val PREF_TOKEN_REFRESHER = "user_token_refresher"
const val PREF_ROLE_ID = "user_role_id"
const val PREF_PHONE_NUMBER = "user_phone_number"
const val PREF_TOKEN_EXPIRATION = "user_token_expiration"
const val PREF_PASSWORD = "user_password"
const val PREF_UUID = "uuid"

class PreferenceProvider(val context: Context) {
    private val appContext = context.applicationContext


    protected val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun clear() {
        preferences.edit().clear().apply()
    }

    fun putString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return preferences.getString(key, defaultValue).toString()
    }

    fun putInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }


    fun putLong(key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return preferences.getLong(key, defaultValue)
    }

    fun clearAll() {
        preferences.edit().clear().apply()
    }


   /* fun saveLoginDataToPref(data: LoginRes) {
        //TODO remove below 2 lines add it in setFCM token success response
        putBoolean(PREF_IS_USER_LOGGED_IN, true)
        putBoolean(PREF_GUEST_USER, false)

        putInt(PREF_USERID, data.response.userId)
        putString(PREF_TOKEN, data.response.token)
        putString(PREF_TOKEN_REFRESHER, data.response.tokenRefresher)
        putInt(PREF_ROLE_ID, data.response.roleId)
        if (data.response.phoneNumber != null)
            putString(PREF_PHONE_NUMBER, data.response.phoneNumber)
        putString(PREF_TOKEN_EXPIRATION, data.response.tokenExpiration)

    }

    fun saveTokenRefreshDataToPref(data: TokenRefreshResponse) {
        data.response?.userId?.let { putInt(PREF_USERID, it) }
        data.response?.token?.let { putString(PREF_TOKEN, it) }
        data.response?.tokenRefresher?.let { putString(PREF_TOKEN_REFRESHER, it) }
        data.response?.roleId?.let { putInt(PREF_ROLE_ID, it) }
        data.response?.phoneNumber?.let { putString(PREF_PHONE_NUMBER, it) }
        data.response?.tokenExpiration?.let { putString(PREF_TOKEN_EXPIRATION, it) }
    }

*/
}