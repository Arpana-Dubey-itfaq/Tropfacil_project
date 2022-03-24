package com.tropfacil.model.exercices


import com.google.gson.annotations.SerializedName

data class ExercicesInfoList(
    @SerializedName("exercices")
    val exercices: List<ExercicesInfo>,
    @SerializedName("idtheme")
    val idtheme: String, // 10298
    @SerializedName("libelle")
    val libelle: String // CSS3 - ENI® CERTIFICATIONS
)