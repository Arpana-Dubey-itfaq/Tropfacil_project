package com.tropfacil.data

import java.io.Serializable

data class Theme(
    val couleur: String,
    val icone: String,
    val id: String,
    val libelle: String,
    val parcours: List<Parcour>,
    val sousthemes: List<Soustheme>
) : Serializable