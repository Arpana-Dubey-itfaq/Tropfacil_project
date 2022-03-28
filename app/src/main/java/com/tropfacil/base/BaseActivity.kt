package com.tropfacil.base

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.tropfacil.R
import com.tropfacil.common.interfaces.ResumeFragmentListener
import com.tropfacil.message.view.WriteAMessageFragment
import com.tropfacil.ui.allusertypes.auth.signup.RegisterFragment
import com.tropfacil.ui.allusertypes.auth.signup.RegisterFragmentDirections
import com.tropfacil.ui.nav.account.AccountSettingsFragment
import com.tropfacil.ui.nav.account.password.UpdatePasswordFragment


open class BaseActivity : AppCompatActivity() {

    var resumeFragmentListener: ResumeFragmentListener? = null
    var bundle: Bundle? = null

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


    override fun onBackPressed() {
        handleBackEvent()
    }

    private fun handleBackEvent() {
        resumeFragmentListener?.onFragmentResume(bundle)
        var fragmentName = supportFragmentManager.findFragmentById(R.id.fragment_container)?.tag
        if (fragmentName == null) {
            finish()
        } else {
            when (fragmentName) {
                AccountSettingsFragment::class.java.name -> {
                    supportFragmentManager.popBackStack()
                }
                UpdatePasswordFragment::class.java.name -> {
                    supportFragmentManager.popBackStack()
                }
                RegisterFragment::class.java.name ->{
                   // findNavController().navigate(RegisterFragmentDirections.actionInitialFragmentToLoginFragment())


                }
                WriteAMessageFragment::class.java.name-> supportFragmentManager.popBackStack()
            }
        }
    }

    fun updateResumeFragment(resumeFragmentListener: ResumeFragmentListener) {
        this.resumeFragmentListener = resumeFragmentListener
    }


    fun visitNewFragment(rootLayout: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(rootLayout,
                fragment,
                fragment::class.java.name)
            .addToBackStack(null)
            .commit()
    }

}
