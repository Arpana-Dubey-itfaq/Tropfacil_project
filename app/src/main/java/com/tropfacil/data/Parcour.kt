package com.tropfacil.data

data class Parcour(
    val couleur: String,
    val description: String,
    val elements: List<Element>,
    val id: String,
    val image: String,
    val libelle: String,
    val type: String
)