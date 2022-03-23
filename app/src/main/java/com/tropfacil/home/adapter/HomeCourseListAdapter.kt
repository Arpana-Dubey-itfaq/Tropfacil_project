package com.tropfacil.home.adapter

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.databinding.ItemHomePageCourseBinding
import com.tropfacil.databinding.ItemHomePageCourseListBinding
import com.tropfacil.databinding.ItemTabRecommededExerciseBinding
import com.tropfacil.util.interfaces.HomeToCourseDetailsListener


class HomeCourseListAdapter(private val homeToCourseDetailsListener: HomeToCourseDetailsListener
) : RecyclerView.Adapter<HomeCourseListAdapter.ViewHolder>() {


    inner class ViewHolder(val bind: ItemHomePageCourseListBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemHomePageCourseListBinding =
            ItemHomePageCourseListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind.icCourseDetails.setOnClickListener {
            homeToCourseDetailsListener.navigateToCourseDetailsScreen(164)
        }

    }

    override fun getItemCount(): Int {
        return 5
    }

}