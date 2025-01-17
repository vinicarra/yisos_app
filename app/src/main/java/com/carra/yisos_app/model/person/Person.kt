package com.carra.yisos_app.model.person

import kotlinx.serialization.Serializable

@Serializable
data class Person (
    val username: String,
    val photo: String,
    val age: Int,
    val gender: String,
    val sexualOrientation: String,
    val city: String
)