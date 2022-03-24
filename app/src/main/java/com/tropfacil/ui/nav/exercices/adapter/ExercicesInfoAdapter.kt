package com.tropfacil.ui.nav.exercices.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.R
import com.tropfacil.base.BaseViewHolder
import com.tropfacil.loadImage
import com.tropfacil.model.exercices.ExercicesInfo
import com.tropfacil.util.Constants.Companion.BASE_IMAGE_URL
import com.tropfacil.utils.ItemClickListener
import kotlinx.android.synthetic.main.raw_item_exercices_info.view.*

/**
 * Created by Nimesh Patel on 24-03-2022.
 */
class ExercicesInfoAdapter(
    private var context: Context,
    private var itemClickListener: ItemClickListener,
) : RecyclerView.Adapter<ExercicesInfoAdapter.ExercicesInfoViewHolder>() {
    private var exercicesInfoList: List<ExercicesInfo> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercicesInfoViewHolder {
        return ExercicesInfoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.raw_item_exercices_info, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExercicesInfoViewHolder, position: Int) {
        with(holder){
            with(exercicesInfoList[position]){
                itemView.title_tv.text = libelle
                itemView.exercise_iv.loadImage(context,"$BASE_IMAGE_URL${image}")
                itemView.description_tv.text = description
                itemView.description_tv.isVisible = description.trim().isNotEmpty()

            }
        }
    }

    override fun getItemCount(): Int = exercicesInfoList.size

    fun updateList(exercicesInfoList: List<ExercicesInfo>) {
        this.exercicesInfoList = exercicesInfoList
        notifyDataSetChanged()
    }

    class ExercicesInfoViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun clear() {

        }
    }

}