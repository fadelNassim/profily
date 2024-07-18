package com.example.profiles.data.api.dto

import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("results")
    val profiles: List<Profile>
)

data class Profile(
    @SerializedName("name")
    val name: Name,
    @SerializedName("email")
    val email: String,
    @SerializedName("dob")
    val birthDate: Dob,
    @SerializedName("location")
    val location: Location,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("login")
    val login: Login,
    @SerializedName("picture")
    val picture: Picture,
    @SerializedName("gender")
    val gender: String
)

data class Name(
    @SerializedName("title")
    val title: String,
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String
)

data class Dob(
    @SerializedName("date")
    val date: String,
    @SerializedName("age")
    val age: Int
)

data class Location(
    @SerializedName("street")
    val street: Street,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("postcode")
    val postcode: String
)

data class Street(
    @SerializedName("number")
    val number: Int,
    @SerializedName("name")
    val name: String
)

data class Login(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)

data class Picture(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)