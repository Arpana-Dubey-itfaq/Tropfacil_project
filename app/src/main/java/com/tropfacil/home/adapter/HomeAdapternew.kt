package com.tropfacil.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tropfacil.R
import com.tropfacil.data.Parcour
import com.tropfacil.data.Soustheme
import com.tropfacil.databinding.ItemTabRecommededExercisenewBinding

import com.tropfacil.util.interfaces.HomeToCourseDetailsListener


class HomeAdapternew(
    val context: Context,
    val parcours: List<Parcour>,
    private val homeToCourseDetailsListener: HomeToCourseDetailsListener
) : RecyclerView.Adapter<HomeAdapternew.PageHolder>() {


    inner class ViewHolder(val bind: ItemTabRecommededExercisenewBinding) :
        RecyclerView.ViewHolder(bind.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(
            LayoutInflater.from(context).inflate(R.layout.item_tab_recommeded_exercisenew, parent, false)
        )



    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById<TextView>(R.id.tv_name)
        val imgNext: ImageView = view.findViewById<ImageView>(R.id.imgNext)
        val bannerImage: ImageView = view.findViewById<ImageView>(R.id.imgCourse)


        // imgNext
    }
        // sets the image to the imageview from our itemHolder class
       /* binding.imgCourse.set("https://rc-tropfacile.onlineformapro.com/"+ItemsViewModel.image)
        Glide.with(context).load(it).into(holder.imgProduct)
        Glide.with(this).load("https://rc-tropfacile.onlineformapro.com/"+ItemsViewModel.parcours.i).into(imageView);
        for (i in 0 until ti.length()) {
            // ID
            val id = jsonArray.getJSONObject(i).getString("id")
            Log.i("ID: ", id)
        }

            // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text
*/

    override fun getItemCount(): Int {
        return parcours.size
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val parCourseItem = parcours[position]
       holder.textView.text =parCourseItem.libelle
        holder.imgNext.setOnClickListener {

            homeToCourseDetailsListener.navigateToCourseDetailsScreenViaParCour(parCourseItem)
        }



       // Glide.with(context).load("https://rc-tropfacile.onlineformapro.com/"+ItemsViewModel.image).into(holder.bannerImage)

    }

    /*  fun updateData(missionList: List<MissionData>) {
          list = missionList
          notifyDataSetChanged()
      }*/
}