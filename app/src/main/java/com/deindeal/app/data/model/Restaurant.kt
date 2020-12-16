package com.deindeal.app.data.model

data class Restaurant(
    val id: Long,
    val title: String?,
    val subtitle: String?,
    val image: String?,
    val filters: ArrayList<String?>?
)
