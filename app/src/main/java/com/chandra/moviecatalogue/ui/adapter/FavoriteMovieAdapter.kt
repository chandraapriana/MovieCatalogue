package com.chandra.moviecatalogue.ui.adapter

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.databinding.ItemRowBinding

class FavoriteMovieAdapter :
    PagedListAdapter<DetailMovieResponse, FavoriteMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: OnItemClickCallback? = null

    companion object {
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w200"
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<DetailMovieResponse>() {
            override fun areItemsTheSame(
                oldItem: DetailMovieResponse,
                newItem: DetailMovieResponse
            ): Boolean {
                Log.d("adapter","1")
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DetailMovieResponse,
                newItem: DetailMovieResponse
            ): Boolean {
                Log.d("adapter","2")
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data:DetailMovieResponse){
            binding.tvTitle.text = data.title
            binding.tvPopularity.text = data.voteAverage.toString()
            binding.tvYear.text = data.releaseDate
            val image = "${IMAGE_URL}${data.posterPath}"
            Glide.with(itemView.context).load(image).apply(
                RequestOptions().override(
                    100,
                    100
                ).placeholder(R.drawable.btn_default).error(R.drawable.ic_delete)
            ).into(binding.imgMovie)
            binding.root.setOnClickListener { onItemClickCallback?.onItemClicked(data) }
           binding.btnWatch.setOnClickListener { onItemClickCallback?.onItemClicked(data) }

        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(favMovie: DetailMovieResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMovieBinding =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
      holder.bind(movie!!)


    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


}