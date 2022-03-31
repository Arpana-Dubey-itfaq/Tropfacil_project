package com.tropfacil.model.search

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Nimesh Patel on 30-03-2022.
 */
class SearchInfo(
    @SerializedName("parent_id")
    var parentId: String?= "",
    @SerializedName("parent_libelle")
    var parentLibelle: String ? ="",
    @SerializedName("type")
    var type: String? = "",
    @SerializedName("image")
    var image: String ? ="",
    @SerializedName("verrouille")
    var verrouille: Boolean? = false,
    @SerializedName("duree_estimee")
    var dureeEstimee: String? = "",
    @SerializedName("duree_suivi")
    var dureeSuivi: String? = "",
    @SerializedName("child_id")
    var childId: String?= "",
    @SerializedName("child_libelle")
    var childLibelle: String ? ="",
    @SerializedName("child_description")
    var childDescription: String? = "",
    @SerializedName("element_id")
    var elementId: String?= "",
    @SerializedName("element_libelle")
    var elementLibelle: String ? ="",
    @SerializedName("element_description")
    var elementDescription: String? = "",
):Serializable