package com.tropfacil.userstatprofile.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.databinding.*


class MyActivitiesAdapter(private val isFormationOrEval: Boolean) :
    RecyclerView.Adapter<MyActivitiesAdapter.ViewHolder>() {


    inner class ViewHolder(val bind: ItemActivitiesBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemActivitiesBinding =
            ItemActivitiesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> {
                holder.bind.itmTvPercentage.text = if (isFormationOrEval)
                    "10" else "14"
                holder.bind.itmTvPercentage.setTextColor(Color.parseColor("#38B6A4"))
                holder.bind.itmTvPercentageName.text = "A realiser"
            }
            1 -> {

                holder.bind.itmTvPercentage.text = if (isFormationOrEval)
                    "1" else "3"
                holder.bind.itmTvPercentage.setTextColor(Color.parseColor("#E83C70"))
                holder.bind.itmTvPercentageName.text = "En cours"
            }
            2 -> {
                holder.bind.itmTvPercentage.text = if (isFormationOrEval)
                    "0" else "1"
                holder.bind.itmTvPercentage.setTextColor(Color.parseColor("#3C2875"))
                holder.bind.itmTvPercentageName.text = "Terminees"

            }
            else -> {

                holder.bind.itmTvPercentage.text = if (isFormationOrEval)
                    "10" else "14"
                holder.bind.itmTvPercentage.setTextColor(Color.parseColor("#38B6A4"))
                holder.bind.itmTvPercentageName.text = "A realiser"

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
