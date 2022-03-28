package com.tropfacil.util.interfaces

import com.tropfacil.data.Parcour
import com.tropfacil.data.Soustheme

interface HomeToCourseDetailsListener {

    fun navigateToCourseDetailsScreenViaParCour(parcourItem: Parcour)

    fun navigateToCourseDetailsScreenViaSousTheme(sousItem: Soustheme)
}