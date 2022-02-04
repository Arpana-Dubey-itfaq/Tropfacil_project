package com.tropfacil.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.FragmentHomeBinding
import com.tropfacil.home.adapter.HomeAdapter
import com.tropfacil.home.adapter.HomeCourseAdapter
import com.tropfacil.home.adapter.HomeCourseListAdapter
import com.tropfacil.home.adapter.ViewPagerAdapter
import com.tropfacil.util.interfaces.HomeOptionsListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import com.tropfacil.databinding.CustomTabRecommededExerciseBinding
import com.tropfacil.databinding.ItemTabRecommededExerciseBinding
import androidx.core.widget.NestedScrollView
import android.graphics.PorterDuff


import androidx.core.content.ContextCompat
import com.tropfacil.databinding.FragmentHome1Binding
import com.tropfacil.message.view.MessageActivity
import com.tropfacil.notificaions.view.NotificationsActivity
import com.tropfacil.search.view.SearchActivity
import com.tropfacil.util.Constants


class Home1Fragment : BaseFragment() {
    lateinit var binding: FragmentHome1Binding
    lateinit var homeOptionsListener: HomeOptionsListener


    companion object {
        const val TAG = "Home1Fragment"

        @JvmStatic
        fun newInstance() = Home1Fragment().apply {

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeOptionsListener = requireActivity() as HomeOptionsListener

    }


    fun setData() {

        binding.incCountine.cardPlay.visibility = View.VISIBLE
        binding.topbar.cardChatcount.visibility = View.GONE

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Constants.FRAGMENT = Constants.HomeFragment
        binding = FragmentHome1Binding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListner()
        setData()
    }

    fun setListner() {
        binding.topbar.imgUser.setOnClickListener {
            homeOptionsListener.onClickMenu()
        }
        binding.topbar.imgsearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
        binding.topbar.imgNotification.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationsActivity::class.java))

        }
        binding.topbar.imgmessage.setOnClickListener {
            startActivity(Intent(requireContext(), MessageActivity::class.java))

        }

    }
}