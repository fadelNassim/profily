package com.example.profiles.domain.models

data class Profile(
    val id: Int,
    val name: String,
    val email: String,
    val dob: String,
    val phone: String,
    val picture: String
)