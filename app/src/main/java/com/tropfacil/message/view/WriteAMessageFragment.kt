package com.tropfacil.message.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.tropfacil.R
import com.tropfacil.base.BaseActivity
import com.tropfacil.base.BaseFragment
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.databinding.*
import com.tropfacil.util.Constants


class WriteAMessageFragment : BaseFragment() {
    lateinit var binding: FragmentWriteAMessageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteAMessageBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

    }


    private fun initListeners() {
        binding.etSubject.doAfterTextChanged {
            enableOrDisableButton()
        }
        binding.etWriteAQuestion.doAfterTextChanged {
            enableOrDisableButton()
        }
        binding.btnSend.setOnClickListener {
            //TODO Handle api calling
            closeFragment()
        }
    }

    private fun enableOrDisableButton() {
        val isEnabled = binding.etSubject.text.toString()
            .isNotEmpty() && binding.etWriteAQuestion.text.toString().isNotEmpty()
        binding.btnSend.isEnabled = isEnabled
        binding.btnSend.isClickable = isEnabled
        if (isEnabled) {
            binding.btnSend.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_green
            )
        } else {
            binding.btnSend.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_light_green
            )
        }

    }

    private fun closeFragment() {
        closeAndResumePrevFrag(requireActivity(), null)
    }

}