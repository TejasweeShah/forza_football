package com.tinybrainz.football.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tinybrainz.football.R
import com.tinybrainz.football.data.network.FootballApi
import com.tinybrainz.football.data.db.entities.Team


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, teamData: Team) {
    val fallbackDrawable = when (teamData.gender) {
        "male" ->
            R.drawable.ic_boy
        "female" ->
            R.drawable.ic_girl
        else -> R.drawable.ic_flag
    }
        val avatarUrl = teamData.badgeUrl?.let {
            FootballApi.BASE_URL.plus(teamData.badgeUrl)
        }
        Glide.with(view.context)
            .load(avatarUrl)
            .circleCrop()
            .fitCenter()
            .fallback(fallbackDrawable)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
}