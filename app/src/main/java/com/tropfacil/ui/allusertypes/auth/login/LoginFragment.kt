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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController

import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.tropfacil.AuthActivity
import com.tropfacil.R
import com.tropfacil.base.BaseActivity

import com.tropfacil.base.BaseFragment
import com.tropfacil.common.interfaces.ResumeFragmentListener
import com.tropfacil.databinding.ActivityLoginBinding
import com.tropfacil.databinding.ActivityRateBinding
import com.tropfacil.databinding.FragmentLoginBinding
import com.tropfacil.main.view.MainActivity
import com.tropfacil.network.request.LoginRequest
import com.tropfacil.network.service.PREF_IS_USER_LOGGED_IN
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.search.view.SearchActivity
import com.tropfacil.ui.allusertypes.auth.forgotpass.ForgotPasswordFragment
import com.tropfacil.ui.allusertypes.auth.signup.RegisterFragment
import com.tropfacil.utils.gone
import kotlinx.coroutines.flow.collect
import org.json.JSONException
import org.json.JSONObject
import org.koin.android.ext.android.inject

class LoginFragment : BaseActivity(), ResumeFragmentListener {
    //  private lateinit var binding: FragmentLoginBinding
    private val viewModel by inject<LoginViewModel>()
    lateinit var binding: ActivityLoginBinding
    private lateinit var email: String
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var password: String
    private var isLoginSuccess = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        initObservers()

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
                        showErrorMsg(it.exception.toString())
                    }
                    is SafeApiCall.SuccessLogin -> {
                        binding.progressBar.isVisible = false
                        if (binding.includeLayout.imageView3.isChecked)
                            viewModel.stayLogin(true)
                        else viewModel.stayLogin(false)
                        startActivity(Intent(this@LoginFragment, MainActivity::class.java))
                    }
                    else -> {

                    }
                }
            }
        }


    }


    private fun initListeners() {
        binding.includeLayout1.backIv.gone()
        binding.relPassword.showPassBtn.setOnClickListener {
            showHidePassword(binding.relPassword.editPassword, binding.relPassword.showPassBtn)
        }

        binding.signupTxt.setOnClickListener {
            val intent = Intent(this, RegisterFragment::class.java)
            this.startActivity(intent)
            //it.hideKeyboard()
        }
        binding.includeLayout.forgotPass.setOnClickListener {
            startActivity(Intent(this@LoginFragment, ForgotPasswordFragment::class.java))
        }

        binding.includeLayout.imageView3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                binding.includeLayout.imageView3.setBackgroundResource(R.drawable.check_1)
            else binding.includeLayout.imageView3.setBackgroundResource(R.drawable.uncheck)

        }
        email = binding.etEmailUsername.text.toString()
        password = binding.relPassword.editPassword.text.toString()
        binding.btnSignIn.setOnClickListener {
            //  it.hideKeyboard()
            if (binding.etEmailUsername.text.toString().trim().isEmpty()) {
                Toast.makeText(
                    this, "Please enter username",
                    Toast.LENGTH_SHORT
                ).show()
                binding.etEmailUsername.requestFocus();
            } else if (binding.relPassword.editPassword.text.toString().trim().isEmpty()) {
                Toast.makeText(
                    this, "Please enter password",
                    Toast.LENGTH_SHORT
                ).show()
                binding.relPassword.editPassword.requestFocus();
            } else {
                //Call your API/function here
                val loginRequest = LoginRequest(
                    loginName = binding.etEmailUsername.text.toString(),
                    password = binding.relPassword.editPassword.text.toString()
                )

                viewModel.loginUser(loginRequest)
            }
        }

    }
    fun validateEmail(): Boolean {
        var isValid = true
        if (TextUtils.isEmpty(email)) {
            isValid = false

            Toast.makeText(
                this, "Valid email address",
                Toast.LENGTH_SHORT
            ).show()
        } /*else {
        isValid = false

        Toast.makeText(
            requireContext(), "Invalid email address",
            Toast.LENGTH_SHORT
        ).show()
    }*/
        if (TextUtils.isEmpty(password)) {
            isValid = false
            Toast.makeText(
                this, "Please enter password",
                Toast.LENGTH_SHORT
            ).show()
        }

        return isValid
    }

    override fun onFragmentResume(bundle: Bundle?) {

    }

}

