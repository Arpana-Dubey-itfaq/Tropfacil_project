package com.tropfacil.data.provider
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.tropfacil.model.Login_resoponse

import com.tropfacil.network.auth.account.RefreshTokenResponse
import com.tropfacil.network.auth.register.AllRegisterResponse


const val PREF_USER_ID = "pf"
const val PREF_USER_IDNEW = "id"
const val PREF_USER_MOBILE = "userMobile"
const val PREF_USER_PASSWORD = "userPassword"
const val PREF_USER_NAME = "username"
const val PREF_USER_FIRST_NAME = "nom"
const val PREF_USER_LAST_NAME = "prenom"
const val PREF_USER_TOKEN = "token"
const val PREF_USER_TOKEN_EXPIRATION = "tokenExpiration"
const val PREF_USER_REFRESH_TOKEN = "refreshToken"
const val PREF_USER_REFRESH_TOKEN_EXPIRATION = "refreshTokenExpiration"
const val PREF_USER_FCM_TOKEN = "fcmToken"
const val PREF_USER_LOGIN_ID = "user_login_id"
const val PREF_USER_DATE_CREATED = "date_debut"
const val PREF_USER_DATE_FIN = "date_fin"
const val PREF_USER_METHOD_DELIVERY = "user_method_delivery"

const val PREF_CUSTOMER_USER_IS_LOGGED_IN = "isCustomerLoggedIn"
const val PREF_HEALTH_PROF_USER_IS_LOGGED_IN = "isHealthProfLoggedIn"
const val PREF_DELIVERY_MAN_USER_IS_LOGGED_IN = "isDeliveryManLoggedIn"

const val PREF_SAVE_TOGGLE_DELIVERY = "save_toggle_delivery"
const val PREF_IS_ORDER_ACCEPTED = "is_order_already_accepted"
const val PREF_IS_SEARCH_FROM_HOME= "is_search_from_home"
const val PREF_IS_SEARCH_FROM_HOME_LENGTH= "is_search_from_home_length"

class PreferenceProvider(val context: Context) {
    private val appContext = context.applicationContext

    protected val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun clearAllPref() {
        preferences.edit().clear().apply()
    }

    fun putString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }
 fun getUserToken(): String? {
        return preferences.getString(PREF_USER_TOKEN, "")
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

    fun getBooleanDefaultFalse(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun getBooleanDefaultTrue(key: String): Boolean {
        return preferences.getBoolean(key, true)
    }

    fun putLong(key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return preferences.getLong(key, defaultValue)
    }
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }
    fun saveLoginDataToPref(data: Login_resoponse) {
      /*  "success": "true",
        "pf": "tropfacile",
        "nom": "ITFAQ",
        "prenom": "Itfaq",
        "date_debut": 1612134000,
        "date_fin": null,
        "id": "20476",
        "token": "089C2CF872282728D7E501D5CAC2AB59839E"*/
        //TODO remove below 2 lines add it in setFCM token success response
       // putBoolean(PREF_USER_ID, true)
       // putBoolean(PREF_GUEST_USER, false)

        putString(PREF_USER_IDNEW, data.id)
        putString(PREF_USER_TOKEN, data.token)
        putString(PREF_USER_ID, data.pf)
        putString(PREF_USER_FIRST_NAME, data.nom)
        putString(PREF_USER_LAST_NAME,data.prenom)
        putString(PREF_USER_NAME,data.username?:"")


    }
    /*fun saveUserInfo(response: Login_resoponse) {
        // check if the account cred is correct and the account is verified.
        if (response.status == 200) {
            response.payload?.userInfo?.userId?.let { putString(PREF_USER_ID, it) }
            response.payload?.userInfo?.email?.let { putString(PREF_USER_EMAIL, it) }
            response.payload?.userInfo?.mobileNumber?.let { putString(PREF_USER_MOBILE, it) }
            response.payload?.userInfo?.firstName?.let { putString(PREF_USER_FIRST_NAME, it) }
            response.payload?.userInfo?.lastName?.let { putString(PREF_USER_LAST_NAME, it) }
            response.payload?.tokens?.userLoginRecordID?.let { putInt(PREF_USER_LOGIN_ID, it) }
            response.payload?.tokens?.token?.let { putString(PREF_USER_TOKEN, it) }
            response.payload?.tokens?.tokenExpiration?.let {
                putString(
                    PREF_USER_TOKEN_EXPIRATION,
                    it
                )
            }
            response.payload?.tokens?.refreshToken?.let { putString(PREF_USER_REFRESH_TOKEN, it) }
            response.payload?.tokens?.refreshTokenExpiration?.let {
                putString(
                    PREF_USER_REFRESH_TOKEN_EXPIRATION,
                    it
                )
            }
            response.payload?.userInfo?.methodDelivery?.let {
                putInt(PREF_USER_METHOD_DELIVERY, it)
            }

            response.payload?.userInfo?.accountType?.let {
                putInt(PREF_USER_ACCOUNT_TYPE, it)
                when (it) {
                    0 -> {
                        putBoolean(PREF_CUSTOMER_USER_IS_LOGGED_IN, true)
                        putBoolean(PREF_HEALTH_PROF_USER_IS_LOGGED_IN, false)
                        putBoolean(PREF_DELIVERY_MAN_USER_IS_LOGGED_IN, false)
                    }
                    1 -> {
                        putBoolean(PREF_HEALTH_PROF_USER_IS_LOGGED_IN, true)
                        putBoolean(PREF_CUSTOMER_USER_IS_LOGGED_IN, false)
                        putBoolean(PREF_DELIVERY_MAN_USER_IS_LOGGED_IN, false)
                    }
                    2 -> {
                        putBoolean(PREF_CUSTOMER_USER_IS_LOGGED_IN, false)
                        putBoolean(PREF_HEALTH_PROF_USER_IS_LOGGED_IN, false)
                        putBoolean(PREF_DELIVERY_MAN_USER_IS_LOGGED_IN, true)

                    }
                    else -> {
                        putBoolean(PREF_CUSTOMER_USER_IS_LOGGED_IN, true)
                        putBoolean(PREF_HEALTH_PROF_USER_IS_LOGGED_IN, false)
                        putBoolean(PREF_DELIVERY_MAN_USER_IS_LOGGED_IN, false)
                    }
                }
            }
            response.payload?.tokens?.dateCreated?.let { putString(PREF_USER_DATE_CREATED, it) }
        }
    }
*/
   /* fun saveRegisterInfo(registerResponse: AllRegisterResponse) {
        if (registerResponse.statusCode == 200) {
            registerResponse.payload?.email?.let {
                putString(PREF_USER_EMAIL, it)
            }
            registerResponse.payload?.userId?.let {
                putString(PREF_USER_ID, it)
            }
            registerResponse.payload?.mobileNumber?.let {
                putString(PREF_USER_MOBILE, it)
            }
        }
    }
*/

   /* fun saveRefreshTokenUserInfo(response: RefreshTokenResponse) {
        // check if the account cred is correct and the account is verified.
        if (response.statusCode == 200) {
            response.payload?.userInfo?.userId?.let { putString(PREF_USER_ID, it) }
            response.payload?.userInfo?.email?.let { putString(PREF_USER_EMAIL, it) }
            response.payload?.userInfo?.mobileNumber?.let { putString(PREF_USER_MOBILE, it) }
            response.payload?.userInfo?.firstName?.let { putString(PREF_USER_FIRST_NAME, it) }
            response.payload?.userInfo?.lastName?.let { putString(PREF_USER_LAST_NAME, it) }
            response.payload?.tokens?.userLoginRecordID?.let { putInt(PREF_USER_LOGIN_ID, it) }
            response.payload?.tokens?.token?.let { putString(PREF_USER_TOKEN, it) }
            response.payload?.tokens?.tokenExpiration?.let {
                putString(
                    PREF_USER_TOKEN_EXPIRATION,it
                )
            }
            response.payload?.tokens?.refreshToken?.let { putString(PREF_USER_REFRESH_TOKEN, it) }
            response.payload?.tokens?.refreshTokenExpiration?.let {
                putString(
                    PREF_USER_REFRESH_TOKEN_EXPIRATION,
                    it
                )
            }
            response.payload?.userInfo?.methodDelivery?.let {
                putInt(PREF_USER_METHOD_DELIVERY, it)
            }

            response.payload?.userInfo?.accountType?.let {
                putInt(PREF_USER_ACCOUNT_TYPE, it)
                when (it) {
                    0 -> {
                        putBoolean(PREF_CUSTOMER_USER_IS_LOGGED_IN, true)
                        putBoolean(PREF_HEALTH_PROF_USER_IS_LOGGED_IN, false)
                        putBoolean(PREF_DELIVERY_MAN_USER_IS_LOGGED_IN, false)
                    }
                    1 -> {
                        putBoolean(PREF_HEALTH_PROF_USER_IS_LOGGED_IN, true)
                        putBoolean(PREF_CUSTOMER_USER_IS_LOGGED_IN, false)
                        putBoolean(PREF_DELIVERY_MAN_USER_IS_LOGGED_IN, false)
                    }
                    2 -> {
                        putBoolean(PREF_CUSTOMER_USER_IS_LOGGED_IN, false)
                        putBoolean(PREF_HEALTH_PROF_USER_IS_LOGGED_IN, false)
                        putBoolean(PREF_DELIVERY_MAN_USER_IS_LOGGED_IN, true)
                    }
                    else -> {
                        putBoolean(PREF_CUSTOMER_USER_IS_LOGGED_IN, true)
                        putBoolean(PREF_HEALTH_PROF_USER_IS_LOGGED_IN, false)
                        putBoolean(PREF_DELIVERY_MAN_USER_IS_LOGGED_IN, false)
                    }
                }
            }
            response.payload?.tokens?.dateCreated?.let { putString(PREF_USER_DATE_CREATED, it) }
        }
    }*/

}