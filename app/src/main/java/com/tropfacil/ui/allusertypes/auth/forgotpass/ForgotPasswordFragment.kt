package com.tropfacil.ui.allusertypes.auth.forgotpass

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.ActivityForgetPasswordBinding
import com.tropfacil.model.ForgotPasswordRes
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.utils.popups.SuccessOrFailurePopup
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject


class ForgotPasswordFragment : BaseFragment() {

    private lateinit var binding: ActivityForgetPasswordBinding
    private val viewModel by inject<ForgotPasswordViewModel>()
    private var flag = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
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
                    requireContext(), "Please enter Username",
                    Toast.LENGTH_SHORT
                ).show()
                binding.etEmailUsername.requestFocus();
            } else {
                viewModel.forgotPassword(binding.etEmailUsername.text.toString())
                flag = true
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel._forgotPasswordStateFlow.collect { it ->
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
                        findNavController().navigate(
                            ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToResetPasswordFragment()
                        )
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun showPopUp(messageRes: String?) {
        findNavController().navigate(
            ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToResetPasswordFragment()
        )
        /*   SuccessOrFailurePopup.newInstance {
               onConfirm =
                   {

                       findNavController().navigate(
                           ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToResetPasswordFragment()
                       )
                   }
               message = "Link sent successfully to your email.\nPlease check your email."
               successBtnName = getString(R.string.ok)
               isSuccessPopUp = 1
               cancellable = false
           }.show(childFragmentManager, SuccessOrFailurePopup.TAG)
   */
    }

    /* private fun isValidForm(): Boolean {
         var isValid = true
         val email = binding.etEmailUsername.text.toString()
         if (!isValidEmailId(email)) {
             isValid = false
             binding.tvErrEmailAddress.visible()
             binding.tvErrEmailAddress.text = getString(R.string.err_email_id)
         } else binding.tvErrEmailAddress.gone()

         return isValid
     }*/
}