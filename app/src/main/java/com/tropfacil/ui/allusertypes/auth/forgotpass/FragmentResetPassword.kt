package com.tropfacil.ui.allusertypes.auth.forgotpass

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tropfacil.base.BaseActivity

import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.ActivityForgetPasswordBinding
import com.tropfacil.databinding.ActivityResetPasswordBinding
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
            //it.hideKeyboard()
            if (binding.currentPasswordEt.text.toString().isEmpty()) {
                Toast.makeText(
                    this, "Please enter New Password",
                    Toast.LENGTH_SHORT
                ).show()
                binding.currentPasswordCl.requestFocus();
            } else if (binding.newPasswordEt.text.toString().isEmpty()) {
                Toast.makeText(
                    this, "Please enter Confirm password",
                    Toast.LENGTH_SHORT
                ).show()
                binding.newPasswordEt.requestFocus();
            } else if (binding.confirmNewPasswordEt.text.toString().isEmpty()) {
                Toast.makeText(
                    this, "Please enter OTP",
                    Toast.LENGTH_SHORT
                ).show()
                binding.confirmNewPasswordEt.requestFocus();
            } else {
                val updatePasswordRequest = UpdatePasswordRequest()
                updatePasswordRequest.login = "appud8787@gmail.com"
                updatePasswordRequest.newPassword = binding.currentPasswordEt.text.toString()
                updatePasswordRequest.password = binding.newPasswordEt.text.toString()
                viewModel.resetPassword(updatePasswordRequest)
                //viewModel.updatePassword(profilename,binding.currentPasswordCl.editPassword.text.toString(),binding.newPasswordEt.editPassword.text.toString())
                // flag = true
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel._updatePasswordStateFlow.collect { it ->
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

    private fun clickListeners() {
        /* binding.btnCreateAccount.setOnClickListener {
           //  this.onBackPressed()
         }*/
    }
}