package com.chandra.moviecatalogue.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.moviecatalogue.R
import com.chandra.moviecatalogue.data.model.ListPopularTVResponse
import com.chandra.moviecatalogue.databinding.FragmentSeriesBinding
import com.chandra.moviecatalogue.ui.adapter.ListSeriesAdapter
import com.chandra.moviecatalogue.ui.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SeriesFragment : Fragment() {
    private lateinit var fragmentSeriesBinding: FragmentSeriesBinding
    private val viewModel: SeriesViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSeriesBinding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return fragmentSeriesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val seriesAdapter = ListSeriesAdapter()
            fragmentSeriesBinding.progressSeries.visibility = View.VISIBLE

            viewModel.getPopularSeries().observe(viewLifecycleOwner) {
                setupListSeries(seriesAdapter, it)
            }

        }
    }

    private fun setupListSeries(
        seriesAdapter: ListSeriesAdapter,
        it: List<ListPopularTVResponse>
    ) {
        seriesAdapter.setSeries(it)
        with(fragmentSeriesBinding.rvSeries) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = seriesAdapter
        }
        fragmentSeriesBinding.progressSeries.visibility = View.GONE
        seriesAdapter.setOnItemClickCallback(object : ListSeriesAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: ListPopularTVResponse) {
                passingDataToAdapter(movie)
            }
        })
    }

    private fun passingDataToAdapter(movie: ListPopularTVResponse) {
        val bundle = Bundle()
        bundle.putString(DetailFragment.ID, movie.id.toString())
        bundle.putString(DetailFragment.TYPE, "series")
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, detailFragment)
            .addToBackStack(null)
            .commit()
    }


}