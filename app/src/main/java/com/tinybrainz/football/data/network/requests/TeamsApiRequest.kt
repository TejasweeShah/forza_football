package com.tinybrainz.football.data.network.requests

import com.tinybrainz.football.data.network.SafeApiRequest
import com.tinybrainz.football.data.network.FootballApi
import javax.inject.Inject

class TeamsApiRequest @Inject constructor(
    private val footballApi: FootballApi
) : SafeApiRequest() {

    suspend fun fetchTeamDetails(id: String) = getResult { footballApi.getTeamDetails(id) }

    suspend fun fetchTeams() = getResult { footballApi.getTeams() }
}