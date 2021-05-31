package com.chandra.moviecatalogue.ui.favorite.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.moviecatalogue.R
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.databinding.FragmentFavoriteSeriesBinding
import com.chandra.moviecatalogue.ui.adapter.FavoriteSeriesAdapter
import com.chandra.moviecatalogue.ui.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FavoriteSeriesFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteSeriesBinding
    private val viewModel: FavoriteSeriesViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            binding.rvFavSeries.layoutManager = LinearLayoutManager(context)
            
            viewModel.getFavoriteSeries().observe(viewLifecycleOwner, {
                if (it != null){
                    val favSeriesAdapter = FavoriteSeriesAdapter()
                    favSeriesAdapter.submitList(it)

                    binding.rvFavSeries.adapter = favSeriesAdapter
                    favSeriesAdapter.setOnItemClickCallback(object :
                        FavoriteSeriesAdapter.OnItemClickCallback {
                        override fun onItemClicked(favSeries: DetailTVSeriesResponse) {
                            val bundle = Bundle()
                            bundle.putString(DetailFragment.ID, favSeries.id.toString())
                            bundle.putString(DetailFragment.TYPE, "seriesDB")
                            bundle.putParcelable(DetailFragment.SERIESDETAILDB, favSeries)
                            val detailFragment = DetailFragment()
                            detailFragment.arguments = bundle
                            parentFragmentManager
                                .beginTransaction()
                                .replace(R.id.container_favorite, detailFragment)
                                .addToBackStack(null)
                                .commit()
                        }
                    })
                }

            })



        }
    }


}