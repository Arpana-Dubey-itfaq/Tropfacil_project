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
import com.tropfacil.data.Theme
import com.tropfacil.data.Parcour
import com.tropfacil.data.Soustheme
import com.tropfacil.databinding.ItemTabRecommededExercisenewBinding

import com.tropfacil.util.interfaces.HomeToCourseDetailsListener
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.tropfacil.BuildConfig
import com.tropfacil.home.adapter.RecommededExcerciseFragmentNew.Companion.TAG


class HomeAdapternew(val context: Context, val theme: Theme, val isboolean: Boolean, private val homeToCourseDetailsListener: HomeToCourseDetailsListener
) :
    RecyclerView.Adapter<HomeAdapternew.PageHolder>()
{


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
            LayoutInflater.from(context)
                .inflate(R.layout.item_tab_recommeded_exercisenew, parent, false)
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
        if (isboolean) {
            return theme.parcours.size
        } else {
            return theme.sousthemes.size
        }
    }
        override fun onBindViewHolder(holder: PageHolder, position: Int) {
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(16))
           if(isboolean) {
               val ItemsViewModel = theme.parcours[position]
               holder.textView.text = ItemsViewModel.libelle

               //   https://rc-tropfacile.onlineformapro.com/php5/competences/themes/defaut/img/themes/bannieres/st_developper_son_efficacite_personnelle_au_quotidien.png
           /*    Glide.with(context).setDefaultRequestOptions(RequestOptions().timeout(100000))
                   .load("http://rc-tropfacile.onlineformapro.com/php5/competences/themes/defaut/img/themes/bannieres/th_13.png")
                   .into(holder.bannerImage);*/

               ItemsViewModel.image.let {
                   Glide.with(context)
                       .load(BuildConfig.LOAD_IMAGE + it)
                       .placeholder(R.drawable.logo)
                       .into(holder.bannerImage)
               }


               // holder.bannerImage.setImageResource("https://rc-tropfacile.onlineformapro.com"+ItemsViewModel.image)
              /* Glide.with(context)
                   .load("https://rc-tropfacile.onlineformapro.com"+ItemsViewModel.image)
                   .apply(requestOptions)
                   .skipMemoryCache(true)//for caching the image url in case phone is offline
                   .into(holder.bannerImage)
*/


               //var id = context.getResources().getIdentifier("https://rc-tropfacile.onlineformapro.com" + ItemsViewModel.image, null, null);
               //holder.bannerImage.setImageResource(id);
               //holder.imgNext.setImageResource(id)
               holder.imgNext.setOnClickListener {

                   homeToCourseDetailsListener.navigateToCourseDetailsScreenViaParCour(ItemsViewModel)
               }
           }else{

                val Itemsnew = theme.sousthemes[position]
              // Log.debug("valuedata",Itemsnew.toString())
               holder.textView.text = Itemsnew.libelle
               var str = Itemsnew.icone
               val output = str.replace("res:", "")

               //  holder.bannerImage.setImageResource("https://rc-tropfacile.onlineformapro.com"+Itemsnew.icone.replace("res:",""))
               output.let {
                   Glide.with(context)
                       .load(BuildConfig.LOAD_IMAGE +"/php5/competences/themes/defaut/img/themes/bannieres/"+ it.replace("res:"," "))
                       .placeholder(R.drawable.logo)
                       .into(holder.bannerImage)
               }
               /* Itemsnew.icone?.let {
                    Glide.with(context).load("https://rc-tropfacile.onlineformapro.com/php5/competences/themes/defaut/img/themes/bannieres/").into(holder.bannerImage);
                }*/
                 // Glide.with(context).load("https://rc-tropfacile.onlineformapro.com/php5/competences/themes/defaut/img/themes/bannieres/st_developper_son_efficacite_personnelle_au_quotidien.png").into(holder.bannerImage);
               holder.imgNext.setOnClickListener {

                   homeToCourseDetailsListener.navigateToCourseDetailsScreenViaSousTheme(Itemsnew)
               }



           }



       // Glide.with(context).load("https://rc-tropfacile.onlineformapro.com/"+ItemsViewModel.image).into(holder.bannerImage)

        }

        /*  fun updateData(missionList: List<MissionData>) {
              list = missionList
              notifyDataSetChanged()
          }*/
    }

private fun ImageView.setImageResource(image: String) {

}

private fun ImageView.setImageDrawable(image: String) {

}
