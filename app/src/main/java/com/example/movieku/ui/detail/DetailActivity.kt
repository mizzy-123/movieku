package com.example.movieku.ui.detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bumptech.glide.Glide
import com.example.core.data.Resource
import com.example.core.utils.DataManipulate
import com.example.movieku.R
import com.example.movieku.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // teks dan ikon status bar putih
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        // Menambahkan padding agar layout tidak tertutup oleh status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        val bundle: Bundle? = intent.extras
        if (bundle !== null){
            val movieId = bundle.getLong(MOVIE_ID)
            initData(movieId)
        }

        setAction()


    }

    private fun setAction(){
        binding.toolBar.setNavigationOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initData(movieId: Long){

        detailViewModel.getDetailMovie(movieId).observe(this){ result ->
            if (result != null){
                when (result){
                    is Resource.Loading -> {
                        binding.apply {
                            layoutMovie.visibility = View.GONE
                            loadingMovie.visibility = View.VISIBLE
                        }
                    }
                    is Resource.Success -> {
                        val data = result.data
                        binding.apply {
                            layoutMovie.visibility = View.VISIBLE
                            loadingMovie.visibility = View.GONE
                            tvDescription.text = data?.overview.toString()
                            tvTitle.text = data?.title.toString()
                            tvDuration.text = "${data?.runtime.toString()} minutes"
                            tvGenre.text = data?.genres?.get(0)?.name ?: "Nothing"
                            tvRating.text = data?.vote_average?.toString()
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                tvRelease.text = DataManipulate.parseDateGetYears(data?.release_date)
                            } else {
                                tvRelease.text = data?.release_date?.substring(0, 4) ?: ""
                            }

                            Glide.with(this@DetailActivity)
                                .load("https://image.tmdb.org/t/p/w500/${data?.poster_path}")
                                .into(imgImageCard)

                            Glide.with(this@DetailActivity)
                                .load("https://image.tmdb.org/t/p/original/${data?.backdrop_path}")
                                .into(imgBackground)
                        }
                    }
                    is Resource.Error -> {
//                        binding.apply {
//                            layoutMovie.visibility = View.VISIBLE
//                            loadingMovie.visibility = View.GONE
//                        }
                        Toast.makeText(this, result.message.toString(), Toast.LENGTH_SHORT).show()
                        Log.e("DetailActivity", result.message.toString())
                    }
                }
            }
        }
    }

    companion object {
        const val MOVIE_ID = "movie_id"
    }
}