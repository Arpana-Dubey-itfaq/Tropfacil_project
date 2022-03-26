package com.tropfacil.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Nimesh Patel on 24-03-2022.
 */
abstract class BaseViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    var currentPosition: Int = 0
        private set

    protected abstract fun clear()

    fun onBind(position: Int) {
        currentPosition = position
        clear()
    }
}