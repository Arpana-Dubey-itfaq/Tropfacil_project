package com.tropfacil.data

data class Element(
    val avancement: Int,
    val date_debut: Any,
    val date_fin: Any,
    val description: String,
    val duree_estimee: String,
    val duree_suivi: Int,
    val id: String,
    val image: String,
    val libelle: String,
    val libstatut: String,
    val nbconnexions: Any,
    val optionnel: Boolean,
    val strdatederniereconnexion: String,
    val type: String,
    val verrouille: Boolean
)