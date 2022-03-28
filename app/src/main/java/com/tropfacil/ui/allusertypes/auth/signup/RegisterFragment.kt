package com.tropfacil.ui.allusertypes.auth.signup


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.ArrayAdapter

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.leust.data.Data.Companion.token

import com.tropfacil.R

import com.tropfacil.base.BaseFragment
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.data.provider.PreferenceProvider
import com.tropfacil.databinding.ActivityRegisterBinding
import com.tropfacil.model.RegisterRequest
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.utils.popups.SuccessOrFailurePopup
import kotlinx.android.synthetic.main.activity_account_settings.view.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class RegisterFragment : BaseFragment() {
    //  private lateinit var binding: FragmentLoginBinding
    private val viewModel by inject<RegisterViewModel>()
    lateinit var binding: ActivityRegisterBinding
    var languages = arrayOf("Miss", "Mr")
    private var qty = ""
    var spinner: Spinner? = null
    var textView_msg: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  initView()
        initListeners()
        /// setTextWatchers()
        initObservers()

        setupSpinner()
        clickListeners()

    }
    private fun clickListeners() {
        binding.includeLayout.imageView3.setOnClickListener {
            closeFragment()
        }
        /*binding.llChangePassword.setOnClickListener {
            findNavController().navigate(AccountSettingsFragmentDirections.actionHomeToAccountSettingsToChangePassword())
        }*/
    }
    private fun closeFragment() {
        closeAndResumePrevFrag(requireActivity(),null)
    }
    private fun initObservers() {
        /* viewModel.showLoading.observe(viewLifecycleOwner, { showLoading ->
             binding.progressBar.visibility = if (showLoading) View.VISIBLE else View.GONE
         })
         viewModel.showError.observe(viewLifecycleOwner, {
             showErrorMsg(it)
         })*/




        lifecycleScope.launch {
            viewModel._registerStateFlow.collect { it ->
                when (it) {
                    is SafeApiCall.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        binding.progressBar.isVisible = false
                        showErrorMsg(it.exception)
                    }
                    is SafeApiCall.SuccessRegister -> {
                        binding.progressBar.isVisible = false
                        findNavController().navigate(RegisterFragmentDirections.actionInitialFragmentToLoginFragment())

                    }
                    else -> {}
                }
            }
        }
    }


    private fun initListeners() {
        binding.signin.setOnClickListener {
            it.hideKeyboard()
            // findNavController().navigate(LoginFragmentDirections.actionRegisterManuallyFragmentToLoginFragment())
        }

        /*    binding.ivClose.setOnClickListener {
                it.hideKeyboard()
                findNavController().popBackStack()
            }
    */
        binding.btnSignIn.setOnClickListener {
            token = PreferenceProvider(requireContext()).getUserToken()

            it.hideKeyboard()
            if (binding.nameEt.getText().toString().trim().isEmpty()) {
                Toast.makeText(
                    requireContext(), "Please enter Name",
                    Toast.LENGTH_SHORT
                ).show()
                binding.nameEt.requestFocus();
            } else if (binding.edtpernom.getText().toString().trim().isEmpty()) {
                Toast.makeText(
                    requireContext(), "Please enter lastname",
                    Toast.LENGTH_SHORT
                ).show()
                binding.edtpernom.requestFocus();
            } else if (binding.edtemail.getText().toString().trim().isEmpty()) {
                Toast.makeText(
                    requireContext(), "Please enter email",
                    Toast.LENGTH_SHORT
                ).show()
                binding.edtemail.requestFocus();
            } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.edtemail.getText().toString().trim())
                    .matches()
            ) {
                Toast.makeText(
                    requireContext(), getString(R.string.validation_input_email_invalid),
                    Toast.LENGTH_SHORT
                ).show()
                binding.edtemail.requestFocus();
            } else if (binding.usename.getText().toString().trim().isEmpty()) {
                Toast.makeText(
                    requireContext(), "Please enter username",
                    Toast.LENGTH_SHORT
                ).show()
                binding.usename.requestFocus();
            } else {
                val registerReq = RegisterRequest(
                    binding.edtemail.text.toString(),
                    binding.usename.text.toString(),
                    binding.nameEt.text.toString(),
                    binding.edtpernom.text.toString(),
                    qty,
                    //binding.edtPassword.text.toString(),
                )
                viewModel.registerUser(registerReq)

                //Call your API/function here
            }

        }
    }

    private fun setupSpinner() {
        binding.spinnerTv.text = resources.getStringArray(R.array.civility_code)[0]

        /* var languages = arrayOf("Miss", "Mr")
         val spinner = binding.spinnernew
             val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
         spinner.adapter = arrayAdapter
 */

        binding.spinnernew.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {

                //  binding.spinnernew.text = resources.getStringArray(R.array.civility_code)[position]
                val text: String = binding.spinnernew.getSelectedItem().toString()
                if (text.equals("Miss")) {
                    qty = "2"
                } else {
                    qty = "1"
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


    }


    /* private fun enableDisableButton() {
         if (binding.edtEmail.text.toString().isNotEmpty()
             && binding.edtFirstName.text.toString().isNotEmpty()
             && binding.edtPhoneNumber.text.toString().isNotEmpty()
             && binding.edtPassword.text.toString().isNotEmpty()
         ) {
             binding.btnCreateAccount.background = AppCompatResources.getDrawable(
                 requireContext(),
                 R.drawable.bg_button_black
             )
             binding.btnCreateAccount.setTextColor(
                 ContextCompat.getColor(
                     requireContext(),
                     R.color.white
                 )
             )
             binding.btnCreateAccount.isEnabled = true
             binding.btnCreateAccount.isClickable = true
         } else {
             binding.btnCreateAccount.background = AppCompatResources.getDrawable(
                 requireContext(),
                 R.drawable.bg_button_grey
             )
             binding.btnCreateAccount.setTextColor(
                 ContextCompat.getColor(
                     requireContext(),
                     R.color.black
                 )
             )
             binding.btnCreateAccount.isEnabled = false
             binding.btnCreateAccount.isClickable = false
         }
     }

 */
/*    private fun isValidForm(): Boolean {
        var isValid = true
        val email = binding.edtemail.text.toString()
        val firstName = binding.edtNom.text.toString()
        val phoneNumber = binding.edtPhoneNumber.text.toString()
        val password = binding.edtPassword.text.toString()

        if (!isValidEmailId(email)) {
            isValid = false
            binding.tvErrEmail.visible()
            binding.tvErrEmail.text = getString(R.string.err_email_id)
        } else binding.tvErrEmail.gone()

        if (TextUtils.isEmpty(firstName)) {
            isValid = false
            binding.tvErrFirstName.visible()
            binding.tvErrFirstName.text = getString(R.string.please_enter_first_name)
        } else binding.tvErrFirstName.gone()

        if (!isValidPhoneNumber(phoneNumber)) {
            isValid = false
            binding.tvErrPhoneNumber.visible()
            binding.tvErrPhoneNumber.text = getString(R.string.err_phone_number)
        } else binding.tvErrPhoneNumber.gone()

//        if (!isValidPassword(password)) {
//            isValid = false
//            binding.tvErrPassword.visible()
//            binding.tvErrPassword.text = getString(R.string.err_password)
//        } else binding.tvErrPassword.gone()

        if (TextUtils.isEmpty(password)) {
            isValid = false
            binding.tvErrPassword.visible()
            binding.tvErrPassword.text = getString(R.string.please_enter_password)
        } else binding.tvErrPassword.gone()
        return isValid
    }

    private fun initView() {
        binding.edtEmail.requestFocus()
        binding.tvLoginHere.paintFlags =
            binding.tvLoginHere.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }*/

    public fun showPopUp(popupMsg: String) {
        SuccessOrFailurePopup.newInstance {
            onConfirm = {
                //  findNavController().navigate(RegisterManuallyFragmentDirections.actionRegisterManuallyFragmentToVerificationCodeFragment())
            }
            message = popupMsg
            isSuccessPopUp = 1
            successBtnName = getString(R.string.verify)
        }.show(childFragmentManager, SuccessOrFailurePopup.TAG)

    }
}