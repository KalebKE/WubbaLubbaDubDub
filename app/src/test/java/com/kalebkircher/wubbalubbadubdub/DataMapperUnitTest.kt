package com.kalebkircher.wubbalubbadubdub

import com.kalebkircher.wubbalubbadubdub.data.dto.CharacterDto
import com.kalebkircher.wubbalubbadubdub.data.dto.LocationDto
import com.kalebkircher.wubbalubbadubdub.data.dto.OriginDto
import com.kalebkircher.wubbalubbadubdub.data.entity.CharacterEntity
import com.kalebkircher.wubbalubbadubdub.data.mapper.CharacterMapper
import org.junit.Assert.assertEquals
import org.junit.Test

class DataMapperUnitTest {

    @Test
    fun testCharacterDtoToCharacterEntityMapping() {
        // Create a CharacterDto instance
        val characterDto = CharacterDto(
            id = 1L,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = OriginDto(name = "Earth", url = ""),
            location = LocationDto(name = "Earth", url = ""),
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            episode = listOf(),
            url = "https://rickandmortyapi.com/api/character/1",
            created = "2017-11-04T18:48:46.250Z"
        )

        // Use CharacterMapper to map CharacterEntity to CharacterState
        val characterEntity: CharacterEntity = CharacterMapper.map(characterDto)

        // Compare the fields of CharacterState and CharacterDto
        assertEquals(characterDto.id, characterEntity.id)
        assertEquals(characterDto.name, characterEntity.name)
        assertEquals(characterDto.status, characterEntity.status)
        assertEquals(characterDto.species, characterEntity.species)
        assertEquals(characterDto.type, characterEntity.type)
        assertEquals(characterDto.gender, characterEntity.gender)
        assertEquals(characterDto.origin.name, characterEntity.origin.name)
        assertEquals(characterDto.location.name, characterEntity.location.name)
        assertEquals(characterDto.image, characterEntity.image)
        assertEquals(characterDto.url, characterEntity.url)
        assertEquals(characterDto.created, characterEntity.created)
    }
}