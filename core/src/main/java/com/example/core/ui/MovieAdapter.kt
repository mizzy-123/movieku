package com.example.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.ItemCardMovieBinding
import com.example.core.domain.model.Movie
import com.example.core.utils.DataManipulate
import java.util.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>(){
    private var listData = ArrayList<Movie>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    fun setOnClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(var binding: ItemCardMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemCardMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = listData[position]

        holder.apply {
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                .into(binding.imgImageCard)
            binding.tvRelease.text = movie.release_date
            binding.tvTitle.text = movie.title
            binding.tvRating.text = movie.vote_average.toString()
            binding.tvGenre.text = DataManipulate.listGenreToString(movie.genre_list)

            itemView.setOnClickListener { onItemClickCallback.onItemClicked(movie) }
        }

    }
}