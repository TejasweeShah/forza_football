package com.tinybrainz.football.ui.teams

import androidx.recyclerview.widget.RecyclerView
import com.tinybrainz.football.databinding.TeamListItemBinding
import com.tinybrainz.football.data.db.entities.Team

class TeamViewHolder(private val binding: TeamListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(team: Team, userClickCallback: ((Team) -> Unit)) {
        binding.apply {
            this.team = team
            executePendingBindings()
        }
        binding.root.setOnClickListener { userClickCallback.invoke(team) }
    }
}