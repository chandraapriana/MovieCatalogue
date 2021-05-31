package com.chandra.moviecatalogue.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chandra.moviecatalogue.ui.movie.MovieFragment
import com.chandra.moviecatalogue.ui.series.SeriesFragment

class SectionPagerAdapter(fragmentActivity: AppCompatActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = SeriesFragment()
        }
        return fragment as Fragment
    }
}