package com.tinybrainz.football.data.network

import com.tinybrainz.football.data.db.entities.Team
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballApi {

    companion object {
        const val BASE_URL = "https://android-exam.s3-eu-west-1.amazonaws.com"
    }

    @GET("/teams/teams.json")
    suspend fun getTeams(): Response<List<Team>>


    @GET("/teams/{id}/team.json")
    suspend fun getTeamDetails(@Path("id") id: String): Response<Team>
}