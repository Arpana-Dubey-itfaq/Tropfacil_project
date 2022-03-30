package com.tropfacil.mycourses.view

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide

import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.common.interfaces.ResumeFragmentListener
import com.tropfacil.data.Element
import com.tropfacil.data.Parcour
import com.tropfacil.data.Soustheme
import com.tropfacil.util.interfaces.HomeOptionsListener


import com.tropfacil.databinding.CoursePerChapterBinding
import com.tropfacil.model.CourseChaptersWithLessonsModel
import com.tropfacil.mycourses.adapter.CourseExpandableListAdapter
import com.tropfacil.mycourses.adapter.CourseListAdapter
import com.tropfacil.mycourses.adapter.SousThemeListAdapter

import com.tropfacil.util.Constants
import com.tropfacil.util.interfaces.HomeToCourseDetailsListener


class Course_per_chapter_Fragment : BaseFragment(), ResumeFragmentListener,
    HomeToCourseDetailsListener {
    lateinit var binding: CoursePerChapterBinding
    lateinit var homeOptionsListener: HomeOptionsListener
    lateinit var homeCourseAdapter: CourseListAdapter
   // private var videoGalleryUiProvider: VideoGalleryUiProvider? = null
    //private var exoVideoPlayerProvider: ExoVideoPlayerProvider? = null
   private lateinit var parCourse: Parcour
    private lateinit var sousThemeList: Soustheme
    private var isForWhichCourse = false
    private var chaptersWithLessonsList = mutableListOf<CourseChaptersWithLessonsModel>()

    companion object {
        //const val TAG = "MyCourseFragment"

        @JvmStatic
        fun newInstance(parCourseItem: Parcour, isForParCourse: Boolean) = Course_per_chapter_Fragment().apply {
            parCourse = parCourseItem
            isForWhichCourse = isForParCourse
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
      //  homeOptionsListener = requireActivity() as HomeOptionsListener

    }
    private fun loadCourseImage() {
       /* if (isForWhichCourse)
            parCourse.image.let {
                Glide.with(requireActivity())
                    .load(BuildConfig.LOAD_IMAGE + it)
                    .placeholder(R.drawable.logo)
                    .into(binding.topbar.imgCourse)
            }
        else
            sousThemeList.icone.let {
                val removedRes="/"+it.removePrefix("res:")
                Glide.with(requireActivity())
                    .load(BuildConfig.LOAD_IMAGE +removedRes )
                    .placeholder(R.drawable.logo)
                    .into(binding.topbar.imgCourse)
            }*/
    }
    private fun loadParCourData() {
        loadCourseImage()
       /* parCourse.libelle.let {
            binding.topbar.tvCourseName.text = it
            binding.tvCourseNameVal.text = it
        }
        parCourse.type.let {
            binding.tvCategoryName.text = it
        }
        parCourse.description.let {
            binding.tvAboutThisCourse.isVisible = !TextUtils.isEmpty(it)
            binding.line.isVisible = !TextUtils.isEmpty(it)
            binding.tvCourseDesc.isVisible = !TextUtils.isEmpty(it)
            if (!TextUtils.isEmpty(it))
                binding.tvCourseDesc.text = it

        }*/
        calculateTotalDuration()
        getAllLessons()
    }
    private fun calculateTotalDuration() {
        var sumDuration = 0
        if (!parCourse.elements.isNullOrEmpty()) {
            val parCourseElements = parCourse.elements
            sumDuration =
                parCourseElements.filter { it.duree_estimee != null && it.duree_estimee != "" }
                    .sumOf { it.duree_estimee.toInt() }

            val durationInHour = sumDuration / 3600
            val durationInMin = (sumDuration % 3600) / 60
            val durationInSec = (sumDuration % 3600) % 60

            binding.duration1.text = when {
                durationInHour == 0 -> {
                    "${durationInMin}min ${durationInSec}sec"
                }
                durationInMin == 0 -> {
                    "${durationInHour}hr ${durationInSec}sec"
                }
                durationInSec == 0 -> {
                    "${durationInHour}hr ${durationInMin}min"
                }
                else -> {
                    "${durationInHour}hr ${durationInMin}min ${durationInSec}sec"
                }
            }
        }
    }

    fun setData() {
        homeCourseAdapter = CourseListAdapter()
        binding.relCourse1.adapter=homeCourseAdapter

        homeCourseAdapter = CourseListAdapter()
        binding.relCourse2.adapter=homeCourseAdapter

        binding.relCoursequestion.adapter=homeCourseAdapter
    }
    private fun getAllLessons() {
        if (!parCourse.elements.isNullOrEmpty()) {
            val parCourseElements = parCourse.elements
            var lessonsList = mutableListOf<Element>()
            for (i in parCourseElements.indices) {
                val courseChaptersWithLessonsModel = CourseChaptersWithLessonsModel()
                if (parCourseElements[i].type.contains("separateur")) {
                    courseChaptersWithLessonsModel.lessonsList = lessonsList
                    if (lessonsList.size > 0) {
                        chaptersWithLessonsList.add(courseChaptersWithLessonsModel)
                    }
                    lessonsList = mutableListOf()
                } else {
                    lessonsList.add(parCourseElements[i])
                    if (i == parCourseElements.size - 1) {
                        courseChaptersWithLessonsModel.lessonsList = lessonsList
                        chaptersWithLessonsList.add(courseChaptersWithLessonsModel)
                    }
                }
            }
            checkSeparatorOnFirstPositionAvailable(parCourseElements)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Constants.FRAGMENT= Constants.MYCOURSESFragment

        binding = CoursePerChapterBinding.inflate(layoutInflater, container, false)



        return binding.root

    }


    private fun checkSeparatorOnFirstPositionAvailable(parCourseElements: List<Element>) {
        if (!parCourseElements[0].type.equals("separateur")) {
            val firstLessonList: MutableList<Element> = mutableListOf()
            chaptersWithLessonsList[0].lessonsList?.forEach { firstLessonElements ->
                firstLessonList.add(firstLessonElements)
            }
            chaptersWithLessonsList.removeAt(0)
            for (i in firstLessonList.indices) {
                chaptersWithLessonsList[0].lessonsList?.add(i, firstLessonList[i])
            }
        }
        getAllChaptersName(parCourseElements)

    }
    private fun getAllChaptersName(parCourseElements: List<Element>) {
        val allChaptersList: MutableList<Element> =
            parCourseElements.filter { s -> s.type == "separateur" } as MutableList<Element>
        mergeChaptersAndLessonsList(allChaptersList)
    }
    private fun mergeChaptersAndLessonsList(allChaptersList: MutableList<Element>) {
        if (allChaptersList.size == chaptersWithLessonsList.size) {
            for (i in allChaptersList.indices) {
                chaptersWithLessonsList[i].id = allChaptersList[i].id
                chaptersWithLessonsList[i].type = allChaptersList[i].type
                chaptersWithLessonsList[i].libelle = allChaptersList[i].libelle
            }
        }
        calculateProgressBarPercentage()
        if (isForWhichCourse) {
            setParCourDataToExpandableAdapter()
            //binding.expChaptersAndLessonsListView.isVisible = true
            binding.relCourse1.isVisible = true

        }
    }
    private fun calculateProgressBarPercentage() {
        var sumPercentage = 0
        var allLessonsSize = 0
        if (!parCourse.elements.isNullOrEmpty()) {
            val parCourseElements = parCourse.elements
            sumPercentage = parCourseElements.sumOf { it.avancement }
            allLessonsSize = chaptersWithLessonsList.sumOf { it.lessonsList!!.size }
            val totalCoursePercentage = (sumPercentage * 100 / allLessonsSize) / 100 // 207/121
           // binding.progressBar.progress = totalCoursePercentage
          //  binding.tvProgressWithPercentage.text = "${totalCoursePercentage}%"
        }
    }
    private fun setParCourDataToExpandableAdapter() {
        //CREATE AND BIND TO ADAPTER
        setSousThemeDataToAdapter()
        /*setExpandableListViewHeight(
            binding.expChaptersAndLessonsListView,
            binding.courseNestedScroll
        )*/
    }

/*    private fun loadSousThemeData() {
        loadCourseImage()
        sousThemeList.libelle.let {
            binding.topbar.tvCourseName.text = it
            binding.tvCourseNameVal.text = it
            binding.tvCategoryName.text = it
        }
        binding.tvAboutThisCourse.isVisible = !TextUtils.isEmpty(sousThemeList.description)
        binding.line.isVisible = !TextUtils.isEmpty(sousThemeList.description)
        binding.tvCourseDesc.isVisible = !TextUtils.isEmpty(sousThemeList.description)
        if(sousThemeList.description!=null && sousThemeList.description!=""){
            binding.tvCourseDesc.text= Html.fromHtml(sousThemeList.description)
        }
        setSousThemeDataToAdapter()
        binding.expChaptersAndLessonsListView.isVisible = false
        binding.rvSousItems.isVisible = true
        //TODO needs to check for below items details from the api model proper keys
        *//*   binding.tvAboutThisCourse.isVisible = !TextUtils.isEmpty(parCourse.description)
           binding.line.isVisible = !TextUtils.isEmpty(parCourse.description)
           binding.tvCourseDesc.isVisible = !TextUtils.isEmpty(parCourse.description)
           if (!TextUtils.isEmpty(parCourse.description))
               binding.tvCourseDesc.text = parCourse.description*//*

    }*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isForWhichCourse)
            loadParCourData()
        //else loadSousThemeData()
        setData()
    }
    private fun setSousThemeDataToAdapter() {
        if (!sousThemeList.elements.isNullOrEmpty()) {
            val adapter = SousThemeListAdapter(sousThemeList.elements)
            binding.relCourse1.adapter = adapter
        }
    }

    override fun onFragmentResume(bundle: Bundle?) {
       // TODO("Not yet implemented")
    }

    override fun navigateToCourseDetailsScreenViaParCour(parcourItem: Parcour) {
       // TODO("Not yet implemented")
    }

    override fun navigateToCourseDetailsScreenViaSousTheme(sousItem: Soustheme) {
        //TODO("Not yet implemented")
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