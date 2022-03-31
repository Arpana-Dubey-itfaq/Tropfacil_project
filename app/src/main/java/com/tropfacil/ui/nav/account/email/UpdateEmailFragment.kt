package com.tropfacil.ui.nav.account.email

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.tropfacil.*
import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.FragmentUpdateEmailBinding
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.ui.nav.account.AccountSettingsViewModel
import com.tropfacil.utils.popups.SuccessOrFailurePopup
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class UpdateEmailFragment : BaseFragment() {
    lateinit var binding: FragmentUpdateEmailBinding
    private val viewModel by inject<AccountSettingsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentUpdateEmailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initObservers()

    }

    private fun initUI() {
        binding.clTitleWithBack.headerTitleTv.text =
            textCapSentences(getString(R.string.lbl_change_password))

        binding.clTitleWithBack.backIv.setOnClickListener {
            closeFragment()
        }
        binding.newEmailEt.doAfterTextChanged {
            enableOrDisableButton()
        }
        binding.passwordEt.doAfterTextChanged {
            enableOrDisableButton()
        }

        binding.btnSubmit.setOnClickListener {
            isValidForm()
        }

        binding.managePassIv.setOnClickListener {
            binding.passwordEt.apply {
                transformationMethod =
                    if (transformationMethod is PasswordTransformationMethod)
                        null //shows password
                    else
                        PasswordTransformationMethod() //hides password
            }
        }

    }


    private fun enableOrDisableButton() {
        val isEnabled = binding.newEmailEt.text.toString()
            .isNotEmpty() && binding.passwordEt.text.toString().isNotEmpty()
        binding.btnSubmit.isEnabled = isEnabled
        binding.btnSubmit.isClickable = isEnabled
        if (isEnabled) {
            binding.btnSubmit.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_green
            )
        } else {
            binding.btnSubmit.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_light_green
            )
        }

    }

    private fun isValidForm() {
        var isValid = true
        if (!isValidEmailId(binding.newEmailEt.text.toString())) {
            isValid = false
            binding.newEmailEt.error = null
            binding.tvErrorMsg.text = getString(R.string.str_please_enter_valid_email)
        }
/*

        if (!binding.passwordEt.isValidPassword(requireContext())) {
            isValid = false
            binding.passwordEt.error = null
            binding.tvErrorMsg.text = getString(R.string.str_please_enter_valid_password)
        }
*/
        if (isValid)
            callAPIForChangeEmail()

    }

    private fun callAPIForChangeEmail() {
        viewModel.updateEmail(binding.newEmailEt.text.toString())
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel._updateEmailStateFlow.collect { it ->
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
                        showSuccessMsg(getString(R.string.email_updated_successfully))
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun showSuccessMsg(successMsg: String) {
        SuccessOrFailurePopup.newInstance {
            onConfirm = {
                closeFragment()
            }
            message = successMsg
            successBtnName = getString(R.string.ok)
            isSuccessPopUp = 1
            cancellable = false
        }.show(childFragmentManager, SuccessOrFailurePopup.TAG)
    }

    private fun closeFragment() {
        closeAndResumePrevFrag(requireActivity(), null)
    }
    private fun isValidEmailId(emailId: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailId).matches()
    }

}