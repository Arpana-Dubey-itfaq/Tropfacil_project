package com.tropfacil.mycourses.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.R
import com.tropfacil.base.BaseViewHolder
import com.tropfacil.data.Parcour
import com.tropfacil.getDurationFromSeconds
import com.tropfacil.loadImage
import com.tropfacil.utils.ItemClickListener
import kotlinx.android.synthetic.main.raw_item_course_info.view.*

/**
 * Created by Nimesh Patel on 31-03-2022.
 */
class CourseInfoAdapter(
    private var context: Context,
    private var itemClickListener: ItemClickListener,
) : RecyclerView.Adapter<CourseInfoAdapter.CoursesInfoViewHolder>() {

    private var parcourList: List<Parcour> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesInfoViewHolder {
        return CoursesInfoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.raw_item_course_info, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoursesInfoViewHolder, position: Int) {
        with(holder) {
            with(parcourList[position]) {
                itemView.title_tv.text = libelle
                itemView.course_iv.loadImage(context,
                    "${com.tropfacil.util.Constants.BASE_IMAGE_URL}${image}")
                itemView.description_tv.text = description
                itemView.description_tv.isVisible = description.trim().isNotEmpty()
                var sumPercentage = 0
                var sumDuration=0
                if (elements.isNotEmpty()) {
                    val parCourseElements = elements
                    sumPercentage = parCourseElements.sumOf { it.avancement }
                    sumDuration = parCourseElements.sumOf { it.duree_suivi }
                    val totalCoursePercentage = (sumPercentage / elements.size)
                    val timeSpent:String= getDurationFromSeconds(sumDuration)
                    itemView.tv_time.text = String.format(context.getString(R.string.str_time_spent_value),timeSpent)
                    itemView.progress_time.progress = totalCoursePercentage
                    itemView.tv_time_percent.text = "${totalCoursePercentage}%"
                }

            }
        }
    }

    override fun getItemCount(): Int = parcourList.size

    fun updateList(parcourList: List<Parcour>) {
        this.parcourList = parcourList
        notifyDataSetChanged()
    }

    class CoursesInfoViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun clear() {

        }
    }
}