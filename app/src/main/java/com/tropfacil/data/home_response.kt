package com.tropfacil.data

data class home_response(
    val success: String,
    var error:Error,
    val themes: List<Theme>
)