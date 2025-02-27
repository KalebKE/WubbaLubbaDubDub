package com.kalebkircher.wubbalubbadubdub.data.entity

data class CharacterEntity(
    val id: Long = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "" ,
    val gender: String = "",
    val origin: OriginEntity = OriginEntity(),
    val location: LocationEntity = LocationEntity(),
    val image: String = "",
    val url: String = "",
    val created: String = ""
)

data class OriginEntity(val name: String = "", val url: String = "")

data class LocationEntity(val name: String = "", val url: String = "")
