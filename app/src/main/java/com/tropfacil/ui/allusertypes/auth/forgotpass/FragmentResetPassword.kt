package com.tropfacil.ui.allusertypes.auth.forgotpass

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tropfacil.R
import com.tropfacil.base.BaseActivity

import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.ActivityForgetPasswordBinding
import com.tropfacil.databinding.ActivityResetPasswordBinding
import com.tropfacil.isValidPassword
import com.tropfacil.model.ResetPasswordRequest
import com.tropfacil.model.UpdatePasswordRequest
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.ui.allusertypes.auth.login.LoginFragment
import kotlinx.android.synthetic.main.activity_account_settings.view.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class FragmentResetPassword : BaseActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private val viewModel by inject<ResetPasswordViewModel>()

    //val profileName = TODO()
    private lateinit var profilename: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profilename = intent.getStringExtra("Email").toString()
        initListeners()
        initObservers()
        clickListeners()
    }


    private fun initListeners() {
        binding.currentPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                isValidConfirmPassowrd()

            }

        })
        binding.newPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.newPasswordEt.isValidPassword(this@FragmentResetPassword)
            }

        })
        /* binding.ivClose.setOnClickListener {
             findNavController().popBackStack()
         }
 */
        binding.includeLayout1.backIv.setOnClickListener {
            this.onBackPressed()
        }

        binding.manageConfirmPassIv.setOnClickListener {
            binding.newPasswordEt.apply {
                transformationMethod =
                    if (transformationMethod is PasswordTransformationMethod)
                        null //shows password
                    else
                        PasswordTransformationMethod() //hides password
            }
        }
        binding.manageCurrentPassIv.setOnClickListener {
            binding.currentPasswordEt.apply {
                transformationMethod =
                    if (transformationMethod is PasswordTransformationMethod)
                        null //shows password
                    else
                        PasswordTransformationMethod() //hides password
            }
        }

        binding.btnCreateAccount.setOnClickListener {

            if (binding.currentPasswordEt.isValidPassword(this) &&
                binding.newPasswordEt.isValidPassword(this) &&
                binding.confirmNewPasswordEt.isValidPassword(this)
               // isValidConfirmPassowrd()
            ) {
                //call Update Password API
                val updatePasswordRequest = ResetPasswordRequest()
                updatePasswordRequest.login=profilename
                updatePasswordRequest.confirmPassword= binding.newPasswordEt.text.toString()
                updatePasswordRequest.newPassword = binding.currentPasswordEt.text.toString()
                viewModel.resetPassword(binding.currentPasswordEt.text.toString(),binding.confirmNewPasswordEt.text.toString(),profilename)
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel._updatePasswordStateFlow1.collect { it ->
                when (it) {
                    is SafeApiCall.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        binding.progressBar.isVisible = false
                        showErrorMsg(it.exception.toString())
                    }
                    is SafeApiCall.Success -> {
                        binding.progressBar.isVisible = false
                        startActivity(Intent(this@FragmentResetPassword, LoginFragment::class.java))

                    }
                    else -> {
                    }
                }
            }
        }
    }
    private fun isValidConfirmPassowrd(): Boolean {
        val newPassword = binding.currentPasswordEt.text.toString().trim()
        val confirmPassword = binding.newPasswordEt.text.toString().trim()
        if (!newPassword.equals(confirmPassword, ignoreCase = false)) {
            binding.newPasswordEt.error = getString(R.string.str_confirm_password_not_match)
            return false
        }

        return true
    }

    private fun clickListeners() {
        /* binding.btnCreateAccount.setOnClickListener {
           //  this.onBackPressed()
         }*/
    }
}