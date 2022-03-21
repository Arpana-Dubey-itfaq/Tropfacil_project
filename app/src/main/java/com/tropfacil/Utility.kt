package com.tropfacil

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.tropfacil.base.BaseActivity

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