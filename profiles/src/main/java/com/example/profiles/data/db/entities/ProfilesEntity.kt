package com.example.profiles.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "birthDate")
    val birthDate: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "picture")
    val picture: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "gender")
    val gender: String
)