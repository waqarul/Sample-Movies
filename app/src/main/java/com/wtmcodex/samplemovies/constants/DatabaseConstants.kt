package com.wtmcodex.samplemovies.constants

interface DatabaseConstants {
    companion object {
        const val DATABASE_NAME = "TMDBDatabase.db"
        const val TABLE_MOVIES = "movies"
        const val QUERY_SELECT_MOVIES = "SELECT * FROM $TABLE_MOVIES"
        const val QUERY_SELECT_MOVIES_BY_ID = "SELECT * FROM $TABLE_MOVIES WHERE id=:id "
        const val QUERY_DELETE_MOVIES = "DELETE FROM $TABLE_MOVIES"
    }
}