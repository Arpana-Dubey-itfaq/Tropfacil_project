package com.tropfacil.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import com.tropfacil.R

import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class Constants {

    companion object {

        //const val BASE_URL = "https://demo2.itfaq.cloud/leust_web/public/"
        //const val BASE_URL = "https://demo2.itfaq.cloud/leust-web/"
        const val BASE_URL = "https://demo2.itfaq.cloud/leust-web/public/" //old base usre

        //Regular Expressions
        const val PASSWORD_REGX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,20}\$"
        const val ALPHA_NUMERIC_REGX = "[A-Za-z0-9\\_\\\"]+\$"//"^[a-zA-Z0-9]+$"
        const val FRANCE_LATITUDE = 45.8812431
        const val FRANCE_LONGITUDE = -6.9322491

        val HomeFragment = 1
        val MYCOURSESFragment = 2
        val MYEXERCISEFragment = 3
        var FRAGMENT = 1


        private fun isIntentAvailable(ctx: Context, intent: Intent): Boolean {
            val packageManager: PackageManager = ctx.packageManager
            val list: List<ResolveInfo> =
                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            return list.size > 0
        }




    }
}