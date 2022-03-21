package com.tropfacil.ui.allusertypes.auth.forgotpass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.ActivityResetPasswordBinding

class FragmentResetPassword : BaseFragment() {
    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityResetPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
    }

    private fun clickListeners() {
        binding.btnCreateAccount.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}