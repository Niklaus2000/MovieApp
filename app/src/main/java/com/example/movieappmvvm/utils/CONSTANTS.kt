package com.example.movieappmvvm.utils

import com.example.movieappmvvm.R

class CONSTANTS {

    companion object {
        const val TABLE_NAME = "BOOKMARK_TABLE"
        const val cast = "CAST"
        const val movie = "MOVIE"

        const val API_URL = "https://api.themoviedb.org/3/";
        const val API_KEY = "a6ea6249484a13e9812c3f315028feb0"
        const val BaseURL = "https://api.themoviedb.org/3/"
        const val ImageBaseURL = "https://image.tmdb.org/t/p/original"


        val viewAll = "view"
        val Popular = "Popular"
        val Upcoming = "Upcoming"
        val TopRated = "TopRated"
        val Bookmarks = "Bookmarks"

        fun getGenreMap(): HashMap<Int , String> {
            val genreMap = HashMap<Int, String>()
            genreMap[28] = "Action 🤠 "
            genreMap[12] = "Adventure 🏕 "
            genreMap[16] = "Animation 🎥 "
            genreMap[35] = "Comedy 🤣 "
            genreMap[80] = "Crime 👮‍♂️ "
            genreMap[99] = "Documentary 📃 "
            genreMap[18] = "Drama 😨 "
            genreMap[10751] = "Family 👪 "
            genreMap[14] = "Fantasy 🧙‍♂️ "
            genreMap[36] = "History 💾 "
            genreMap[27] = "Horror 👻 "
            genreMap[10402] = "Music 🎶 "
            genreMap[9648] = "Mystery 🕵️‍♀️ "
            genreMap[10749] = "Romance 💏"
            genreMap[878] = "Science Fiction 🚀 "
            genreMap[53] = "Thriller 🗡 "
            genreMap[10752] = "War ⚔ "
            genreMap[37] = "Western 🤵 "
            genreMap[10770] = "TV Movie 📺 "
            return genreMap
        }


        val moviePlaceHolder = arrayListOf<Int>(
            R.drawable.ic_movie_blue,
            R.drawable.ic_movie_red,
            R.drawable.ic_movie_yellow,
            R.drawable.ic_movie_green
        )

        val viewPagerPlaceHolder = arrayListOf<Int>(
            R.drawable.ic_movie_blue_wide,
            R.drawable.ic_movie_red_wide,
            R.drawable.ic_movie_yellow_wide,
            R.drawable.ic_movie_green_wide
        )


        val actorPlaceHolder = arrayListOf<Int> (
            R.drawable.ic_person_blue,
            R.drawable.ic_person_red,
            R.drawable.ic_person_yellow,
            R.drawable.ic_person_green
        )

    }

}