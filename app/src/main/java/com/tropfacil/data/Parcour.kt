package com.tropfacil.data

import java.io.Serializable

data class Parcour(
    val couleur: String,
    val description: String,
    val elements: List<Element>,
    val id: String,
    val image: String,
    val libelle: String,
    val type: String,
    val duree_estimee: String,
    val duree_suivi: String,
    val verrouille: Boolean,
):Serializable