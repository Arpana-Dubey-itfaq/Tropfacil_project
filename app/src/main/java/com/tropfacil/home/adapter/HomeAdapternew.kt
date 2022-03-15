package com.tropfacil.home.adapter

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.databinding.ItemTabRecommededExerciseBinding
import com.tropfacil.databinding.ItemTabRecommededExercisenewBinding


class HomeAdapternew(
) : RecyclerView.Adapter<HomeAdapternew.ViewHolder>() {


    inner class ViewHolder(val bind: ItemTabRecommededExercisenewBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemTabRecommededExercisenewBinding =
            ItemTabRecommededExercisenewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }

    /*  fun updateData(missionList: List<MissionData>) {
          list = missionList
          notifyDataSetChanged()
      }*/
}