package com.kalebkircher.wubbalubbadubdub.data.mapper

import com.kalebkircher.wubbalubbadubdub.data.dto.CharacterDto
import com.kalebkircher.wubbalubbadubdub.data.entity.CharacterEntity
import com.kalebkircher.wubbalubbadubdub.data.entity.LocationEntity
import com.kalebkircher.wubbalubbadubdub.data.entity.OriginEntity


class CharacterMapper {
    companion object {
        fun map(dto: CharacterDto): CharacterEntity {
            return CharacterEntity(
                id = dto.id,
                name = dto.name,
                status = dto.status,
                species = dto.species,
                type = dto.type,
                gender = dto.gender,
                origin = OriginEntity(name = dto.origin.name, url = dto.origin.url),
                location = LocationEntity(name = dto.location.name, url = dto.location.url),
                image = dto.image,
                url = dto.url,
                created = dto.created
            )
        }
    }
}