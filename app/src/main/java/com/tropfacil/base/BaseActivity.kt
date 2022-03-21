package com.tropfacil.base

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.tropfacil.R
import com.tropfacil.utils.DevicePlatforms
import com.tropfacil.utils.popups.FailurePopup

import io.paperdb.Paper

import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateStatusBarColor(ContextCompat.getColor(this, android.R.color.white))
    }

    /** Hide the status bar*/
    fun hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    fun updateStatusBarColor(colorResource: Int) {
        window.statusBarColor = colorResource
    }

    /**
     * Handle a resolved activity from the Google Pay payment sheet.
     *
     * @param requestCode Request code originally supplied to AutoResolveHelper in requestPayment().
     * @param resultCode Result code returned by the Google Pay API.
     * @param data Intent from the Google Pay API containing payment or error data.
     * @see [Getting a result
     * from an Activity](https://developer.android.com/training/basics/intents/result)
     */


}
