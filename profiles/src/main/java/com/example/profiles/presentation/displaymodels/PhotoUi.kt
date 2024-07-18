package com.example.profiles.presentation.displaymodels

data class ProfileUi(
    val id: Int,
    val name: String,
    val email: String,
    val birthDate: String,
    val phone: String,
    val picture: String,
    val country: String,
    val isMale: Boolean
)