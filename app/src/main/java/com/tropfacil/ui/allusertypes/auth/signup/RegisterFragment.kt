package com.tropfacil.ui.allusertypes.auth.signup


import android.app.Activity
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
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
import com.tropfacil.databinding.ActivityRegisterBinding
import com.tropfacil.databinding.FragmentLoginBinding
import com.tropfacil.model.RegisterRequest
import com.tropfacil.network.request.LoginRequest
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.ui.allusertypes.auth.login.LoginFragmentDirections
import com.tropfacil.utils.popups.SuccessOrFailurePopup
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import org.koin.android.ext.android.inject

class RegisterFragment : BaseFragment() {
    //  private lateinit var binding: FragmentLoginBinding
    private val viewModel by inject<RegisterViewModel>()
    lateinit var binding: ActivityRegisterBinding
    var languages = arrayOf("Miss", "Mr")

    var spinner: Spinner? = null
    var textView_msg: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  initView()
        initListeners()
       /// setTextWatchers()
        initObservers()




    }

    private fun initObservers() {
       /* viewModel.showLoading.observe(viewLifecycleOwner, { showLoading ->
            binding.progressBar.visibility = if (showLoading) View.VISIBLE else View.GONE
        })
        viewModel.showError.observe(viewLifecycleOwner, {
            showErrorMsg(it)
        })*/




        lifecycleScope.launch {
            viewModel._registerStateFlow.collect { it ->
                when (it) {
                    is SafeApiCall.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        binding.progressBar.isVisible = false
                        showErrorMsg(it.exception)
                    }
                    is SafeApiCall.Success -> {
                        binding.progressBar.isVisible = false
                        it.data.message?.let { it1 -> showPopUp(it1) }
                    }
                    else -> {}
                }
            }
        }
    }


    private fun initListeners() {
        binding.signin.setOnClickListener {
            it.hideKeyboard()
           // findNavController().navigate(LoginFragmentDirections.actionRegisterManuallyFragmentToLoginFragment())
        }

    /*    binding.ivClose.setOnClickListener {
            it.hideKeyboard()
            findNavController().popBackStack()
        }
*/
        binding.btnSignIn.setOnClickListener {
           it.hideKeyboard()
         //   if (isValidForm()) {
                val registerReq = RegisterRequest(
                    binding.edtemail.text.toString(),
                    binding.usename.text.toString(),
                    binding.edtNom.text.toString(),
                    binding.edtpernom.text.toString(),
                    "2",
                    //binding.edtPassword.text.toString(),
                )
                viewModel.registerUser(registerReq)
           // }
        }
    }



   /* private fun enableDisableButton() {
        if (binding.edtEmail.text.toString().isNotEmpty()
            && binding.edtFirstName.text.toString().isNotEmpty()
            && binding.edtPhoneNumber.text.toString().isNotEmpty()
            && binding.edtPassword.text.toString().isNotEmpty()
        ) {
            binding.btnCreateAccount.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_button_black
            )
            binding.btnCreateAccount.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.btnCreateAccount.isEnabled = true
            binding.btnCreateAccount.isClickable = true
        } else {
            binding.btnCreateAccount.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_button_grey
            )
            binding.btnCreateAccount.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            binding.btnCreateAccount.isEnabled = false
            binding.btnCreateAccount.isClickable = false
        }
    }

*/
/*    private fun isValidForm(): Boolean {
        var isValid = true
        val email = binding.edtemail.text.toString()
        val firstName = binding.edtNom.text.toString()
        val phoneNumber = binding.edtPhoneNumber.text.toString()
        val password = binding.edtPassword.text.toString()

        if (!isValidEmailId(email)) {
            isValid = false
            binding.tvErrEmail.visible()
            binding.tvErrEmail.text = getString(R.string.err_email_id)
        } else binding.tvErrEmail.gone()

        if (TextUtils.isEmpty(firstName)) {
            isValid = false
            binding.tvErrFirstName.visible()
            binding.tvErrFirstName.text = getString(R.string.please_enter_first_name)
        } else binding.tvErrFirstName.gone()

        if (!isValidPhoneNumber(phoneNumber)) {
            isValid = false
            binding.tvErrPhoneNumber.visible()
            binding.tvErrPhoneNumber.text = getString(R.string.err_phone_number)
        } else binding.tvErrPhoneNumber.gone()

//        if (!isValidPassword(password)) {
//            isValid = false
//            binding.tvErrPassword.visible()
//            binding.tvErrPassword.text = getString(R.string.err_password)
//        } else binding.tvErrPassword.gone()

        if (TextUtils.isEmpty(password)) {
            isValid = false
            binding.tvErrPassword.visible()
            binding.tvErrPassword.text = getString(R.string.please_enter_password)
        } else binding.tvErrPassword.gone()
        return isValid
    }

    private fun initView() {
        binding.edtEmail.requestFocus()
        binding.tvLoginHere.paintFlags =
            binding.tvLoginHere.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }*/

    public fun showPopUp(popupMsg: String) {
        SuccessOrFailurePopup.newInstance {
            onConfirm = {
              //  findNavController().navigate(RegisterManuallyFragmentDirections.actionRegisterManuallyFragmentToVerificationCodeFragment())
            }
            message = popupMsg
            isSuccessPopUp = 1
            successBtnName = getString(R.string.verify)
        }.show(childFragmentManager, SuccessOrFailurePopup.TAG)

    }
}