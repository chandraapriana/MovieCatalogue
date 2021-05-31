package com.chandra.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.moviecatalogue.R
import com.chandra.moviecatalogue.data.model.ListPopularMovieResponse
import com.chandra.moviecatalogue.databinding.FragmentMovieBinding
import com.chandra.moviecatalogue.ui.adapter.ListMovieAdapter
import com.chandra.moviecatalogue.ui.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    private val viewModel: MovieViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)

        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = ListMovieAdapter()
            fragmentMovieBinding.progressMovie.visibility = View.VISIBLE
            viewModel.getPopularMovie().observe(viewLifecycleOwner) { movie ->
                setupListMovie(movieAdapter, movie)
            }
        }
    }

    private fun setupListMovie(
        movieAdapter: ListMovieAdapter,
        movie: List<ListPopularMovieResponse>
    ) {
        movieAdapter.setMovie(movie)
        with(fragmentMovieBinding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
        fragmentMovieBinding.progressMovie.visibility = View.INVISIBLE
        movieAdapter.setOnItemClickCallback(object :
            ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: ListPopularMovieResponse) {
                passingDataToAdapter(movie)
            }
        })
    }

    private fun passingDataToAdapter(movie: ListPopularMovieResponse) {
        val bundle = Bundle()
        bundle.putString(DetailFragment.ID, movie.id.toString())
        bundle.putString(DetailFragment.TYPE, "movie")
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container_main, detailFragment)
            .addToBackStack(null)
            .commit()
    }

}
