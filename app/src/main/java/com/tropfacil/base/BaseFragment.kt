package com.tropfacil.base

import android.content.Context
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.google.android.material.bottomsheet.BottomSheetBehavior

import com.tropfacil.R
import com.tropfacil.closeAndResumePrevFrag
import com.tropfacil.updateStatusBarColor
import com.tropfacil.utils.popups.SuccessOrFailurePopup

abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateStatusBarColor(
            ContextCompat.getColor(requireContext(), R.color.pinkdark),
            requireActivity()
        )
    }
    fun showHidePassword(password: EditText, imgEye: ImageView) {
        if (password.text.toString().isNotEmpty()) {
            if (password.transformationMethod == null) {
                imgEye.setImageResource(R.drawable.icon_show_password)
                password.transformationMethod = PasswordTransformationMethod()
            } else {
                imgEye.setImageResource(R.drawable.icon_hide_password)
                password.transformationMethod = null
            }
            password.setSelection(password.length())
        } else {
            imgEye.setImageResource(R.drawable.icon_show_password)
            password.transformationMethod = PasswordTransformationMethod()
        }
    }

    protected fun showErrorMsg(errorMsg: String) {
        SuccessOrFailurePopup.newInstance {
            onConfirm = {}
            message = errorMsg
            successBtnName = getString(R.string.ok)
            isSuccessPopUp = 2
            cancellable = false
        }.show(childFragmentManager, SuccessOrFailurePopup.TAG)
    }

    protected fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}