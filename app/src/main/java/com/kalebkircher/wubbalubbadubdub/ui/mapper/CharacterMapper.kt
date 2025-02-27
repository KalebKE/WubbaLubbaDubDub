package com.kalebkircher.wubbalubbadubdub.ui.mapper

import com.kalebkircher.wubbalubbadubdub.data.entity.CharacterEntity
import com.kalebkircher.wubbalubbadubdub.ui.state.CharacterState
import com.kalebkircher.wubbalubbadubdub.ui.state.LocationState
import com.kalebkircher.wubbalubbadubdub.ui.state.OriginState

class CharacterMapper {
    companion object {
        fun map(entity: CharacterEntity): CharacterState {
            return CharacterState(
                id = entity.id,
                name = entity.name,
                status = entity.status,
                species = entity.species,
                type = entity.type,
                gender = entity.gender,
                origin = OriginState(name = entity.origin.name, url = entity.origin.url),
                location = LocationState(name = entity.location.name, url = entity.location.url),
                image = entity.image,
                url = entity.url,
                created = entity.created
            )
        }
    }
}