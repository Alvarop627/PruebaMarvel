package com.ejemplo.marvel.prueba.data.data_source.dto
import com.google.gson.GsonBuilder
import java.util.Date

data class ComicDate(
    val type: String?,
    val date: Date?
) {

    fun toJson(): String {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()
        return gson.toJson(this)
    }

    companion object {
        fun fromJson(json: String): ComicDate {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()
            return gson.fromJson(json, ComicDate::class.java)
        }
    }
}
