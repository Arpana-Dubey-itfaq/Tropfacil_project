package com.tropfacil.util.interfaces

interface HomeOptionsListener {
    fun onClickMenu()
    fun lockDrawer(lock: Boolean)
    fun navigateToHomeScreen()
    fun navigateToMyCoursesScreen()
    fun navigateToMyExerciseScreen()
    fun navigateToCategoryScreen()
}