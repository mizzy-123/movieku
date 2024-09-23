package com.example.movieku.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.core.ui.MovieAdapter
import com.example.movieku.R
import com.example.movieku.databinding.ActivityMainBinding
import com.example.movieku.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent()
        initData()
        setAction()
    }

    private fun initComponent(){
        movieAdapter = MovieAdapter()
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        binding.rvMovie.adapter = movieAdapter
    }

    private fun setAction(){
        movieAdapter.setOnClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                startActivity(intent)
            }

        })
    }

    private fun initData(){
        mainViewModel.movie.observe(this){ movie ->
            if(movie != null){
                when(movie){
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        Log.e("MainActivity", movie.message.toString())
                    }
                }
            }
        }
    }
}