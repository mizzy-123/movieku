package com.example.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.Movie
import com.example.core.ui.MovieAdapter
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.movieku.di.FavoriteModuleDependencies
import com.example.movieku.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // teks dan ikon status bar putih
        window.statusBarColor = ContextCompat.getColor(this, com.example.movieku.R.color.black2)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        // Menambahkan padding agar layout tidak tertutup oleh status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(com.example.movieku.R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent()
        initData()
        setAction()
    }

    private fun setAction(){
        binding.toolBar.setNavigationOnClickListener {
            finish()
        }

        movieAdapter.setOnClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.MOVIE_ID, data.movieId)
                startActivity(intent)
            }
        })
    }

    private fun initComponent(){
        movieAdapter = MovieAdapter()
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = movieAdapter
    }

    private fun initData(){
//        favoriteViewModel.getAllFavoriteMovie().observe(this){ movie ->
//            if (movie != null){
//                movieAdapter.setData(movie)
//            }
//        }
    }

}