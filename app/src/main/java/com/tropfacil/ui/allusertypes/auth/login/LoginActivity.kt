package com.tropfacil.ui.allusertypes.auth.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View

import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope

import com.tropfacil.R
import com.tropfacil.base.BaseActivity

import com.tropfacil.network.BaseResponse
import com.tropfacil.network.auth.login.UserInfo
import com.tropfacil.network.request.LoginRequest
import com.tropfacil.network.service.SafeApiCall
import com.tropfacil.util.Status
import com.tropfacil.utils.popups.FailurePopup
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.inclued_seekbar.*
import kotlinx.android.synthetic.main.view_edit_password.*
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import com.tropfacil.databinding.ActivityLoginBinding


class LoginActivity : BaseActivity(), TextWatcher {
    lateinit var binding: ActivityLoginBinding
    private val viewModel by inject<LoginViewModel>()
    private var userAccountType = -1
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideStatusBar()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        actionBar?.isHideOnContentScrollEnabled
        /*   initViewModel()*/
      //  setupObserver();
      //  initViewClickListeners()
        initListeners()
        initObservers()
        //initFCM()
        // get reference to button

    }

    private fun initViewClickListeners() {
        // img_toolbar_back_arrow.gone()
        et_email_username.addTextChangedListener(this)
        edit_password.addTextChangedListener(this)
        edit_password.imeOptions = EditorInfo.IME_ACTION_DONE
        btn_sign_in.setOnClickListener {
            isValidLoginForm()
        }
        /*  btn_create_account.setOnClickListener {
              ChooseAccountTypeActivity.start(this)
          }
          img_toolbar_back_arrow.setOnClickListener {
              finish()
          }
          tv_forgot_password.setOnClickListener {
              ForgotPasswordActivity.start(this)
          }*/
        /*  tv_show.setOnClickListener {
              if (et_password.text.toString().isNotEmpty()) {
                  if (et_password.transformationMethod == null) {
                      tv_show.text = getString(R.string.show)
                      et_password.transformationMethod = PasswordTransformationMethod()
                  } else {
                      tv_show.text = getString(R.string.hide)
                      et_password.transformationMethod = null
                  }
              } else {
                  tv_show.text = getString(R.string.show)
                  et_password.transformationMethod = PasswordTransformationMethod()
              }

          }
  */
    }

  /*  private fun initViewModel() {

        // viewModel = ViewModelProvider(this, viewModelFactory)
        //.get(LoginViewModel::class.java)
        // viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)


        viewModel.showLoading.observe(this, Observer { showLoading ->
            progress_bar.visibility = if (showLoading) View.VISIBLE else View.GONE
        })
        viewModel.showError.observe(this, Observer { showError ->
            if (showError == null)
                return@Observer

            // handle error here
            FailurePopup.newInstance {
                message = showError.toString()
            }.show(supportFragmentManager, FailurePopup.TAG)
        })

        viewModel.loginLiveData.observe(this, Observer {
            onLoginSuccess(it)
        })

        viewModel.fcmTokenLiveData.observe(this, Observer {
            onTokenSetSuccessfully(it)
        })

    }
*/
   /* private fun setupObserver() {
        viewModel.loginLiveData.observe(this, Observer {
            viewModel.showLoading.observe(this, Observer { showLoading ->
                progress_bar.visibility = if (showLoading) View.VISIBLE else View.GONE
            })
            viewModel.showError.observe(this, Observer { showError ->
                if (showError == null)
                    return@Observer

                // handle error here
                FailurePopup.newInstance {
                    message = showError.toString()
                }.show(supportFragmentManager, FailurePopup.TAG)
            })
            viewModel.loginLiveData.observe(this, Observer {
                onLoginSuccess(it)
            })

        })
    }*/


    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel._loginStateFlow.collect {
                when (it) {
                    is SafeApiCall.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is SafeApiCall.Error -> {
                        binding.progressBar.isVisible = false
                        FailurePopup.newInstance {
                            message = it.exception.toString()
                        }.show(supportFragmentManager, FailurePopup.TAG)
                      //  showErrorMsg(it.exception)
                    }
                    is SafeApiCall.Success -> {
                        binding.progressBar.isVisible = false
                        //viewModel.syncGuestItems(getUUID())
                    }
                    else -> {

                    }
                }
            }
        }


    }


    private fun initListeners() {
        /*  binding.tvCreateAccount.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterManuallyFragment())
        }*/

        /*       binding.ivClose.setOnClickListener {
            it.hideKeyboard()
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment)
            val backStackEntryCount = navHostFragment?.childFragmentManager?.backStackEntryCount
            if (backStackEntryCount != 0) {
                findNavController().popBackStack()
            } else {
                activity?.finishAffinity()
            }
        }
        binding.textB.setOnClickListener {
            it.hideKeyboard()
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }*/

       /* binding.btnSignIn.setOnClickListener {
            //  it.hideKeyboard()
        //    if (isValidForm()) {
                viewModel.loginUsernew("v6yRZ5gsSPY0dS9imbUUySYuTdPGn5Wo",
                    LoginRequest(
                        binding.etEmailUsername.text.toString(),
                        binding.relPassword.editPassword.text.toString()
                    )
                )
          //  }
        }
*/
        /* binding.imgPassword.setOnClickListener {
            showHidePassword(binding.edtPassword, binding.imgPassword)
        }

        binding.edtEmail.doAfterTextChanged {
            enableDisableButton()
        }

        binding.edtPassword.doAfterTextChanged {
            enableDisableButton()
        }
        binding.btnFacebookLogin.setOnClickListener {
            onFacebookLoginClicked()
        }

        binding.btnGoogleLogin.setOnClickListener {
            signIn()
        }

        binding.tvLoginTermsConditions.setOnClickListener {
            openWebPage(requireContext(),requireContext().getString(R.string.terms_and_conditions_link))
        }

        binding.tvLoginPrivacyPolicy.setOnClickListener {
            openWebPage(requireContext(),requireContext().getString(R.string.privacy_policy_link))
        }
    }*/


    }


/*    private fun enableDisableButton() {
        if (binding.et_email_username.text.toString().isNotEmpty() && binding.edit_password.text.toString()
                .isNotEmpty()
        ) {
            binding.btn_sign_in.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_button_black
            )
            binding.btnLogin.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.btnLogin.isEnabled = true
            binding.btnLogin.isClickable = true
        } else {
            binding.btnLogin.background = AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.bg_button_grey
            )
            binding.btnLogin.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            binding.btnLogin.isEnabled = false
            binding.btnLogin.isClickable = false
        }
    }*/

    private fun isValidForm(): Boolean {
        var isValid = true
        val email = binding.etEmailUsername.text.toString()
        val password = binding.relPassword.editPassword.text.toString()

       /* if (!isValidEmailId(email)) {
            isValid = false

            binding.tvPassword.text = getString(R.string.please_enter_password)
        } else binding.etEmailUsername.toString()
*/
        /*if (!isValidPassword(password)) {
            isValid = false
            binding.tvErrPassword.visible()
            binding.tvErrPassword.text = getString(R.string.err_password)
        } else binding.tvErrPassword.gone()*/
        if (TextUtils.isEmpty(password)) {
            isValid = false
            //  binding.relPassword.editPassword.visible()
            binding.tvPassword.text = getString(R.string.please_enter_password)
        } else binding.relPassword.editPassword.toString()

        return isValid
    }



    /*private fun onLoginSuccess(it: Login_resoponse) {
        // Toast.makeText(applicationContext, "this is toast message", Toast.LENGTH_SHORT).show()
        if (it.success?.equals(true) == true) {
            Toast.makeText(this@LoginActivity, "Its a toast!", Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        this@LoginActivity.startActivity(intent)
    }
*/
    /*else if (it.statusCode == 200) {
            it.payload?.userInfo?.accountType?.let { userType ->
                userAccountType = userType
            }
            //viewModel.setFcmToken()
        } else {
            // handle error here
            FailurePopup.newInstance {
                message = it.message.toString()
            }.show(supportFragmentManager, FailurePopup.TAG)

        }*/


    private fun displayErrorMessageForUnverifiedAccounts(
        errorMessage: String,
        accountType: Int,
        userInfo: UserInfo
    ) {
        if (accountType == 0) {
            //viewModel.savePassword(edit_password.text.toString())
            FailurePopup.newInstance {
                message = errorMessage
                cancellable = false
                onConfirm = {
                    // VerificationCodeActivity.start(this@LoginActivity, true, userInfo)
                }
            }.show(supportFragmentManager, FailurePopup.TAG)
        } else {
            FailurePopup.newInstance {
                message = errorMessage
                cancellable = false
            }.show(supportFragmentManager, FailurePopup.TAG)
        }

    }

    private fun onTokenSetSuccessfully(it: BaseResponse) {
        navigateToProperHomePage(userAccountType)
//        if (!isFromCartActivity)
//        else
//            EventBus.getDefault().post("success")
        finish()
    }

    private fun navigateToProperHomePage(userType: Int) {
        when (userType) {
            /*   AccountTypes.RegularUserType.value -> {
                   HomeActivity.start(this)
               }
               AccountTypes.HealthProfessionalType.value -> {
                   HealthProfHomeActivity.start(this)
               }
               AccountTypes.DeliveryManType.value -> {
                   DeliveryHomeActivity.start(this)
               }*/
        }
    }

    private fun isValidLoginForm() {
        var isValid = true
        val email = et_email_username.text.toString()
        val password = edit_password.text.toString()

//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            isValid = false
//            et_email_username.error = getString(R.string.err_msg_email_field)
//        }

        /*if (!Pattern.matches(PASSWORD_REGX, password)) {
            isValid = false
            edit_password.error = getString(R.string.err_msg_password_field)
        }*/

       /* if (isValid) {
            val loginRequest = LoginRequest(
                loginName = email,
                password = password,
                token = "v6yRZ5gsSPY0dS9imbUUySYuTdPGn5Wo"
            )

            viewModel.loginUser(loginRequest)
        }*/

    }


    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        setEnableOrDisableBtn()
    }

    private fun setEnableOrDisableBtn() {
        if (et_email_username.text.toString().isNotEmpty()
            && edit_password.text.toString().isNotEmpty()
        ) {
            btn_sign_in.isEnabled = true
            btn_sign_in.background =
                ContextCompat.getDrawable(this, R.drawable.ic_rounded_corner_white)
        } else {
            btn_sign_in.isEnabled = false
            btn_sign_in.background =
                ContextCompat.getDrawable(this, R.drawable.ic_disabled_button_gray)
        }
    }
   /* protected fun showErrorMsg(errorMsg: String) {
        SuccessOrFailurePopup.newInstance {
            onConfirm = {}
            message = errorMsg
            successBtnName = getString(R.string.ok)
            isSuccessPopUp = 2
            cancellable = false
        }.show(this, SuccessOrFailurePopup.TAG)
    }*/
}