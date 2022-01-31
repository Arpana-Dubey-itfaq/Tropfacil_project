package com.tropfacil.home.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tropfacil.databinding.FragmentHomeBinding
import com.tropfacil.util.interfaces.HomeOptionsListener

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeOptionsListener: HomeOptionsListener

    companion object {
        const val TAG = "HomeFragment"

        @JvmStatic
        fun newInstance() = HomeFragment().apply {

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeOptionsListener = requireActivity() as HomeOptionsListener

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setListner()
        return binding.root

    }

    fun setListner() {
        binding.topbar.imgUser.setOnClickListener {
            homeOptionsListener.onClickMenu()
        }
    }
}