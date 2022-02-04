package com.tropfacil.ui.allusertypes.auth.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.main.view.MainActivity
import com.tropfacil.ui.allusertypes.auth.Register
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        setContentView(R.layout.activity_login)
        actionBar?.isHideOnContentScrollEnabled
        initViewClickListeners()
        // get reference to button

    }

    private fun initViewClickListeners() {

        btn_sign_in.setOnClickListener {
           // MainActivity.start(this)
        }
        signup_txt.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            // start your next activity
            startActivity(intent)
        }

    }
}