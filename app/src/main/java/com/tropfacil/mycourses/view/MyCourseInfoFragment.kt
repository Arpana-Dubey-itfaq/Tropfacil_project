package com.tropfacil.mycourses.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.data.Theme
import com.tropfacil.databinding.FragmentExerciseInfoBinding
import com.tropfacil.databinding.FragmentMyCourseInfoBinding
import com.tropfacil.model.exercices.ExercicesInfoList
import com.tropfacil.mycourses.adapter.CourseInfoAdapter
import com.tropfacil.ui.nav.exercices.adapter.ExercicesInfoAdapter
import com.tropfacil.util.Constants
import com.tropfacil.utils.ItemClickListener
import kotlinx.android.synthetic.main.raw_item_course_info.view.*

class MyCourseInfoFragment : BaseFragment() {
    lateinit var binding: FragmentMyCourseInfoBinding
    private  var theme:Theme?= null
    private var courseInfoAdapter:CourseInfoAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.arguments?.let {
            if (it.getSerializable(Constants.THEME_INFO_LIST) != null) {
                theme =
                    it.getSerializable(Constants.THEME_INFO_LIST) as Theme
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMyCourseInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI()

    }

    private fun setUI() {
        courseInfoAdapter = CourseInfoAdapter(requireContext(), object : ItemClickListener {
            override fun onItemClick(bundle: Bundle?) {

            }
        })


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = courseInfoAdapter

        if(theme?.parcours?.isNotEmpty() == true)
            courseInfoAdapter?.updateList(theme?.parcours?:ArrayList())

        binding.tvTimePercent.text = "${getOverallPercentage()}%"
        binding.clCourse.isVisible = theme?.parcours?.isNotEmpty() == true
        binding.tvNoDataFound.isVisible = theme?.parcours?.isNullOrEmpty() == true
    }

    private fun getOverallPercentage(): Int {

        val parcoursList = theme?.parcours?:ArrayList()
        var overallPercentage = 0
        if(parcoursList.isNotEmpty()) {
            for (currentParcours in parcoursList) {
                var sumPercentage = 0
                if (currentParcours.elements.isNotEmpty()) {
                    val parCourseElements = currentParcours.elements
                    sumPercentage = parCourseElements.sumOf { it.avancement }
                    val totalCoursePercentage = (sumPercentage / currentParcours.elements.size)
                    overallPercentage += totalCoursePercentage
                }
            }
            overallPercentage /= parcoursList.size
        }
        return overallPercentage
    }
}