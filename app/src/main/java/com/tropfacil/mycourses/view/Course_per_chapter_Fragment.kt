package com.tropfacil.mycourses.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tropfacil.base.BaseFragment
import com.tropfacil.util.interfaces.HomeOptionsListener


import com.tropfacil.databinding.CoursePerChapterBinding
import com.tropfacil.mycourses.adapter.CourseListAdapter

import com.tropfacil.util.Constants


class Course_per_chapter_Fragment : BaseFragment() {
    lateinit var binding: CoursePerChapterBinding
    lateinit var homeOptionsListener: HomeOptionsListener
    lateinit var homeCourseAdapter: CourseListAdapter
   // private var videoGalleryUiProvider: VideoGalleryUiProvider? = null
    //private var exoVideoPlayerProvider: ExoVideoPlayerProvider? = null

    companion object {
        const val TAG = "MyCourseFragment"

        @JvmStatic
        fun newInstance() = Course_per_chapter_Fragment().apply {

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
      //  homeOptionsListener = requireActivity() as HomeOptionsListener

    }


    fun setData() {
        homeCourseAdapter = CourseListAdapter()
        binding.relCourse1.adapter=homeCourseAdapter

        homeCourseAdapter = CourseListAdapter()
        binding.relCourse2.adapter=homeCourseAdapter

        binding.relCoursequestion.adapter=homeCourseAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Constants.FRAGMENT= Constants.MYCOURSESFragment

        binding = CoursePerChapterBinding.inflate(layoutInflater, container, false)



        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // setListner()
        setData()
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