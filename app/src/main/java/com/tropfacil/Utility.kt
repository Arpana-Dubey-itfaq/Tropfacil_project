package com.tropfacil

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tropfacil.base.BaseActivity
import kotlinx.android.synthetic.main.imagelayout.view.*

/**
 * Created by Nimesh Patel on 21-03-2022.
 */
fun closeAndResumePrevFrag(activity: Activity, bundle: Bundle?) {
    hideSoftKeyboard(activity)
    try {
        (activity as BaseActivity).resumeFragmentListener?.onFragmentResume(bundle)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    (activity as BaseActivity).supportFragmentManager.popBackStack()
}

fun hideSoftKeyboard(activity: Activity) {
    try {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                0
            )
        }
    } catch (e: Exception) {
    }
}

fun textCapSentences(msg:String):String{
    var result=""
    if(msg.isNotEmpty()){
        val tempMsgs= msg.trim().split(" ")

        tempMsgs.forEach { currentString ->
            result += currentString.replaceFirstChar { it.uppercase() }+" "
        }
    }
    return result.trim()
}

fun EditText.isValidEmail(context: Context):Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    val currentEmail = this.text.toString().trim()
    if(currentEmail.isEmpty() || currentEmail.matches(emailPattern.toRegex())){
      this.error = context.getString(R.string.str_please_enter_valid_email)
        return false
    }else this.error = null
    return true
}

fun EditText.isValidPassword(context: Context):Boolean {
    val passwordPattern =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-.!;:@$!%*?&^+='])[A-Za-z\\d-.!;:@$!%*?&^+=']{6,}$"
    val currentPassword = this.text.toString().trim()
    if(currentPassword.isEmpty() || currentPassword.matches(passwordPattern.toRegex())){
        this.error = context.getString(R.string.str_please_enter_valid_password)
        return false
    }else this.error = null
    return true
}

fun EditText.isValidPasswordotp(context: Context):Boolean {
    val passwordPattern =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-.!;:@$!%*?&^+='])[A-Za-z\\d-.!;:@$!%*?&^+=']{6,}$"
    val currentPassword = this.text.toString().trim()
    if(currentPassword.isEmpty() || currentPassword.matches(passwordPattern.toRegex())){
        this.error = context.getString(R.string.str_please_enter_valid_otp)
        return false
    }else this.error = null
    return true
}

fun updateStatusBarColor(colorResource: Int, activity: Activity) {
    activity.window.statusBarColor = colorResource
}

fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadSrcImage(context: Context, src: Int) {
    Glide.with(context).load(src).into(this)
}

fun getDurationFromSeconds(seconds:Int):String{
    return when (seconds) {
        in 0..(60) -> {
            (seconds).toInt().toString() + " seconds"
        }
        in (60)..(60 * 60) -> {
            (seconds / (60)).toInt().toString() + " minutes"
        }
        else ->
            (seconds / (60 * 60)).toInt().toString() + " hours"
    }
}
