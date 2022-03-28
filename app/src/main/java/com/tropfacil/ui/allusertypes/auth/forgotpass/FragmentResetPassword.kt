package com.tropfacil.ui.allusertypes.auth.forgotpass

import android.content.Intent
import android.os.Bundle
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
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.ui.allusertypes.auth.login.LoginFragment
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class FragmentResetPassword : BaseActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private val viewModel by inject<ResetPasswordViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initObservers()
        clickListeners()
    }


    private fun initListeners() {
        /* binding.ivClose.setOnClickListener {
             findNavController().popBackStack()
         }
 */


        binding.btnCreateAccount.setOnClickListener {
            //it.hideKeyboard()
            if (binding.etEmailUsername.getText().toString().trim().isEmpty()) {
                Toast.makeText(
                    this, "Please enter Username",
                    Toast.LENGTH_SHORT
                ).show()
                binding.etEmailUsername.requestFocus();
            } else {
               // viewModel.updatePassword(binding.etEmailUsername.text.toString(),binding.relPassword.editPassword.text.toString(),binding.relPassword1.editPassword.text.toString())
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

                        // showErrorMsg(it.exception.toString())
                    }
                    is SafeApiCall.SuccessForgot -> {
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
        binding.btnCreateAccount.setOnClickListener {
            this.onBackPressed()
        }
    }
}