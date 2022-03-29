package com.tropfacil.data

data class home_response(
    val success: String,
    val error:Error,
    val themes: List<Theme>
)