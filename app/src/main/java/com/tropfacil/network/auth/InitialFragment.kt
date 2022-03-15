package com.tropfacil.network.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.tropfacil.R
import com.tropfacil.base.BaseFragment
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.databinding.FragmentInitialBinding
import com.tropfacil.utils.changeStatusBarColor

import org.koin.android.ext.android.inject
import java.util.*


class InitialFragment : BaseFragment() {
    private var navOptions: NavOptions? = null
    private lateinit var binding: FragmentInitialBinding
    private val preferenceProvider by inject<PreferenceProvider>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.initialFragment, true)
            .build()

       /* //TODO needs to check again according to the flow
        when {
            isUserLoggedIn() -> findNavController().navigate(
                R.id.action_initialFragment_to_homeShopFragment,
                null,
                navOptions
            )
            isGuestUser() && isGuestUserRelaunchHomeScreen() -> findNavController().navigate(
                R.id.action_initialFragment_to_homeShopFragment,
                null,
                navOptions
            )
        }
        if (!isUserLoggedIn())
            preferenceProvider.putBoolean(PREF_GUEST_USER_RELAUNCH_HOME_SCREEN, true)
        saveUUID()*/
    }

   /* private fun saveUUID() {
        if (!preferenceProvider.getBoolean(PREF_FIRST_TIME_APP_OPEN, false)) {
            preferenceProvider.putString(PREF_UUID, UUID.randomUUID().toString())
            preferenceProvider.putBoolean(PREF_FIRST_TIME_APP_OPEN, true)
        }
    }
*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().changeStatusBarColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black
            ), false
        )
        binding = FragmentInitialBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

    }

    private fun initListeners() {
     /*   binding.btnRegister.setOnClickListener {
            findNavController().navigate(InitialFragmentDirections.actionInitialFragmentToRegisterOptionsFragment())
        }
*/
       /* binding.btnLogin.setOnClickListener {
            findNavController().navigate(InitialFragmentDirections.actionInitialFragmentToLoginFragment())
        }
*/
      /*  binding.btnGuest.setOnClickListener {
            preferenceProvider.putBoolean(PREF_IS_USER_LOGGED_IN, false)
            preferenceProvider.putBoolean(PREF_GUEST_USER, true)
            preferenceProvider.putBoolean(PREF_GUEST_USER_RELAUNCH_HOME_SCREEN, true)
            findNavController().navigate(InitialFragmentDirections.actionInitialFragmentToHomeShopFragment())
        }

        binding.ivClose.setOnClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment)
            val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount
            if (backStackEntryCount != 0) {
                findNavController().popBackStack()
            } else {
                activity?.finishAffinity()
            }
        }*/
    }
}