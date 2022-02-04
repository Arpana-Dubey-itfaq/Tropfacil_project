package com.tropfacil.notificaions.adapter

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.R
import com.tropfacil.databinding.ItemHomePageCourseBinding
import com.tropfacil.databinding.ItemHomePageCourseListBinding
import com.tropfacil.databinding.ItemNotificationBinding
import com.tropfacil.databinding.ItemTabRecommededExerciseBinding


class NotificationListAdapter(
) : RecyclerView.Adapter<NotificationListAdapter.ViewHolder>() {


    inner class ViewHolder(val bind: ItemNotificationBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemNotificationBinding =
            ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (position < 2) {
            holder.bind.cardNotification.setCardBackgroundColor(ContextCompat.getColor(context,R.color.light_green))
            holder.bind.tvNew.visibility=View.VISIBLE
        }else
        {
            holder.bind.cardNotification.setCardBackgroundColor(ContextCompat.getColor(context,R.color.white))
            holder.bind.tvNew.visibility=View.GONE

        }

    }

    override fun getItemCount(): Int {
        return 5
    }

    /*  fun updateData(missionList: List<MissionData>) {
          list = missionList
          notifyDataSetChanged()
      }*/
}