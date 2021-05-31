package com.chandra.moviecatalogue.ui.home


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.chandra.moviecatalogue.R
import com.chandra.moviecatalogue.databinding.ActivityMainBinding
import com.chandra.moviecatalogue.ui.adapter.SectionPagerAdapter
import com.chandra.moviecatalogue.ui.favorite.FavoriteFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {


    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.movies, R.string.series)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.toolbarHome.btnBack.visibility = View.GONE
        activityMainBinding.toolbarHome.tvTitle.text = resources.getString(R.string.app_name)

        if (Build.VERSION.SDK_INT > 22) {
            if (isOnline(this)) {
                setupPagerAdapter(activityMainBinding)
            } else {
                activityMainBinding.tvNoConnection.visibility = View.VISIBLE
            }
        } else {
            setupPagerAdapter(activityMainBinding)
        }

        activityMainBinding.toolbarHome.favorite.setOnClickListener {
            val favoriteFragment = FavoriteFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_main, favoriteFragment)
                .addToBackStack(null)
                .commit()
        }

    }

    private fun setupPagerAdapter(activityMainBinding: ActivityMainBinding) {
        activityMainBinding.tvNoConnection.visibility = View.GONE
        val sectionsPagerAdapter =
            SectionPagerAdapter(this)
        val viewPager: ViewPager2 = activityMainBinding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = activityMainBinding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}