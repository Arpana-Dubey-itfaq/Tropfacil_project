package com.tropfacil.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Parcour(
    val couleur: String,
    val description: String,
    val elements: List<Element>,
    val id: String,
    val image: String,
    val libelle: String,
    val type: String
)