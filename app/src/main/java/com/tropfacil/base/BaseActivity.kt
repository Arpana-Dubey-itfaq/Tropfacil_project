package com.tropfacil.base

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tropfacil.network.request.LogoutRequest
import com.tropfacil.ui.allusertypes.auth.login.LoginActivity
import com.tropfacil.utils.DevicePlatforms
import com.tropfacil.utils.popups.FailurePopup

import io.paperdb.Paper

import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

open class BaseActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
   // private val viewModelFactory: AccountUnVerifiedViewModelFactory by instance<AccountUnVerifiedViewModelFactory>()
    private lateinit var viewModel: AccountUnVerifiedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateStatusBarColor(ContextCompat.getColor(this, android.R.color.white))
        Paper.init(this)

    }

    /** Hide the status bar*/
    public fun hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    fun updateStatusBarColor(colorResource: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = colorResource
        }
    }



}
