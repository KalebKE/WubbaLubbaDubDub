package com.kalebkircher.wubbalubbadubdub.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharactersDto(
    val info: InfoDto,
    val results: List<CharacterDto>,
)

@Serializable
data class InfoDto(
    val count: Long,
    val pages: Long,
    val next: String?,
    val prev: String?,
)

@Serializable
data class CharacterDto(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginDto,
    val location: LocationDto,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
)

@Serializable
data class OriginDto(
    val name: String,
    val url: String,
)

@Serializable
data class LocationDto(
    val name: String,
    val url: String,
)
