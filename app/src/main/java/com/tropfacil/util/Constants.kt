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


        val HomeFragment = 1
        val MYCOURSESFragment = 2
        val MYEXERCISEFragment = 3
        var FRAGMENT = 1

    }
}