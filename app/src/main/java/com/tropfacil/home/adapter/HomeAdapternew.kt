package com.tropfacil.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tropfacil.R
import com.tropfacil.data.Parcour
import com.tropfacil.databinding.ItemTabRecommededExercisenewBinding

import com.tropfacil.main.view.MainActivity
import com.tropfacil.mycourses.view.Course_chapter_detail_Fragment


class HomeAdapternew( val context: Context,val parcours: List<Parcour>) : RecyclerView.Adapter<HomeAdapternew.PageHolder>() {


    inner class ViewHolder(val bind: ItemTabRecommededExercisenewBinding) :
        RecyclerView.ViewHolder(bind.root)
   // lateinit var binding: ItemTabRecommededExercisenewBinding
   // lateinit var context: Context

    /*override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        context = parent.context

        val binding: ItemTabRecommededExercisenewBinding =
            ItemTabRecommededExercisenewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
             binding.imgNext.setOnClickListener {
                 val optionsFrag = Course_chapter_detail_Fragment()
                 (context as MainActivity).getSupportFragmentManager().beginTransaction()
                     .replace(R.id.fragment_container, optionsFrag, "MyCourseFragment").addToBackStack(null)
                     .commit()
             }

       *//* Glide.with(context).load(it)
            .into(convertView.findViewById<ImageView>(R.id.imageView))
*//*
        // -- Put the other views in your recycler view item here --
        return ViewHolder(binding)
    }*/
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
        val ItemsViewModel = parcours[position]
        holder.textView.text =ItemsViewModel.libelle
        holder.imgNext.setOnClickListener {
            val optionsFrag = Course_chapter_detail_Fragment()
            (context as MainActivity).getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, optionsFrag, "MyCourseFragment").addToBackStack(null)
                .commit()
        }



       // Glide.with(context).load("https://rc-tropfacile.onlineformapro.com/"+ItemsViewModel.image).into(holder.bannerImage)

    }

    /*  fun updateData(missionList: List<MissionData>) {
          list = missionList
          notifyDataSetChanged()
      }*/
}