package com.tropfacil.data

import java.io.Serializable

data class Soustheme(
    val couleur: String,
    val description: String,
    val elements: List<ElementX>,
    val icone: String,
    val id: String,
    val libelle: String
):Serializable