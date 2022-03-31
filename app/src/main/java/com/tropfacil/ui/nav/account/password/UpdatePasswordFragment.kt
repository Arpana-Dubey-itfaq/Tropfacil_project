package com.tropfacil.ui.nav.account.password

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.databinding.FragmentUpdatePasswordBinding
import com.tropfacil.isValidPassword
import com.tropfacil.model.ForgotPasswordRes
import com.tropfacil.model.UpdatePasswordRequest
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.textCapSentences
import com.tropfacil.ui.nav.account.AccountSettingsViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class UpdatePasswordFragment : BaseFragment() {

    lateinit var binding: FragmentUpdatePasswordBinding
    private val viewModel by inject<AccountSettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentUpdatePasswordBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onViewCreated(view, savedInstanceState)

        SetUI()
        initObservers()
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
                        closeFragment()
                    }
                    else -> {
                    }
                }
            }
        }
    }

    private fun closeFragment() {
        closeAndResumePrevFrag(requireActivity(),null)
    }

    private fun SetUI() {
        binding.clTitleWithBack.headerTitleTv.text =
            textCapSentences(getString(R.string.lbl_change_password))

        binding.currentPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.currentPasswordEt.isValidPassword(requireContext())
            }

        })
        binding.newPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.newPasswordEt.isValidPassword(requireContext())
            }

        })
        binding.confirmNewPasswordEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                isValidConfirmPassowrd()

            }

        })

        binding.submitCl.setOnClickListener {

            if (binding.currentPasswordEt.isValidPassword(requireContext()) &&
                binding.newPasswordEt.isValidPassword(requireContext()) &&
                binding.confirmNewPasswordEt.isValidPassword(requireContext()) &&
                isValidConfirmPassowrd()
            ) {
                //call Update Password API
                val updatePasswordRequest = UpdatePasswordRequest()
                updatePasswordRequest.newPassword= binding.newPasswordEt.text.toString()
                updatePasswordRequest.password = binding.currentPasswordEt.text.toString()
                viewModel.updatePassword(updatePasswordRequest)
            }
        }

        binding.manageConfirmPassIv.setOnClickListener {
            binding.confirmNewPasswordEt.apply {
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
        binding.manageNewPassIv.setOnClickListener {
            binding.newPasswordEt.apply {
                transformationMethod =
                    if (transformationMethod is PasswordTransformationMethod)
                        null //shows password
                    else
                        PasswordTransformationMethod() //hides password
            }
        }

        binding.clTitleWithBack.backIv.setOnClickListener {
            closeFragment()
        }

    }

    private fun isValidConfirmPassowrd(): Boolean {
        val newPassword = binding.newPasswordEt.text.toString().trim()
        val confirmPassword = binding.confirmNewPasswordEt.text.toString().trim()
        if (!newPassword.equals(confirmPassword, ignoreCase = false)) {
            binding.confirmNewPasswordEt.error = getString(R.string.str_confirm_password_not_match)
            return false
        }

        return true
    }

}