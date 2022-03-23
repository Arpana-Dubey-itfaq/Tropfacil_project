package com.tropfacil.mycourses.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tropfacil.base.BaseFragment
import com.tropfacil.util.interfaces.HomeOptionsListener
import android.os.Handler


import com.tropfacil.databinding.*
import com.tropfacil.home.adapter.*
import com.tropfacil.home.view.HomeFragment
import com.tropfacil.main.view.MainActivity

import com.tropfacil.mycourses.adapter.CourseListAdapter

import com.tropfacil.util.Constants


class CourseDetailsFragment : BaseFragment() {
    lateinit var binding: FragmentCourseDetailsBinding
    lateinit var homeOptionsListener: HomeOptionsListener
    lateinit var homeCourseAdapter: CourseListAdapter
    lateinit var homeCourseAdapter1: CourseDetailAdapter
   // private var exoVideoPlayerProvider: ExoVideoPlayerProvider? = null
    private var orientationHandler: Handler? = null
    private var videoUrlActual: String? = null
    companion object {
        const val TAG = "MyCourseFragment"

        @JvmStatic
        fun newInstance() = CourseDetailsFragment().apply {

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeOptionsListener = requireActivity() as HomeOptionsListener

    }


    fun setData() {
//        homeCourseAdapter = CourseListAdapter()
//           binding.relCourse1.adapter=homeCourseAdapter
//
//        homeCourseAdapter1 = CourseDetailAdapter()
//        binding.relCourse12.adapter = homeCourseAdapter1
     //   binding.relCoursequestion.adapter=homeCourseAdapter

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Constants.FRAGMENT= Constants.MYCOURSESFragment

        binding = FragmentCourseDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListner()
    setData()


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

    fun setListner() {
        binding.btnCreateAccount.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    Course_per_chapter_Fragment.newInstance(),
                    Course_per_chapter_Fragment.TAG
                ).commit()
        }
       /* binding.topbar.imgsearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
        binding.topbar.imgNotification.setOnClickListener {
            startActivity(Intent(requireContext(), NotificationsActivity::class.java))

        }
        binding.topbar.imgmessage.setOnClickListener {
            startActivity(Intent(requireContext(), MessageActivity::class.java))

        }*/
    }
}