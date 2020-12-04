package com.tinybrainz.football.ui.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tinybrainz.football.R
import com.tinybrainz.football.databinding.TeamsFragmentBinding
import com.tinybrainz.football.data.db.entities.Team
import com.tinybrainz.football.data.network.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment() {

    private val teamsViewModel: TeamsViewModel by viewModels()

    private var adapter = TeamsListAdapter { user ->
        findNavController().navigate(TeamsFragmentDirections.actionShowTeamDetails(user.teamId))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dateBinding = DataBindingUtil.inflate<TeamsFragmentBinding>(
            inflater,
            R.layout.teams_fragment,
            container,
            false
        )
        dateBinding.teamsList.adapter = adapter
        dateBinding.teamsList.layoutManager = LinearLayoutManager(context)
        dateBinding.teamsList.adapter

        subscribeUi(dateBinding)

        return dateBinding.root
    }

    private fun subscribeUi(dateBinding: TeamsFragmentBinding) {
        teamsViewModel.teams.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    dateBinding.progressBar.visibility = View.GONE
                    adapter.submitList(result.data ?: emptyList<Team>())
                }
                Result.Status.LOADING -> dateBinding.progressBar.visibility = View.VISIBLE
                Result.Status.ERROR -> {
                    dateBinding.progressBar.visibility = View.GONE
                    Snackbar.make(dateBinding.container, result.message!!, Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        })
    }
}