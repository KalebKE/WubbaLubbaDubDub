package com.kalebkircher.wubbalubbadubdub.ui.state

data class CharacterState(
    val id: Long = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val origin: OriginState = OriginState(),
    val location: LocationState = LocationState(),
    val image: String = "",
    val url: String = "",
    val created: String = ""
)

data class OriginState(val name: String = "", val url: String = "")
data class LocationState(val name: String = "", val url: String = "")