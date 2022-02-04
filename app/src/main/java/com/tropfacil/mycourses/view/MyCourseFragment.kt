package com.tropfacil.mycourses.view

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
import com.tropfacil.message.view.MessageActivity
import com.tropfacil.notificaions.view.NotificationsActivity
import com.tropfacil.search.view.SearchActivity
import com.tropfacil.util.Constants


class MyCourseFragment : BaseFragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeOptionsListener: HomeOptionsListener
    lateinit var homeCourseAdapter: HomeCourseAdapter


    companion object {
        const val TAG = "MyCourseFragment"

        @JvmStatic
        fun newInstance() = MyCourseFragment().apply {

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeOptionsListener = requireActivity() as HomeOptionsListener

    }


    fun setData() {
        homeCourseAdapter = HomeCourseAdapter()
        binding.relCourse.adapter = homeCourseAdapter

        binding.incCountine.cardPlay.visibility = View.VISIBLE
        binding.incLevelInfo.cardLevel.visibility = View.GONE
        binding.lblconutine.visibility = View.GONE
        binding.incCountine.cardCountine.visibility = View.GONE
        binding.lblRecommendExcrcise.visibility = View.GONE
        binding.tabLayoutExercise.visibility = View.GONE
        binding.viewPagerExercise.visibility = View.GONE
        binding.lblcourse.visibility = View.GONE
        binding.cardSchedule.visibility = View.GONE
        binding.viewPagerscheduleCourse.visibility = View.GONE
        binding.lblMyCourses.visibility = View.VISIBLE
        binding.lblMyCourses.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Constants.FRAGMENT= Constants.MYCOURSESFragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
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
        /*binding.nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
              //  binding.view1.visibility = View.GONE
                binding.lblMyCourses.setTextColor(
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
            //    binding.view1.visibility = View.VISIBLE
                binding.lblMyCourses.setTextColor(
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
        })*/
    }
}