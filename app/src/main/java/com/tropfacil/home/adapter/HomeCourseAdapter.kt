package com.tropfacil.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.databinding.ItemHomePageCourseBinding
import com.tropfacil.util.Constants


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