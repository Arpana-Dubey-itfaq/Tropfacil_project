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
import com.tropfacil.util.interfaces.HomeOptionsListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tropfacil.R
import androidx.core.widget.NestedScrollView
import android.graphics.PorterDuff
import android.os.Handler
import android.widget.SeekBar
import android.widget.Toast


import androidx.core.content.ContextCompat
import com.tropfacil.databinding.*
import com.tropfacil.home.adapter.*

import com.tropfacil.message.view.MessageActivity
import com.tropfacil.mycourses.adapter.CourseListAdapter
import com.tropfacil.notificaions.view.NotificationsActivity

import com.tropfacil.search.view.SearchActivity
import com.tropfacil.util.Constants
import kotlinx.android.synthetic.main.inclued_seekbar.*


class Course_chapter_Fragment : BaseFragment() {
    lateinit var binding: CourseChapterBinding
    lateinit var homeOptionsListener: HomeOptionsListener
    lateinit var homeCourseAdapter: CourseListAdapter
    lateinit var homeCourseAdapter1: CourseDetailAdapter
    lateinit var viewPagerSchudeleCourseAdapter: ViewPagerAdapter
   // private var exoVideoPlayerProvider: ExoVideoPlayerProvider? = null
    private var orientationHandler: Handler? = null
    private var videoUrlActual: String? = null
    companion object {
        const val TAG = "MyCourseFragment"

        @JvmStatic
        fun newInstance() = Course_chapter_Fragment().apply {

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeOptionsListener = requireActivity() as HomeOptionsListener

    }


    fun setData() {
        homeCourseAdapter = CourseListAdapter()
           binding.relCourse1.adapter=homeCourseAdapter
        viewPagerSchudeleCourseAdapter = ViewPagerAdapter(requireActivity(), 5)

        homeCourseAdapter1 = CourseDetailAdapter()
        binding.relCourse12.adapter = homeCourseAdapter1
        binding.viewPagerscheduleCourse.adapter = viewPagerSchudeleCourseAdapter
     //   binding.relCoursequestion.adapter=homeCourseAdapter

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Constants.FRAGMENT= Constants.MYCOURSESFragment

        binding = CourseChapterBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // setListner()
    setData()
        setTabLayout()

        /*seekBar?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
             *//*   Toast.makeText(this@MainActivity,
                    "Progress is: " + seek.progress + "%",
                    Toast.LENGTH_SHORT).show()*//*
            }
        })*/

    }
    fun setTabLayout() {


        TabLayoutMediator(
            binding.tabscheduleCourse,
            binding.viewPagerscheduleCourse
        ) { tab, position ->
            /* val tabView = LayoutInflater.from(this.context)
                 .inflate(R.layout.custom_tab_recommeded_exercise, binding.tabLayoutExercise, false)
*/
            // tab.setCustomView(R.layout.custom_tab_recommeded_exercise);
            binding.tabscheduleCourse.setTabMode(TabLayout.MODE_SCROLLABLE);

            when (position) {
                0 -> {
                    tab.text = "CSS3 - ENI® CERTIFICATIONS"
                    // tabview.imgIcon.setImageResource(R.drawable.menu_home)
                    // tabview.tvExerciseName.text = "hfgdsghf"
                    // you can set your tab text and color here for tab1
                }
                1 -> {
                    tab.text = "FORMATION PLATEFORME"
                }
                2 -> {
                    tab.text = "PACK OFFICE 2019 INTÉGRAL"
                }
                3 -> {
                    tab.text = "REFLEX'ENGLISH NIVEAU 1"
                }
                4 -> {
                    tab.text = "This Month"
                }
            }
        }.attach()
    }
    /*fun setListner() {
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
        *//*binding.nestedscrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
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
        })*//*
    }*/
}