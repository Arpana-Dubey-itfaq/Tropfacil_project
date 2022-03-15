package com.tropfacil.ui.allusertypes.auth.welcome

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation.findNavController
import androidx.viewpager.widget.PagerAdapter

import com.tropfacil.R
import com.tropfacil.model.GuestTutorialViewPagerModel

class GuestAdapter(
    models: ArrayList<GuestTutorialViewPagerModel>,
    context: Context,
    activity: Activity,
    private val listener: OnCourseHistoryListener
) :
    PagerAdapter() {
    private val models: ArrayList<GuestTutorialViewPagerModel> = models
    private val context: Context = context
    private val activity = activity
    override fun getCount(): Int {
        return models.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.activity_welcome_screen, container, false)
        val guestImageSlider: ImageView = view.findViewById(R.id.stylish1)
        //val constGuest: ConstraintLayout = view.findViewById(R.id.constGuest)
        val tvSliderTitle: AppCompatTextView = view.findViewById(R.id.textView)
        val tvSliderDescription: AppCompatTextView = view.findViewById(R.id.textView2)
       /// val guestImageShade: AppCompatImageView = view.findViewById(R.id.guestImageShade)
    val next: Button = view.findViewById(R.id.next)

        // constGuest.setBackgroundColor(models[position].color)

        guestImageSlider.setImageDrawable(models[position].image)

      //  guestImageShade.setColorFilter(models[position].shadeColor)
        next.setOnClickListener {
            listener.onCourseDetails()
         }
        tvSliderTitle.text = models[position].title
        tvSliderDescription.text = models[position].desc

        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
    interface OnCourseHistoryListener {
        fun onCourseDetails()
    }
}