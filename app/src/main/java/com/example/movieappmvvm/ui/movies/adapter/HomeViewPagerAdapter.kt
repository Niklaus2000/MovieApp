package com.example.movieappmvvm.ui.movies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieappmvvm.data.model.Movie
import com.example.movieappmvvm.ui.movies.viewpager.HomeViewPagerFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeViewPagerAdapter (fm : FragmentManager , lifecycle: Lifecycle , val movies : List<Movie> = ArrayList()): FragmentStateAdapter(fm, lifecycle){

    override fun getItemCount(): Int = movies.size

    override fun createFragment(position: Int): Fragment {
        return HomeViewPagerFragment(movies[position])
    }

}