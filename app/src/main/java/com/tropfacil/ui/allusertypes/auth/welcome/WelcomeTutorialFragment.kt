package com.tropfacil.ui.allusertypes.auth.welcome

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


class WelcomeTutorialFragment : BaseFragment(),GuestAdapter.OnCourseHistoryListener {
    private lateinit var binding: FragmentGuestTutorialBinding
    private lateinit var adapter: GuestAdapter
    private var guestTutorialViewPagerModel: ArrayList<GuestTutorialViewPagerModel> = ArrayList()
    private var dotscount = 0

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
        binding.btnConnection.setOnClickListener {
          //  findNavController().navigate(GuestTutorialFragmentDirections.actionGuestSignInFragment())
        }
        binding.btnInscription.setOnClickListener {
          //  findNavController().navigate(GuestTutorialFragmentDirections.actionGuestSignInFragment())
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun addList() {
        guestTutorialViewPagerModel.clear()
        guestTutorialViewPagerModel.add(
            GuestTutorialViewPagerModel(
                requireContext().getDrawable(R.drawable.review_course_img),
                requireContext().getString(R.string.la_meilleure_cole_en_ligne),
                requireContext().getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor_incididunt_ut_labore_et_dolore_magna_aliqua),
               // requireContext().getColor(R.color.pink),
                //requireContext().getColor(R.color.view_pager_shade_color1),
            )
        )
        guestTutorialViewPagerModel.add(
            GuestTutorialViewPagerModel(
                requireContext().getDrawable(R.drawable.screen2),
                requireContext().getString(R.string.la_meilleure_cole_en_ligne),
                requireContext().getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor_incididunt_ut_labore_et_dolore_magna_aliqua),

            )
        )
        guestTutorialViewPagerModel.add(
            GuestTutorialViewPagerModel(
                requireContext().getDrawable(R.drawable.review_course_img),
                requireContext().getString(R.string.la_meilleure_cole_en_ligne),
                requireContext().getString(R.string.lorem_ipsum_dolor_sit_amet_consectetur_adipiscing_elit_sed_do_eiusmod_tempor_incididunt_ut_labore_et_dolore_magna_aliqua),

                )
        )
        adapter = GuestAdapter(guestTutorialViewPagerModel, requireContext(), requireActivity(),this@WelcomeTutorialFragment)
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



    override fun onCourseDetails() {
        findNavController().navigate(WelcomeTutorialFragmentDirections.actionInitialFragmentToLoginFragment())
    }
}