package com.chandra.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.chandra.moviecatalogue.R
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.databinding.FragmentDetailBinding

import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DetailFragment : Fragment(), View.OnClickListener {
    companion object {
        const val ID: String = "id"
        const val TYPE: String = "type"
        const val MOVIE:String = "movie"
        const val MOVIEDB:String = "movieDB"
        const val SERIES:String = "series"
        const val SERIESDB:String = "seriesDB"
        const val MOVIEDETAILDB : String = "detailMoviedb"
        const val SERIESDETAILDB : String = "detailSeriesdb"
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w200"
    }

    private var statusFavorite: Boolean = false
    private val viewModel: DetailViewModel by sharedViewModel()


    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarDetail.btnBack.setOnClickListener(this)
        binding.toolbarDetail.favorite.visibility = View.INVISIBLE

        val id = arguments?.getString(ID).toString()

        if (activity != null) {
            if (arguments != null) {
                when (arguments?.getString(TYPE).toString()) {
                    MOVIE -> {
                        binding.progressDetail.visibility = View.VISIBLE
                        checkFavoriteMovie(id)
                        viewModel.let {
                            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                                it.getDetailMovie(id).observe(viewLifecycleOwner, Observer {
                                    setupDetailMovieUI(it)
                                })
                            }
                        }

                    }
                    SERIES -> {
                        checkFavoriteSeries(id)
                        binding.progressDetail.visibility = View.VISIBLE

                        viewModel.let {
                            viewLifecycleOwner.lifecycleScope.launch {
                                it.getDetailSeries(id).observe(viewLifecycleOwner) {
                                    setupDetailSeriesUI(it)
                                }
                            }
                        }
                    }
                    MOVIEDB -> {
                        checkFavoriteMovie(id)
                        val args = arguments
                        val data= args?.getParcelable<DetailMovieResponse>(MOVIEDETAILDB)
                        setupDetailMovieUI(data as DetailMovieResponse)
                    }
                    SERIESDB -> {
                        checkFavoriteSeries(id)
                        val args = arguments
                        val data= args?.getParcelable<DetailTVSeriesResponse>(SERIESDETAILDB)
                        setupDetailSeriesUI(data as DetailTVSeriesResponse)
                    }
                }

            }
        }

    }

    private fun setupDetailSeriesUI(detailSeries: DetailTVSeriesResponse) {
        binding.apply {
            detailTvTitle.text = detailSeries.name
            tvSynopsis.visibility = View.VISIBLE
            var genre = ""
            for (i in 0..(detailSeries.genres.count().minus(1))) {
                genre += detailSeries.genres[i].name + " "
            }
            detailTvYearAndGenre.text = java.lang.StringBuilder("${detailSeries.firstAirDate} | $genre")
            detailTvDescription.text = detailSeries.overview
            val image = "$IMAGE_URL${detailSeries.posterPath}"
            activity?.applicationContext?.let {
                Glide.with(it).load(image).into(detailImgMovie)
            }
            fabAdd.setOnClickListener {
                if (!statusFavorite){
                    viewModel.insertFavoriteSeries(detailSeries)
                    Toast.makeText(context,"Successfully Add",Toast.LENGTH_LONG).show()
                    statusFavorite = true
                    setStatusFavorite(true)
                }else{
                    viewModel.deleteFavoriteSeries(detailSeries)
                    Toast.makeText(context,"Successfully Delete",Toast.LENGTH_LONG).show()
                    statusFavorite = false
                    setStatusFavorite(false)
                }

            }
            progressDetail.visibility = View.INVISIBLE
        }
    }

    private fun setupDetailMovieUI(detailMovie: DetailMovieResponse) {
        binding.apply {
            detailTvTitle.text = detailMovie.title

            tvSynopsis.visibility = View.VISIBLE
            var genre = ""
            for (i in 0..(detailMovie.genres.count().minus(1))) {
                genre += detailMovie.genres[i].name + " "
            }
            detailTvYearAndGenre.text = StringBuilder("${detailMovie.releaseDate} | $genre")
            detailTvDescription.text = detailMovie.overview
            val image = "$IMAGE_URL${detailMovie.posterPath}"
            activity?.applicationContext?.let {
                Glide.with(it).load(image).into(detailImgMovie)
            }
            progressDetail.visibility = View.INVISIBLE

            fabAdd.setOnClickListener {
                if (!statusFavorite){
                    viewModel.insertFavoriteMovie(detailMovie)
                    Toast.makeText(context,"Successfully Add",Toast.LENGTH_LONG).show()
                    statusFavorite = true
                    setStatusFavorite(true)
                }else{
                    viewModel.deleteFavoriteMovie(detailMovie)
                    Toast.makeText(context,"Successfully Delete",Toast.LENGTH_LONG).show()
                    statusFavorite = false
                    setStatusFavorite(false)
                }
            }

        }
    }


    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_back){
            parentFragmentManager.popBackStack()
        }

    }

    private fun checkFavoriteMovie(id:String){
        var count:Int
        MainScope().launch {
            withContext(Dispatchers.IO){
                count = viewModel.getFavMovieById(id)
            }

            if (count > 0) {
                    statusFavorite = true
                    setStatusFavorite(statusFavorite)
                } else {
                    statusFavorite = false
                    setStatusFavorite(statusFavorite)
                }

        }

    }

    private fun checkFavoriteSeries(id:String){
        var count:Int
        MainScope().launch {
            withContext(Dispatchers.IO) {
                count = viewModel.getFavSeriesById(id)
            }
            if (count > 0) {
                statusFavorite = true
                setStatusFavorite(statusFavorite)
            } else {
                statusFavorite = false
                setStatusFavorite(statusFavorite)
            }
        }


    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabAdd.setImageResource(R.drawable.ic_favorite_fill)

        } else {
            binding.fabAdd.setImageResource(R.drawable.ic_favorite_border)
        }
    }

}