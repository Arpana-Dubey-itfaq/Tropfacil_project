package com.tropfacil.home.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.databinding.ItemHomePageCourseBinding
import com.tropfacil.databinding.ItemTabRecommededExerciseBinding
import com.tropfacil.util.Constants
import androidx.core.graphics.drawable.DrawableCompat

import android.graphics.drawable.Drawable
import android.R

import androidx.appcompat.content.res.AppCompatResources
import android.graphics.PorterDuff

import android.graphics.PorterDuffColorFilter

import android.widget.TextView
import com.tropfacil.databinding.ItemDetailCourseBinding


class CourseDetailAdapter(
) : RecyclerView.Adapter<CourseDetailAdapter.ViewHolder>() {


    inner class ViewHolder(val bind: ItemDetailCourseBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemDetailCourseBinding =
            ItemDetailCourseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind.tvTitle.setTextColor(
            ContextCompat.getColor(
                context,
                (com.tropfacil.R.color.black)
            )
        )
        holder.bind.tvViewAllWHite.visibility = View.GONE
        holder.bind.tvViewAll.visibility = View.VISIBLE
        if (Constants.MYCOURSESFragment == Constants.FRAGMENT) {
            if (position == 0) {
                holder.bind.tvTitle.setTextColor(
                    ContextCompat.getColor(
                        context,
                        (com.tropfacil.R.color.white)
                    )
                )

                holder.bind.tvViewAllWHite.visibility = View.VISIBLE
                holder.bind.tvViewAll.visibility = View.GONE
            }

            /*  val unwrappedDrawable =
                  AppCompatResources.getDrawable(context, com.tropfacil.R.drawable.view_all_icon)

              val wrappedDrawable = unwrappedDrawable?.let { DrawableCompat.wrap(it) }
              wrappedDrawable?.let { DrawableCompat.setTint(it, Color.WHITE) }
       */
        }
       /* var homeCourseListAdapter = HomeCourseListAdapter()
        holder.bind.relCourse.adapter = homeCourseListAdapter*/
    }

    override fun getItemCount(): Int {
        return 1
    }

    /*  fun updateData(missionList: List<MissionData>) {
          list = missionList
          notifyDataSetChanged()
      }*/
}