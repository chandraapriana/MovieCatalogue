package com.chandra.moviecatalogue.ui.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.databinding.ItemRowBinding

class FavoriteSeriesAdapter : PagedListAdapter<DetailTVSeriesResponse,FavoriteSeriesAdapter.ViewHolder>(
    DIFF_CALLBACK) {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    companion object {
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/w200"
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<DetailTVSeriesResponse> = object :
            DiffUtil.ItemCallback<DetailTVSeriesResponse>() {
            override fun areItemsTheSame(
                oldItem: DetailTVSeriesResponse,
                newItem: DetailTVSeriesResponse
            ): Boolean {
                return oldItem.id == newItem.id}

            override fun areContentsTheSame(
                oldItem: DetailTVSeriesResponse,
                newItem: DetailTVSeriesResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(favSeries:DetailTVSeriesResponse){
            with(binding){
                            tvTitle.text = favSeries.name
            tvPopularity.text = favSeries.voteAverage.toString()
            tvYear.text = favSeries.firstAirDate
            val image = "${IMAGE_URL}${favSeries.posterPath}"
            Glide.with(itemView.context).load(image).apply(
                RequestOptions().override(
                    100,
                    100
                ).placeholder(R.drawable.btn_default).error(R.drawable.ic_delete)
            ).into(imgMovie)
            root.setOnClickListener { onItemClickCallback?.onItemClicked(favSeries) }
            btnWatch.setOnClickListener { onItemClickCallback?.onItemClicked(favSeries) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(favSeries: DetailTVSeriesResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemSeriesBinding =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemSeriesBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(getItem(position)!!)
    }


}