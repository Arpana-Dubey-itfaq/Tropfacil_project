package com.tropfacil.ui.nav.exercices

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tropfacil.base.BaseFragment
import com.tropfacil.home.adapter.ViewPagerAdapter
import com.tropfacil.util.interfaces.HomeOptionsListener
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import com.tropfacil.databinding.CustomTabRecommededExerciseBinding


import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.tropfacil.base.BaseActivity
import com.tropfacil.databinding.FragmentExcerciseBinding
import com.tropfacil.message.view.WriteAMessageFragment
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.notificaions.view.NotificationsActivity
import com.tropfacil.search.view.SearchActivity
import com.tropfacil.ui.nav.profile.ProfileSettingsViewModel
import com.tropfacil.util.Constants
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject


class MyExerciseFragment : BaseFragment() {
    lateinit var binding: FragmentExcerciseBinding
    private val viewModel by inject<ExercicesViewModel>()
    lateinit var homeOptionsListener: HomeOptionsListener
    lateinit var viewPagerExcerAdapter: ViewPagerAdapter


    companion object {
        const val TAG = "MyExerciseFragment"

        @JvmStatic
        fun newInstance() = MyExerciseFragment().apply {

        }
    }

    fun setTabLayout() {


        TabLayoutMediator(binding.tabLayoutExercise, binding.viewPagerExercise) { tab, position ->
            /* val tabView = LayoutInflater.from(this.context)
                 .inflate(R.layout.custom_tab_recommeded_exercise, binding.tabLayoutExercise, false)
*/
            // tab.setCustomView(R.layout.custom_tab_recommeded_exercise);
            var tabview = CustomTabRecommededExerciseBinding.inflate(
                layoutInflater,
                binding.tabLayoutExercise,
                false
            )
            tab.setCustomView(tabview.root)

            when (position) {
                0 -> {
                    // tabview.imgIcon.setImageResource(R.drawable.menu_home)
                    // tabview.tvExerciseName.text = "hfgdsghf"
                    // you can set your tab text and color here for tab1
                }
                1 -> {
                    //   tabview.tvExerciseName.text = "hfgdsghf"

                    // you can set your tab text and color here for tab2
                }
                2 -> {
                    // tabview.tvExerciseName.text = "hfgdsghf"

                    // you can set your tab text and color here for tab3
                }
            }
        }.attach()


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeOptionsListener = requireActivity() as HomeOptionsListener

    }


    fun setData() {
        viewPagerExcerAdapter = ViewPagerAdapter(requireActivity(), 5)
        binding.viewPagerExercise.adapter = viewPagerExcerAdapter


        binding.incCountine.cardPlay.visibility = View.VISIBLE
        binding.incLevelInfo.cardLevel.visibility = View.GONE
        binding.lblconutine.visibility = View.GONE
        binding.incCountine.cardCountine.visibility = View.GONE
        binding.relCourse.visibility = View.GONE
        binding.lblRecommendExcrcise.text = getString(R.string.my_exercises)
        binding.lblRecommendExcrcise.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )

        binding.lblcourse.visibility = View.GONE
        binding.cardSchedule.visibility = View.GONE
        binding.viewPagerscheduleCourse.visibility = View.GONE

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Constants.FRAGMENT= Constants.MYEXERCISEFragment

        binding = FragmentExcerciseBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListner()
        setData()
        setTabLayout()
        initObservers()
    }
    private fun initObservers() {
        viewModel.getExerciseList()
        lifecycleScope.launchWhenStarted {
            viewModel._getExercicesStateFlow.collect { it ->
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
                        Log.e(TAG, "initObservers: ${it.data.message}", )
                    }
                    else -> {
                    }
                }
            }
        }

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

            val writeAMessageFragment = WriteAMessageFragment()
            (requireActivity() as BaseActivity).visitNewFragment(R.id.fragment_container, writeAMessageFragment)

//            startActivity(Intent(requireContext(), MessageActivity::class.java))

        }
/*
        binding.nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.view1.visibility = View.GONE
                binding.lblRecommendExcrcise.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.black
                    )
                )

                Log.i(TAG, "Scroll DOWN")
            }
            if (scrollY < oldScrollY) {

                Log.i(TAG, "Scroll UP")
            }
            if (scrollY == 0) {
                binding.view1.visibility = View.VISIBLE
                binding.lblRecommendExcrcise.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                Log.i(TAG, "TOP SCROLL")
            }
            if (scrollY == v.measuredHeight - v.getChildAt(0).measuredHeight) {
                Log.i(TAG, "BOTTOM SCROLL")
            }
        })
*/
    }
}