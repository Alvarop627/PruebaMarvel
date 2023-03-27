package com.ejemplo.marvel.prueba.data.data_source.dto

import com.ejemplo.marvel.prueba.domain.model.ComicModel
import java.util.*

data class ComicResult(

    val id: Int?,
    val digitalId: Int?,
    val title: String?,
    val issueNumber: Double?,
    val variantDescription: String?,
    val description: String?,
    val modified: Date?,
    val isbn: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format: String?,
    val pageCount: Int?,
    val textObjects: List<TextObject>?,
    val resourceURI: String?,
    val urls: List<Url>?,
    val series: SeriesSummary?,
    val variants: List<ComicSummary>?,
    val collections: List<ComicSummary>?,
    val collectedIssues: List<ComicSummary>?,
    val dates: List<ComicDate>?,
    val prices: List<ComicPrice>?,
    val thumbnail: Thumbnail?,
    val images: List<Thumbnail>?,
    val creators: CreatorList?,
    val characters: CharacterList?,
    val stories: StoryList?,
    val events: EventList?


) {
    fun toComic(): ComicModel? {
        return id?.let {
            title?.let { it1 ->
                description?.let { it2 ->
                    thumbnail?.let { it3 ->
                        ComicModel(
                            id = it,
                            title = it1,
                            description = it2,
                            thumbnail = it3
                        )
                    }
                }
            }
        }
    }
}

