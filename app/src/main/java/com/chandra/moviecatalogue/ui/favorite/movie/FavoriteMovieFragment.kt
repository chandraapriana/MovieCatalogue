package com.chandra.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chandra.moviecatalogue.R
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.chandra.moviecatalogue.ui.adapter.FavoriteMovieAdapter
import com.chandra.moviecatalogue.ui.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FavoriteMovieFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteMovieBinding
    private val viewModel: FavoriteMovieViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(
            layoutInflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favMovieAdapter = FavoriteMovieAdapter()
        with(binding.rvFavMovie){
            layoutManager = LinearLayoutManager(context)
        }
        if (activity != null){

            viewModel.getFavoriteMovie().observe(viewLifecycleOwner, {
                    movie->
                favMovieAdapter.submitList(movie)
                binding.rvFavMovie.adapter = favMovieAdapter
                favMovieAdapter.setOnItemClickCallback(object : FavoriteMovieAdapter.OnItemClickCallback {
                    override fun onItemClicked(favMovie: DetailMovieResponse) {
                        val bundle = Bundle()
                        bundle.putString(DetailFragment.ID, favMovie.id.toString())
                        bundle.putString(DetailFragment.TYPE, "movieDB")
                        bundle.putParcelable(DetailFragment.MOVIEDETAILDB,favMovie)
                        val detailFragment = DetailFragment()
                        detailFragment.arguments = bundle
                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.container_favorite, detailFragment)
                            .addToBackStack(null)
                            .commit()
                    }
                })

            })



        }
    }




}