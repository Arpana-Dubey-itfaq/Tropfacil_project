@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.tropfacil.utils

import android.app.Activity
import android.content.Context
import android.text.format.DateUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.tropfacil.R
import java.text.SimpleDateFormat
import java.util.*


fun getDayName(context: Context, dayIndex: Int): String {
    var dayName = ""
    when (dayIndex) {
        0 -> dayName = context.getString(R.string.sunday)
        1 -> dayName = context.getString(R.string.monday)
        2 -> dayName = context.getString(R.string.tuesday)
        3 -> dayName = context.getString(R.string.wednesday)
        4 -> dayName = context.getString(R.string.thursday)
        5 -> dayName = context.getString(R.string.friday)
        6 -> dayName = context.getString(R.string.saturday)
    }
    return dayName
}

fun getDateInMinusTenMin(tokenExpDate: String): String? {
    var tokenDateMinusMin: String? = null

    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val sdf1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)

        val calendar = Calendar.getInstance()
        calendar.time = sdf1.parse(tokenExpDate)!!
        calendar.add(Calendar.MINUTE, -10)
        tokenDateMinusMin = sdf.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return tokenDateMinusMin
}

fun compareTokenExpirationDates(tokenDateTime: String): Boolean {
    var isTokenDateGreater = false
//           var     tokenDateTime = "2019-08-04T11:08:47"
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val selectedDate = sdf.parse(tokenDateTime)
        val currentDate = sdf.parse(sdf.format(Date()))
        currentDate?.let {
            if (currentDate.compareTo(selectedDate) < 0) {
                isTokenDateGreater = true
            }
        }

    } catch (e1: Exception) {
        e1.printStackTrace()
    }
    return isTokenDateGreater

}


fun getDateAdded(dateAdded: String): String {
    val f = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS", Locale.ENGLISH)
    val d =
        f.parse(dateAdded) // Format String to a dateobject with the format provided by the String.
    val f2 =
        SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
    return f2.format(d)
}


fun getCurrentDate(): String {
    val sdf =
        SimpleDateFormat("yyyy/MM/dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    return sdf.format(Date())

}



fun getCurrentDateInYYYYMMDD(): String {
    val sdf =
        SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
    return sdf.format(Date())

}

fun getCurrentTime(): String {
    val sdf =
        SimpleDateFormat("HH:mm", Locale.ENGLISH)
    return sdf.format(Date())

}

private fun isTomorrow(deliveryDate: String): Boolean {
    var date = Date()
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        date = sdf.parse(deliveryDate)
    } catch (e1: Exception) {
        e1.printStackTrace()
    }
    return DateUtils.isToday(date.time - DateUtils.DAY_IN_MILLIS)
}

private fun isToday(deliveryDate: String): Boolean {
    var date = Date()
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        date = sdf.parse(deliveryDate)
    } catch (e1: Exception) {
        e1.printStackTrace()
    }
    return DateUtils.isToday(date.time)
}

fun getDeliveryDate(context: Context, deliveryDate: String): String {
    val isToday = isToday(deliveryDate)
    val isTomorrow = isTomorrow(deliveryDate)
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val sdf1 = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.ENGLISH)
    val sdf2 = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)

    when {
        isToday -> {
            val d =
                sdf.parse(deliveryDate)
            val formattedDate = sdf2.format(d)
            return "${context.getString(R.string.today)}, $formattedDate"
        }
        isTomorrow -> {
            val d =
                sdf.parse(deliveryDate)
            val formattedDate = sdf2.format(d)
            return "${context.getString(R.string.tomorrow)}, $formattedDate"
        }
        else -> {
            val d =
                sdf.parse(deliveryDate)
            return sdf1.format(d)
        }
    }
}


fun getDeliveryDateFullMonth(context: Context, deliveryDate: String): String {
    val isToday = isToday(deliveryDate)
    val isTomorrow = isTomorrow(deliveryDate)
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val sdf1 = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH)
    val sdf2 = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)

    when {
        isToday -> {
            val d =
                sdf.parse(deliveryDate)
            val formattedDate = sdf2.format(d)
            return "${context.getString(R.string.today)}, $formattedDate"
        }
        isTomorrow -> {
            val d =
                sdf.parse(deliveryDate)
            val formattedDate = sdf2.format(d)
            return "${context.getString(R.string.tomorrow)}, $formattedDate"
        }
        else -> {
            val d =
                sdf.parse(deliveryDate)
            return sdf1.format(d)
        }
    }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

