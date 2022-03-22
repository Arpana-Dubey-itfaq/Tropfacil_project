package com.tropfacil.ui.nav.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.base.BaseFragment
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.databinding.FragmentAccountSettingsBinding
import com.tropfacil.textCapSentences
import com.tropfacil.ui.allusertypes.auth.login.LoginFragment
import com.tropfacil.ui.nav.account.email.UpdateEmailFragment
import com.tropfacil.ui.nav.account.password.UpdatePasswordFragment
import org.koin.android.ext.android.inject

class AccountSettingsFragment : BaseFragment() {
    lateinit var binding: FragmentAccountSettingsBinding
    private val viewModel by inject<AccountSettingsViewModel>()

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
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
     // getResponse of  logout APi here
        }
    }

    private fun SetUI() {
        binding.clTitleWithBack.headerTitleTv.text = textCapSentences(getString(R.string.lbl_account_settings))

        binding.clChangeEmail.setOnClickListener {
            navigateToUpdateEmailScreen()
        }

        binding.clChangePassword.setOnClickListener {
            navigateToUpdatePasswordScreen()
        }

        binding.clLogout.setOnClickListener {
           processLogout()

        }

        binding.clSynchronization.setOnClickListener {

        }

        binding.syncNowTv.setOnClickListener {
        }
    }

    private fun processLogout() {
        viewModel.logout()
        requireActivity().supportFragmentManager.apply {
            for (fragment in fragments) {
                beginTransaction().remove(fragment).commit()
            }
            popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        navigateToLoginScreen()
    }

    private fun navigateToLoginScreen() {
        val loginFragment = LoginFragment()
        (requireActivity() as BaseActivity).visitNewFragment(R.id.fragment_container, loginFragment)
    }
    private fun navigateToUpdateEmailScreen() {
        val updateEmailFragment = UpdateEmailFragment()
        (requireActivity() as BaseActivity).visitNewFragment(R.id.fragment_container, updateEmailFragment)
    }

  private fun navigateToUpdatePasswordScreen() {
        val updatePasswordFragment = UpdatePasswordFragment()
        (requireActivity() as BaseActivity).visitNewFragment(R.id.fragment_container, updatePasswordFragment)
    }


}