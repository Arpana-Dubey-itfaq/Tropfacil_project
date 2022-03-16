package com.tropfacil.ui.allusertypes.auth.login


import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.tropfacil.R

import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.ActivityLoginBinding
import com.tropfacil.databinding.FragmentLoginBinding
import com.tropfacil.main.view.MainActivity
import com.tropfacil.network.request.LoginRequest
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.search.view.SearchActivity
import kotlinx.coroutines.flow.collect
import org.json.JSONException
import org.json.JSONObject
import org.koin.android.ext.android.inject

class LoginFragment : BaseFragment() {
  //  private lateinit var binding: FragmentLoginBinding
    private val viewModel by inject<LoginViewModel>()
    lateinit var binding: ActivityLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // initView()
        initListeners()
        initObservers()
        //   callbackManager = CallbackManager.Factory.create()

    }


    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel._loginStateFlow.collect {
                when (it) {
                    is SafeApiCall.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        binding.progressBar.isVisible = false
                        //showErrorMsg(it.exception)
                    }
                    is SafeApiCall.Success -> {
                        binding.progressBar.isVisible = false
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())

                        //viewModel.syncGuestItems(getUUID())
                    }
                    else -> {

                    }
                }
            }
        }


    }


    private fun initListeners() {
        /*  binding.tvCreateAccount.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterManuallyFragment())
        }*/

        /*       binding.ivClose.setOnClickListener {
            it.hideKeyboard()
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment)
            val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount
            if (backStackEntryCount != 0) {
                findNavController().popBackStack()
            } else {
                activity?.finishAffinity()
            }
        }
        binding.textB.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }*/

        binding.signupTxt.setOnClickListener {
            //it.hideKeyboard()
            findNavController().navigate(LoginFragmentDirections.actionRegsterFragment())
        }
        binding.includeLayout.forgotPass.setOnClickListener {
            //it.hideKeyboard()
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }

        binding.btnSignIn.setOnClickListener {
            //  it.hideKeyboard()
            if (isValidForm()) {
                val loginRequest = LoginRequest(
                    loginName = binding.etEmailUsername.text.toString(),
                    password = binding.relPassword.editPassword.text.toString(),
                    token = "v6yRZ5gsSPY0dS9imbUUySYuTdPGn5Wo"
                )

                viewModel.loginUser(loginRequest)
                startActivity(Intent(requireContext(), MainActivity::class.java))

            }
        }

        /* binding.imgPassword.setOnClickListener {
            showHidePassword(binding.edtPassword, binding.imgPassword)
        }

        binding.edtEmail.doAfterTextChanged {
            enableDisableButton()
        }

        binding.edtPassword.doAfterTextChanged {
            enableDisableButton()
        }
        binding.btnFacebookLogin.setOnClickListener {
            onFacebookLoginClicked()
        }

        binding.btnGoogleLogin.setOnClickListener {
            signIn()
        }

        binding.tvLoginTermsConditions.setOnClickListener {
            openWebPage(requireContext(),requireContext().getString(R.string.terms_and_conditions_link))
        }

        binding.tvLoginPrivacyPolicy.setOnClickListener {
            openWebPage(requireContext(),requireContext().getString(R.string.privacy_policy_link))
        }
    }*/


    }


/*    private fun enableDisableButton() {
        if (binding.et_email_username.text.toString().isNotEmpty() && binding.edit_password.text.toString()
                .isNotEmpty()
        ) {
            binding.btn_sign_in.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_button_black
            )
            binding.btnLogin.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.btnLogin.isEnabled = true
            binding.btnLogin.isClickable = true
        } else {
            binding.btnLogin.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_button_grey
            )
            binding.btnLogin.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            binding.btnLogin.isEnabled = false
            binding.btnLogin.isClickable = false
        }
    }*/

    private fun isValidForm(): Boolean {
        var isValid = true
        val email = binding.etEmailUsername.text.toString()
        val password = binding.relPassword.editPassword.text.toString()

       /* if (!isValidEmailId(email)) {
            isValid = false

            binding.tvPassword.text = getString(R.string.please_enter_password)
        } else binding.etEmailUsername.toString()
*/
        /*if (!isValidPassword(password)) {
            isValid = false
            binding.tvErrPassword.visible()
            binding.tvErrPassword.text = getString(R.string.err_password)
        } else binding.tvErrPassword.gone()*/
        if (TextUtils.isEmpty(password)) {
            isValid = false
          //  binding.relPassword.editPassword.visible()
            binding.tvPassword.text = getString(R.string.please_enter_password)
        } else binding.relPassword.editPassword.toString()

        return isValid
    }


}