package com.tinybrainz.football.ui.teams

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.tinybrainz.football.data.repository.TeamsRepository

class TeamsViewModel @ViewModelInject constructor(private val repository: TeamsRepository) : ViewModel() {

    val teams by lazy { repository.observeTeamsList() }

}