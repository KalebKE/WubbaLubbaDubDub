package com.kalebkircher.wubbalubbadubdub

import com.kalebkircher.wubbalubbadubdub.data.entity.CharacterEntity
import com.kalebkircher.wubbalubbadubdub.data.entity.LocationEntity
import com.kalebkircher.wubbalubbadubdub.data.entity.OriginEntity
import com.kalebkircher.wubbalubbadubdub.ui.mapper.CharacterMapper
import com.kalebkircher.wubbalubbadubdub.ui.state.CharacterState
import org.junit.Assert.assertEquals
import org.junit.Test

class UiMapperUnitTest {
    @Test
    fun testCharacterEntityToCharacterStateMapping() {
        // Create a CharacterEntity instance
        val characterEntity = CharacterEntity(
            id = 1L,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = OriginEntity(name = "Earth", url = ""),
            location = LocationEntity(name = "Earth", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )

        // Use CharacterMapper to map CharacterEntity to CharacterState
        val characterState: CharacterState = CharacterMapper.map(characterEntity)

        // Compare the fields of CharacterState and CharacterEntity
        assertEquals(characterEntity.id, characterState.id)
        assertEquals(characterEntity.name, characterState.name)
        assertEquals(characterEntity.status, characterState.status)
        assertEquals(characterEntity.species, characterState.species)
        assertEquals(characterEntity.type, characterState.type)
        assertEquals(characterEntity.gender, characterState.gender)
        assertEquals(characterEntity.origin.name, characterState.origin.name)
        assertEquals(characterEntity.location.name, characterState.location.name)
        assertEquals(characterEntity.image, characterState.image)
        assertEquals(characterEntity.url, characterState.url)
        assertEquals(characterEntity.created, characterState.created)
    }
}