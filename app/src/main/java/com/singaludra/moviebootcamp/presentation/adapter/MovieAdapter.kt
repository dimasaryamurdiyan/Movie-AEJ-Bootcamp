package com.singaludra.moviebootcamp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.singaludra.moviebootcamp.R
import com.singaludra.moviebootcamp.databinding.ItemMovieBinding
import com.singaludra.moviebootcamp.domain.model.Movie
import com.singaludra.moviebootcamp.utils.Constants
import com.singaludra.moviebootcamp.utils.loadImage


class MovieAdapter(private val itemClick: OnClickListener): RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<Movie>?) = differ.submitList(value)

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie){
            binding.apply {
                ivPoster.loadImage(
                    url = Constants.POSTER_PATH + item.posterPath,
                )
                tvTitle.text = item.originalTitle
                tvOverview.text = item.overview
                tvVoteAverage.text = root.context.getString(R.string.vote, item.voteAverage.toString())

                binding.root.setOnClickListener {
                    itemClick.onClickItem(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            ItemMovieBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MovieAdapter.ViewHolder,
        position: Int
    ) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = differ.currentList.size

    interface OnClickListener {
        fun onClickItem(item: Movie)
    }
}