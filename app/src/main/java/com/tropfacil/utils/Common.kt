package com.tropfacil.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.widget.ImageView
import com.bumptech.glide.Glide

import androidx.core.graphics.drawable.DrawableCompat

import androidx.appcompat.widget.AppCompatImageView
import com.tropfacil.R


/*
fun glideImage(context: Context, imageURL: String, imageView: ImageView) {
    Glide.with(context).load(imageURL).placeholder(R.drawable.ic_launcher_background)
        .into(imageView)
}

fun glideRoundedImage(context: Context, imageURL: String, imageView: RoundedImageView) {
    Glide.with(context).load(imageURL).placeholder(R.drawable.ic_launcher_background)
        .into(imageView)
}
*/

fun updateStatusBarColor(colorResource: Int, activity: Activity) {
    activity.window.statusBarColor = colorResource
}

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun changeImageColor(context: Context, color: Int, imageView: AppCompatImageView) {
    DrawableCompat.setTint(
        DrawableCompat.wrap(imageView.drawable),
        color
    )
}