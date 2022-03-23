package com.tropfacil.ui.nav.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.databinding.FragmentProfileBinding
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.textCapSentences
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment() {

    lateinit var binding: FragmentProfileBinding
    private val viewModel by inject<ProfileSettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SetUI()
        initObservers()
    }

    private fun SetUI() {
        binding.clTitleWithBack.headerTitleTv.text =
            textCapSentences(getString(R.string.lbl_profile))

        binding.clTitleWithBack.backIv.setOnClickListener {
            closeAndResumePrevFrag(requireActivity(), null)
        }

        binding.submitCl.setOnClickListener {
              val prenom=binding.nameEt.text.toString()
            viewModel.updateUser(prenom)

        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel._updateUserStateFlow.collect { it ->
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

}