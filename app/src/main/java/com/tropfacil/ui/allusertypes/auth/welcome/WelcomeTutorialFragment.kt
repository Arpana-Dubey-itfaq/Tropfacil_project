package com.tropfacil.ui.allusertypes.auth.welcome

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.databinding.FragmentGuestTutorialBinding

import com.tropfacil.model.GuestTutorialViewPagerModel
import java.text.FieldPosition
import android.graphics.drawable.Drawable

import androidx.viewpager.widget.ViewPager.OnPageChangeListener


class WelcomeTutorialFragment : BaseFragment(), GuestAdapter.OnCourseHistoryListener {
    private lateinit var binding: FragmentGuestTutorialBinding
    private lateinit var adapter: GuestAdapter
    private var guestTutorialViewPagerModel: ArrayList<GuestTutorialViewPagerModel> = ArrayList()
    private var dotscount = 0
    private var position = 0
    private var dots: List<ImageView>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuestTutorialBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            addList()
        }
        clickListener()

    }

    private fun clickListener() {
        binding.next.setOnClickListener {
            when (position) {
                0 -> {
                    binding.viewPager.currentItem = 1
                    position = 1
                }
                1 -> {
                    binding.viewPager.currentItem = 2
                    position = -1
                }
                else -> findNavController().navigate(WelcomeTutorialFragmentDirections.actionInitialFragmentToLoginFragment())
            }
        }
        binding.tvSkip.setOnClickListener {
            findNavController().navigate(WelcomeTutorialFragmentDirections.actionInitialFragmentToLoginFragment())
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun addList() {
        guestTutorialViewPagerModel.clear()
        guestTutorialViewPagerModel.add(
            GuestTutorialViewPagerModel(
                ContextCompat.getDrawable(requireContext(), R.drawable.review_course_img),
                requireContext().getString(R.string.la_meilleure_cole_en_ligne),
                requireContext().getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor_incididunt_ut_labore_et_dolore_magna_aliqua),
                // requireContext().getColor(R.color.pink),
                //requireContext().getColor(R.color.view_pager_shade_color1),
            )
        )
        guestTutorialViewPagerModel.add(
            GuestTutorialViewPagerModel(
                ContextCompat.getDrawable(requireContext(), R.drawable.screen2),
                requireContext().getString(R.string.la_meilleure_cole_en_ligne),
                requireContext().getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor_incididunt_ut_labore_et_dolore_magna_aliqua),

                )
        )
        guestTutorialViewPagerModel.add(
            GuestTutorialViewPagerModel(
                ContextCompat.getDrawable(requireContext(), R.drawable.review_course_img),
                requireContext().getString(R.string.la_meilleure_cole_en_ligne),
                requireContext().getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor_incididunt_ut_labore_et_dolore_magna_aliqua),

                )
        )
        adapter = GuestAdapter(
            guestTutorialViewPagerModel,
            requireContext(),
            requireActivity(),
            this@WelcomeTutorialFragment
        )
        binding.viewPager.adapter = adapter

        viewPager()
    }

    private fun viewPager() {
        dotscount = adapter.count

        val dots = arrayOfNulls<ImageView>(dotscount)

        for (i in 0 until dotscount) {
            dots[i] = ImageView(requireContext())
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.inactive_circle
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            binding.sliderDots!!.addView(dots[i], params)
        }
        dots[0]?.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(), R.drawable.active_circle
            )
        )

        binding.viewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(), R.drawable.inactive_circle
                        )
                    )
                }
                dots[position]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(), R.drawable.active_circle
                    )
                )
            }
        })
    }


    override fun onCourseDetails(position: Int) {
        when (position) {
            0 -> binding.viewPager.currentItem = 1
            1 -> binding.viewPager.currentItem = 2
            else -> findNavController().navigate(WelcomeTutorialFragmentDirections.actionInitialFragmentToLoginFragment())
        }
    }

    override fun onSkipClicked() {
        findNavController().navigate(WelcomeTutorialFragmentDirections.actionInitialFragmentToLoginFragment())
    }

}