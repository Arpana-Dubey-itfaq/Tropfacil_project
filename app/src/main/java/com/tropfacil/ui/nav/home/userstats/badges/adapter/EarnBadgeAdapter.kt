package com.tropfacil.ui.nav.home.userstats.badges.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.R
import com.tropfacil.base.BaseViewHolder
import com.tropfacil.ui.nav.exercices.adapter.ExercicesInfoAdapter
import com.tropfacil.utils.ItemClickListener

/**
 * Created by Nimesh Patel on 24-03-2022.
 */
class EarnBadgeAdapter(
private var context: Context,
private var itemClickListener: ItemClickListener,
) : RecyclerView.Adapter<EarnBadgeAdapter.EarnBadgeViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarnBadgeViewHolder {
        return EarnBadgeViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.raw_item_earn_badge, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EarnBadgeViewHolder, position: Int) {

    }

    override fun getItemCount(): Int =10

    class EarnBadgeViewHolder (itemView: View) : BaseViewHolder(itemView) {
        override fun clear() {

        }
    }
}