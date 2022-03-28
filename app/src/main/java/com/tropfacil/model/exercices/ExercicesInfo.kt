package com.tropfacil.model.exercices


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ExercicesInfo(
    @SerializedName("avancement")
    val avancement: Int, // 0
    @SerializedName("date_debut")
    val dateDebut: Any, // null
    @SerializedName("date_fin")
    val dateFin: Any, // null
    @SerializedName("description")
    val description: String,
    @SerializedName("duree_estimee")
    val dureeEstimee: Int, // 0
    @SerializedName("duree_suivi")
    val dureeSuivi: Int, // 0
    @SerializedName("id")
    val id: String, // 14614
    @SerializedName("image")
    val image: String, // /parcours/img/parcoursV2/quiz_illu_1.jpg
    @SerializedName("libelle")
    val libelle: String, // 1. Quiz sur le chapitre
    @SerializedName("libstatut")
    val libstatut: String, // |Non commencé|
    @SerializedName("nbconnexions")
    val nbconnexions: Int, // 0
    @SerializedName("optionnel")
    val optionnel: Boolean, // false
    @SerializedName("strdatederniereconnexion")
    val strdatederniereconnexion: String,
    @SerializedName("type")
    val type: String, // quiz
    @SerializedName("verrouille")
    val verrouille: Boolean // true
) : Serializable