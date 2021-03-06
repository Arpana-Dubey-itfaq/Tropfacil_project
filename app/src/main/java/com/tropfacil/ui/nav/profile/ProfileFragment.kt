package com.tropfacil.ui.nav.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.data.provider.PREF_USER_FIRST_NAME
import com.tropfacil.data.provider.PREF_USER_LAST_NAME
import com.tropfacil.data.provider.PREF_USER_NAME
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.databinding.FragmentProfileBinding
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.textCapSentences
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.flow.collect
import okhttp3.ResponseBody
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment() {

    lateinit var binding: FragmentProfileBinding
    private val viewModel by inject<ProfileSettingsViewModel>()
    private val preferenceProvider by inject<PreferenceProvider>()

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
            val nom = binding.nameEt.text.toString().trim()
            val prenom = binding.lastnameEt.text.toString().trim()
            viewModel.updateUser(nom,prenom)

        }
        binding.spinnerTv.text = resources.getStringArray(R.array.civility_code)[0]

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {

                binding.spinnerTv.text = resources.getStringArray(R.array.civility_code)[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        binding.usernameEt.setText(preferenceProvider.getString(PREF_USER_NAME, ""))
        binding.nameEt.setText(preferenceProvider.getString(PREF_USER_FIRST_NAME, ""))
        binding.lastnameEt.setText(preferenceProvider.getString(PREF_USER_LAST_NAME, ""))

    }

    private fun initObservers() {
        viewModel.getProfilePicture()
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
                        preferenceProvider.getString(PREF_USER_FIRST_NAME, name_et.text.toString())
                        preferenceProvider.getString(PREF_USER_LAST_NAME, lastname_et.text.toString())
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
                        displayImages(it.data)
                    }
                    else -> {
                    }
                }
            }
        }

    }

    private fun displayImages(data: ResponseBody) {
        try {
            val bitmap: Bitmap = BitmapFactory.decodeStream(data.byteStream())
            Glide.with(requireActivity())
                .asBitmap()
                .load(bitmap)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.user_profile)
                .into(binding.profileIv)
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    private fun closeFragment() {
        closeAndResumePrevFrag(requireActivity(), null)
    }

}