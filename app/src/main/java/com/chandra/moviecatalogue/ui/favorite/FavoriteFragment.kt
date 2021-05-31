package com.chandra.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.chandra.moviecatalogue.R
import com.chandra.moviecatalogue.databinding.FragmentFavoriteBinding
import com.chandra.moviecatalogue.ui.adapter.FavoritePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.fav_movies, R.string.fav_series)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarFavorite.tvTitle.text = resources.getString(R.string.favorite)
        binding.toolbarFavorite.favorite.visibility = View.INVISIBLE
        binding.toolbarFavorite.btnBack.setOnClickListener { parentFragmentManager.popBackStack() }
        setupPagerAdapter(binding)
    }

    private fun setupPagerAdapter(binding: FragmentFavoriteBinding) {

        val favoritePagerAdapter =
            FavoritePagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPagerFavorite
        viewPager.adapter = favoritePagerAdapter
        val tabs: TabLayout = binding.tabsFavorite
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

    }


}