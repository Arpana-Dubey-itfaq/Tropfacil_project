package com.tropfacil.model

import com.google.gson.annotations.SerializedName
import com.tropfacil.data.Element


data class CourseChaptersWithLessonsModel(

    @SerializedName("id")
    var id: String? = null,
    @SerializedName("type")
    var type: String? = null,

    @SerializedName("libelle")
    var libelle: String? = null,

    @SerializedName("lessons")
    var lessonsList: MutableList<Element>? = null


)