package com.tropfacil.home.adapter

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tropfacil.R
import com.tropfacil.data.Theme
import com.tropfacil.databinding.FragmentHomeBinding
import com.tropfacil.databinding.FragmentTabRecommededExerciseBinding
import com.tropfacil.databinding.ItemTabRecommededExerciseBinding
import com.tropfacil.databinding.ItemTabRecommededExercisenewBinding
import com.tropfacil.home.view.HomeFragment
import com.tropfacil.mycourses.view.Course_chapter_Fragment
import com.tropfacil.mycourses.view.Course_per_chapter_Fragment

import com.tropfacil.main.view.MainActivity
import com.tropfacil.mycourses.view.Course_chapter_detail_Fragment


class HomeAdapternew(
) : RecyclerView.Adapter<HomeAdapternew.ViewHolder>() {


    inner class ViewHolder(val bind: ItemTabRecommededExercisenewBinding) :
        RecyclerView.ViewHolder(bind.root)
   // lateinit var binding: ItemTabRecommededExercisenewBinding
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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

            // -- Put the other views in your recycler view item here --
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      //  val ItemsViewModel = titleList[position]


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
        return 1
    }

    /*  fun updateData(missionList: List<MissionData>) {
          list = missionList
          notifyDataSetChanged()
      }*/
}