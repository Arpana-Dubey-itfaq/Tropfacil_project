package com.tropfacil.mycourses.view


import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.tropfacil.BuildConfig
import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.common.interfaces.ResumeFragmentListener
import com.tropfacil.data.Element
import com.tropfacil.data.Parcour
import com.tropfacil.data.Soustheme
import com.tropfacil.databinding.FragmentCourseDetailsBinding
import com.tropfacil.model.CourseChaptersWithLessonsModel
import com.tropfacil.mycourses.adapter.CourseExpandableListAdapter
import com.tropfacil.mycourses.adapter.SousThemeListAdapter
import setExpandableListViewHeight
import setListViewHeight


class CourseDetailsFragment : BaseFragment(), ResumeFragmentListener {
    lateinit var binding: FragmentCourseDetailsBinding
    private lateinit var parCourse: Parcour
    private lateinit var sousThemeList: Soustheme
    private var isForWhichCourse = false
    private var chaptersWithLessonsList = mutableListOf<CourseChaptersWithLessonsModel>()

    companion object {
        const val TAG = "MyCourseFragment"

        @JvmStatic
        fun newInstance(parCourseItem: Parcour, isForParCourse: Boolean) =
            CourseDetailsFragment().apply {
                parCourse = parCourseItem
                isForWhichCourse = isForParCourse
            }

        @JvmStatic
        fun newInstance(sousThemeItem: Soustheme, isForSousTheme: Boolean) =
            CourseDetailsFragment().apply {
                sousThemeList = sousThemeItem
                isForWhichCourse = isForSousTheme
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        if (isForWhichCourse)
            loadParCourData()
        else loadSousThemeData()
    }

    private fun loadSousThemeData() {
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
        binding.tvDurationName.text ="0min 0sec"
        //TODO needs to check for below items details from the api model proper keys
        /*   binding.tvAboutThisCourse.isVisible = !TextUtils.isEmpty(parCourse.description)
           binding.line.isVisible = !TextUtils.isEmpty(parCourse.description)
           binding.tvCourseDesc.isVisible = !TextUtils.isEmpty(parCourse.description)
           if (!TextUtils.isEmpty(parCourse.description))
               binding.tvCourseDesc.text = parCourse.description*/

    }

    private fun loadParCourData() {
        loadCourseImage()
        parCourse.libelle.let {
            binding.topbar.tvCourseName.text = it
            binding.tvCourseNameVal.text = it
            binding.tvCategoryName.text = it

        }

        parCourse.description.let {
            binding.tvAboutThisCourse.isVisible = !TextUtils.isEmpty(it)
            binding.line.isVisible = !TextUtils.isEmpty(it)
            binding.tvCourseDesc.isVisible = !TextUtils.isEmpty(it)
            if (!TextUtils.isEmpty(it))
                binding.tvCourseDesc.text = it

        }
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

            binding.tvDurationName.text = when {
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


    private fun loadCourseImage() {
        if (isForWhichCourse)
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
                    .load(BuildConfig.LOAD_ICON_IMAGE +removedRes )
                    .placeholder(R.drawable.logo)
                    .into(binding.topbar.imgCourse)
            }
    }

    private fun setListener() {
        binding.topbar.imgBackArrow.setOnClickListener {
            closeFragment()
        }
        binding.btnStartCourse.setOnClickListener {

        }
        binding.expChaptersAndLessonsListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            //TODO handle the navigation to per chapter view.
        /*    Toast.makeText(
                requireContext(),
                "Clicked: " + chaptersWithLessonsList[groupPosition].libelle + " -> " + (chaptersWithLessonsList[groupPosition].lessonsList?.get(
                    childPosition
                )),
                Toast.LENGTH_SHORT
            ).show()*/
            false
        }
        binding.expChaptersAndLessonsListView.setOnGroupClickListener { parent, v, groupPosition, id ->
            setListViewHeight(parent, groupPosition);
            false
        }
    }

    private fun closeFragment() {
        closeAndResumePrevFrag(requireActivity(), null)
    }

    //TODO refactor the code
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
            binding.expChaptersAndLessonsListView.isVisible = true
            binding.rvSousItems.isVisible = false

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
            binding.progressBar.progress = totalCoursePercentage
            binding.tvProgressWithPercentage.text = "${totalCoursePercentage}%"
        }
    }

    private fun setParCourDataToExpandableAdapter() {
        //CREATE AND BIND TO ADAPTER
        val adapter = CourseExpandableListAdapter(requireContext(), chaptersWithLessonsList)
        binding.expChaptersAndLessonsListView.setAdapter(adapter)
        adapter.notifyDataSetChanged()
        setExpandableListViewHeight(
            binding.expChaptersAndLessonsListView,
            binding.courseNestedScroll
        )
    }

    private fun setSousThemeDataToAdapter() {
        if (!sousThemeList.elements.isNullOrEmpty()) {
            val adapter = SousThemeListAdapter(sousThemeList.elements)
            binding.rvSousItems.adapter = adapter
        }
    }

    override fun onFragmentResume(bundle: Bundle?) {

    }
}