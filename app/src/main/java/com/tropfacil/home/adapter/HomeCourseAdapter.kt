package com.tropfacil.home.adapter

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.databinding.ItemHomePageCourseBinding
import com.tropfacil.databinding.ItemTabRecommededExerciseBinding


class HomeCourseAdapter(
) : RecyclerView.Adapter<HomeCourseAdapter.ViewHolder>() {


    inner class ViewHolder(val bind: ItemHomePageCourseBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemHomePageCourseBinding =
            ItemHomePageCourseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var homeCourseListAdapter = HomeCourseListAdapter()
        holder.bind.relCourse.adapter = homeCourseListAdapter
    }

    override fun getItemCount(): Int {
        return 2
    }

    /*  fun updateData(missionList: List<MissionData>) {
          list = missionList
          notifyDataSetChanged()
      }*/
}