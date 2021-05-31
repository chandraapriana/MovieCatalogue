package com.chandra.moviecatalogue.ui.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chandra.moviecatalogue.data.model.ListPopularTVResponse
import com.chandra.moviecatalogue.databinding.ItemRowBinding


class ListSeriesAdapter : RecyclerView.Adapter<ListSeriesAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<ListPopularTVResponse>()

    private var onItemClickCallback: OnItemClickCallback? = null

    companion object {
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w200"
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.binding.apply {
            tvTitle.text = movie.name
            tvPopularity.text = movie.voteAverage.toString()
            tvYear.text = StringBuilder("${movie.firstAirDate} | ")
            val image = "$IMAGE_URL${movie.posterPath}"
            Glide.with(holder.itemView.context).load(image).apply(
                RequestOptions().override(
                    100,
                    100
                ).placeholder(R.drawable.btn_default).error(R.drawable.ic_delete)
            ).into(imgMovie)
            root.setOnClickListener { onItemClickCallback?.onItemClicked(movie) }
            btnWatch.setOnClickListener { onItemClickCallback?.onItemClicked(movie) }
        }


    }

    fun setSeries(movie: List<ListPopularTVResponse>) {
        this.listMovie.addAll(movie)
    }

    inner class MovieViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(movie: ListPopularTVResponse)
    }


}