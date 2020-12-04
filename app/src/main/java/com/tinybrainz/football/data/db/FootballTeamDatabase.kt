package com.tinybrainz.football.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tinybrainz.football.data.db.entities.Team


@Database(entities = [Team::class], version = 1, exportSchema = false)
abstract class FootballTeamDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "footballteams"
    }

    abstract fun teamDao(): TeamsDao
}