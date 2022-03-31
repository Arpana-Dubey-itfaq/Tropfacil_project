package com.tropfacil.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.R
import com.tropfacil.base.BaseViewHolder
import com.tropfacil.loadImage
import com.tropfacil.model.search.SearchInfo
import com.tropfacil.util.Constants
import com.tropfacil.utils.ItemClickListener
import kotlinx.android.synthetic.main.item_search.view.*
import kotlinx.android.synthetic.main.item_search.view.progress_time
import kotlinx.android.synthetic.main.item_search.view.tv_time_percent
import kotlinx.android.synthetic.main.raw_item_course_info.view.*

/**
 * Created by Nimesh Patel on 30-03-2022.
 */
class SearchItemAdapter (
    private var context: Context,
    private var itemClickListener: ItemClickListener,
) : RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder>(), Filterable {

    private  var  searchInfoList: ArrayList<SearchInfo> = ArrayList()
    private var filterSearchInfoList: ArrayList<SearchInfo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        with(holder){
            with(filterSearchInfoList[position]){
                itemView.tv_category.text = if(elementLibelle?.trim()?.isNotEmpty() == true) childLibelle else parentLibelle
                itemView.tv_title.text = if(elementLibelle?.trim()?.isNotEmpty() == true) elementLibelle else childLibelle
                itemView.tv_description.text = if(elementDescription?.trim()?.isNotEmpty() == true) elementDescription else childDescription
                itemView.tv_description.isVisible = childDescription?.trim()?.isNotEmpty() == true
                itemView.iv_item_profile.loadImage(context,"${Constants.BASE_IMAGE_URL}${image}")
            }
        }
    }

    override fun getItemCount(): Int =filterSearchInfoList.size

    fun updateList(searchInfoList: ArrayList<SearchInfo>?) {
        this.searchInfoList = searchInfoList?: ArrayList()
        filterSearchInfoList= searchInfoList?: ArrayList()
        notifyDataSetChanged()
    }

    class SearchItemViewHolder (itemView: View) : BaseViewHolder(itemView) {
        override fun clear() {
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterSearchInfoList = if (charSearch.isEmpty()) {
                    searchInfoList?: ArrayList()
                } else {
                    val resultList = ArrayList<SearchInfo>()
                    for (row in searchInfoList) {
                        val searchKey = constraint.toString().trim().lowercase()
                        if (row.parentLibelle?.trim()?.lowercase()
                                ?.contains(searchKey) == true ||
                            row.childLibelle?.trim()?.lowercase()
                                ?.contains(searchKey) == true ||
                            row.elementLibelle?.trim()?.lowercase()
                                ?.contains(searchKey) == true
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList?: ArrayList()
                }
                val filterResults = FilterResults()
                filterResults.values = filterSearchInfoList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterSearchInfoList = results?.values as ArrayList<SearchInfo>
                notifyDataSetChanged()
            }

        }
    }


}