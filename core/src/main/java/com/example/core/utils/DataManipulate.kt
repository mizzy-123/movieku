package com.example.core.utils

import com.example.core.data.source.local.entity.GenreEntity

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
}