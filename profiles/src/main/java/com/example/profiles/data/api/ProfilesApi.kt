package com.example.profiles.data.api


import com.example.profiles.data.api.dto.ProfileDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfilesApi {
    @GET("1.3/")
    suspend fun getProfiles(
        @Query("page") pageNumber: Int,
        @Query("results") profileCount: Int
    ): ProfileDto
}