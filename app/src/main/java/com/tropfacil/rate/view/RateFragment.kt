package com.tropfacil.rate.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.databinding.ActivityRateBinding
import com.tropfacil.databinding.FragmentWriteAMessageBinding
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.rate.viewmodel.RateCourseViewModel
import com.tropfacil.ui.nav.profile.ProfileSettingsViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject


class RateFragment : BaseFragment() {
    lateinit var binding: ActivityRateBinding

    private val viewModel by inject<RateCourseViewModel>()
    private val preferenceProvider by inject<PreferenceProvider>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityRateBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()

    }

    private fun initObservers() {
        viewModel.SendRating()
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
        lifecycleScope.launchWhenStarted {
            viewModel._profilePictureStateFlow.collect { it ->
                when (it) {
                    is SafeApiCall.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        binding.progressBar.isVisible = false
                        showErrorMsg(it.exception.toString())
                    }
                    is SafeApiCall.SuccessResponseBody -> {
                        binding.progressBar.isVisible = false
                       // displayImages(it.data)
                    }
                    else -> {
                    }
                }
            }
        }

    }

    private fun initListeners() {
       /* binding.etSubject.doAfterTextChanged {
            enableOrDisableButton()
        }
        binding.etWriteAQuestion.doAfterTextChanged {
            enableOrDisableButton()
        }*/
        binding.cardSubmit.setOnClickListener {
            //TODO Handle api calling
            closeFragment()
        }
    }


    private fun closeFragment() {
        closeAndResumePrevFrag(requireActivity(), null)
    }

}