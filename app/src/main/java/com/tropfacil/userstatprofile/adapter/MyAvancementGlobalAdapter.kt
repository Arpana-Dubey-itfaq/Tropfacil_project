package com.tropfacil.userstatprofile.adapter

import android.content.Context
import android.graphics.Color
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.R
import com.tropfacil.databinding.*


class MyAvancementGlobalAdapter(
) : RecyclerView.Adapter<MyAvancementGlobalAdapter.ViewHolder>() {


    inner class ViewHolder(val bind: ItemAvancementGlobalBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemAvancementGlobalBinding =
            ItemAvancementGlobalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.bind.itmTvPercentage.text = "100%"
                holder.bind.itmTvPercentage.setTextColor(Color.parseColor("#38B6A4"))
                holder.bind.itmTvPercentageName.text="des formations terminées"
                holder.bind.progressBar.progress=100
            }
            1->{
                holder.bind.itmTvPercentage.text = "50%"
                holder.bind.itmTvPercentage.setTextColor(Color.parseColor("#E83C70"))
                holder.bind.itmTvPercentageName.text="des évaluations complétées"
                holder.bind.progressBar.progress=50
            }
            2->{
                holder.bind.itmTvPercentage.text = "3%"
                holder.bind.itmTvPercentage.setTextColor(Color.parseColor("#3C2875"))
                holder.bind.itmTvPercentageName.text="des badges obtenus"
                holder.bind.progressBar.progress=30

            }
            else->{
                holder.bind.itmTvPercentage.text = "100%"
                holder.bind.itmTvPercentage.setTextColor(Color.parseColor("#38B6A4"))
                holder.bind.itmTvPercentageName.text="des formations terminées"
                holder.bind.progressBar.progress=100

            }
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
