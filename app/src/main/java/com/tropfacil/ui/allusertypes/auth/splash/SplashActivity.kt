package com.tropfacil.ui.allusertypes.auth.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.Observer
import com.tropfacil.R


import com.tropfacil.base.BaseActivity
import com.tropfacil.data.provider.*
import com.tropfacil.main.view.MainActivity
import com.tropfacil.network.auth.account.RefreshTokenResponse
import com.tropfacil.network.service.PREF_IS_USER_LOGGED_IN
import com.tropfacil.ui.allusertypes.auth.welcome.Welcome_Screen
import org.koin.android.ext.android.inject


@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private val preferenceProvider by inject<PreferenceProvider>()

    private lateinit var viewModel: SplashViewModel
    private var orderId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // hideStatusBar()
        setContentView(R.layout.activity_splash)

        //  initViewModel()
        Handler(Looper.getMainLooper()).postDelayed({
            if (preferenceProvider.getBoolean(PREF_IS_USER_LOGGED_IN, false)) {
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Welcome_Screen.start(this)
            }
            finish()

        }, 4000)
    }

    private fun initViewModel() {
        viewModel.logoutLiveData.observe(this, Observer {
            Toast.makeText(
                this,
                getString(R.string.session_expired_logout),
                Toast.LENGTH_LONG
            ).show()
            // Paper.book().delete(CART_ITEMS)
            viewModel.clearAllPref()
            finish()
            recreate()
        })
        viewModel.refreshTokenLiveData.observe(this, Observer {
            if (it == null)
                return@Observer
            saveRefreshResponse(it)
        })
    }


    private fun saveRefreshResponse(
        response: RefreshTokenResponse?
    ) {
        if (response != null) {
            if (response.payload != null) {
                val refreshTokenPayload = response.payload
                refreshTokenPayload.userInfo?.accountType?.let {
                    //   navigateToProperHomePage(it)
                }
            }
        }
    }


    /*  private fun navigateToProperHomePage(userType: Int) {
          when (userType) {
              AccountTypes.RegularUserType.value -> {
                  HomeActivity.start(this, orderId)
              }
              AccountTypes.HealthProfessionalType.value -> {
                  HealthProfHomeActivity.start(this, orderId)
              }
              AccountTypes.DeliveryManType.value -> {
                  DeliveryHomeActivity.start(this)
              }
          }
          finish()
      }*/

    /*  private fun updateTokenExpirationDateIfExpired() {
          var tokenExpDate: String? = null
          var refreshTokenExpDate: String? = null
          val tokenDate = preferenceProvider.getString(PREF_USER_TOKEN_EXPIRATION, "")
          val refreshTokenDate =
              preferenceProvider.getString(PREF_USER_REFRESH_TOKEN_EXPIRATION, "")

          if (tokenDate.isNotEmpty()) {
              tokenExpDate = getDateInMinusTenMin(tokenDate)
          }

          if (refreshTokenDate.isNotEmpty()) {
              refreshTokenExpDate = getDateInMinusTenMin(refreshTokenDate)
          }

          if (!tokenExpDate.isNullOrEmpty() && !refreshTokenExpDate.isNullOrEmpty()) {
              val isTokenValid = compareTokenExpirationDates(tokenExpDate)
              val isRefreshTokenValid =
                  compareTokenExpirationDates(refreshTokenExpDate)
              if (!isRefreshTokenValid) {
                  callLogoutAsync()
              } else if (!isTokenValid) {
                  callRefreshTokenAsync()
              }
          }
      }

      private fun callRefreshTokenAsync() {
          val refreshTokenRequest = RegenerateTokenRequest()
          refreshTokenRequest.userId = preferenceProvider.getString(PREF_USER_ID, "")
          refreshTokenRequest.token = preferenceProvider.getString(PREF_USER_TOKEN, "")
          refreshTokenRequest.refreshToken =
              preferenceProvider.getString(PREF_USER_REFRESH_TOKEN, "")
          refreshTokenRequest.device = DevicePlatforms.Android.value
          refreshTokenRequest.refreshTokenExpiration =
              preferenceProvider.getString(PREF_USER_REFRESH_TOKEN_EXPIRATION, "")
          refreshTokenRequest.userLoginRecordID =
              preferenceProvider.getInt(PREF_USER_LOGIN_ID, 0)
          refreshTokenRequest.tokenExpiration =
              preferenceProvider.getString(PREF_USER_TOKEN_EXPIRATION, "")
          refreshTokenRequest.dateCreated =
              preferenceProvider.getString(PREF_USER_DATE_CREATED, "")
          refreshTokenRequest.isActive = true
          refreshTokenRequest.accountType = preferenceProvider.getInt(PREF_USER_ACCOUNT_TYPE, 0)

          viewModel.callRefreshTokenApi(refreshTokenRequest)
      }

      private fun callLogoutAsync() {
          val logoutRequest = LogoutRequest(
              userId = preferenceProvider.getString(PREF_USER_ID, ""),
              devicePlatform = DevicePlatforms.Android.value,
              authToken = preferenceProvider.getString(PREF_USER_TOKEN, ""),
              //deviceFCMToken = preferenceProvider.getString(PREF_USER_FCM_TOKEN, "")
          )
          viewModel.callLogoutApi(logoutRequest)
      }*/


}