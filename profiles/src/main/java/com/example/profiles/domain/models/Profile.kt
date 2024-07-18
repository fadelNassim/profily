package com.example.profiles.domain.models

data class Profile(
    val id: Int,
    val name: String,
    val email: String,
    val birthDate: String,
    val phone: String,
    val picture: String,
    val country: String,
    val gender: String
)