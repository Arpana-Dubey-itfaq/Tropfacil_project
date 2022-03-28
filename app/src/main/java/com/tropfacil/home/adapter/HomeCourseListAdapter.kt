package com.tropfacil.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.databinding.ItemHomePageCourseListBinding


class HomeCourseListAdapter(
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


    }

    override fun getItemCount(): Int {
        return 5
    }

}