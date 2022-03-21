package com.tropfacil.ui.nav.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.FragmentAccountSettingsBinding
import com.tropfacil.textCapSentences
import com.tropfacil.ui.nav.account.email.UpdateEmailFragment

class AccountSettingsFragment : BaseFragment() {
    lateinit var binding: FragmentAccountSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SetUI()

    }

    private fun SetUI() {
        binding.clTitleWithBack.headerTitleTv.text = textCapSentences(getString(R.string.lbl_account_settings))

        binding.clChangeEmail.setOnClickListener {
            navigateToUpdateEmailScreen()
        }

        binding.clChangePassword.setOnClickListener {

        }

        binding.clLogout.setOnClickListener {

        }

        binding.clSynchronization.setOnClickListener {

        }

        binding.syncNowTv.setOnClickListener {
        }
    }

    private fun navigateToUpdateEmailScreen() {
        val updateEmailFragment = UpdateEmailFragment()
        (this as BaseActivity).visitNewFragment(R.id.fragment_container, updateEmailFragment)
    }


}