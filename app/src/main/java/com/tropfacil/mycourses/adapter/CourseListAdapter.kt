package com.tropfacil.mycourses.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.databinding.*


class CourseListAdapter(
) : RecyclerView.Adapter<CourseListAdapter.ViewHolder>() {


    inner class ViewHolder(val bind: ItemCoursePerBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemCoursePerBinding =
            ItemCoursePerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 5
    }

    /*  fun updateData(missionList: List<MissionData>) {
          list = missionList
          notifyDataSetChanged()
      }*/
}