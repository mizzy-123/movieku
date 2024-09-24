package com.example.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.core.data.source.local.entity.GenreEntity
import java.time.LocalDate

object DataManipulate {
    fun listGenreToString(genreList: List<GenreEntity>): String {
        var result = ""
        genreList.forEachIndexed { index, genreEntity ->
            result += if (genreList.size-1 == index){
                genreEntity.name
            } else {
                "${genreEntity.name}, "
            }
        }

        return result
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseDateGetYears(date: String?): String {
        if (date == null) return ""

        val parse = LocalDate.parse(date)
        val year = parse.year

        return year.toString()
    }
}