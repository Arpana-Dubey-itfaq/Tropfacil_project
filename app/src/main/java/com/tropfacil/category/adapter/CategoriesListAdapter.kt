package com.tropfacil.category.adapter

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.R
import com.tropfacil.category.model.CategoryList
import com.tropfacil.databinding.*


class CategoriesListAdapter(
    var list: MutableList<CategoryList>
) : RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {


    var selectedItems = MutableLiveData<String>()


    private fun getSelectedItems(): String? {
        val mylist = list.filter { it.isSelected == true }
        val csv = arrayListOf<String>()
        for (item in mylist) {
            csv.add(item.id.toString())
        }
        return csv.joinToString()
    }

    inner class ViewHolder(val bind: ItemAllCategoryBinding) :
        RecyclerView.ViewHolder(bind.root)

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemAllCategoryBinding =
            ItemAllCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)

        if (item.isSelected) {
            holder.bind.imgCategory.setImageResource(R.drawable.cat1_active)
            holder.bind.tvCatName.setTextColor(ContextCompat.getColor(context, R.color.green))
        } else {
            holder.bind.imgCategory.setImageResource(R.drawable.cat1_inactive)
            holder.bind.tvCatName.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
        holder.bind.tvCatName.text = item.name
        holder.bind.catImage.setOnClickListener {
            val item = list.get(position)
            if (item.isSelected) {
                holder.bind.imgCategory.setImageResource(R.drawable.cat1_inactive)
                holder.bind.tvCatName.setTextColor(ContextCompat.getColor(context, R.color.black))
            } else {
                holder.bind.imgCategory.setImageResource(R.drawable.cat1_active)
                holder.bind.tvCatName.setTextColor(ContextCompat.getColor(context, R.color.green))
            }
            item.isSelected = !item.isSelected
            notifyItemChanged(position)
            selectedItems.value = getSelectedItems()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    /*  fun updateData(missionList: List<MissionData>) {
          list = missionList
          notifyDataSetChanged()
      }*/
}