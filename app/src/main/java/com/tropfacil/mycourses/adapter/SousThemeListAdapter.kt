package com.tropfacil.mycourses.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.R
import com.tropfacil.data.ElementX
import com.tropfacil.databinding.*


class SousThemeListAdapter(
    private val sousThemeList: List<ElementX>
) : RecyclerView.Adapter<SousThemeListAdapter.ViewHolder>() {


    inner class ViewHolder(val bind: ItemCourseChildBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemCourseChildBinding =
            ItemCourseChildBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sousItem = sousThemeList[position]

        //set lesson name
        sousItem.libelle.let {
            holder.bind.tvLessonName.text = it
        }

        //calculate time for each lesson
        sousItem.duree_suivi.let {
            val durationInHour = it / 3600
            val durationInMin = (it % 3600) / 60
            val durationInSec = (it % 3600) % 60

            holder.bind.tvTimeSpent.text = when {
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
        //set progress bar
        sousItem.avancement.let {
            holder.bind.progressBar.progress = it
            holder.bind.tvProgressWithPercentage.text = "${it}"
        }

        //set icon play or locked for lessons
        if (sousItem.disponible) {
            holder.bind.imgLessonPlayOrLock.setImageResource(R.drawable.chapter_lock)
        } else {
            holder.bind.imgLessonPlayOrLock.setImageResource(R.drawable.chapters_play)
        }


    }

    override fun getItemCount(): Int {
        return sousThemeList.size
    }
}