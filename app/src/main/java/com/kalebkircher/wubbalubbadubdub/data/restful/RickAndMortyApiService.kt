package com.kalebkircher.wubbalubbadubdub.data.restful

import android.util.Log
import com.kalebkircher.wubbalubbadubdub.data.dto.CharactersDto
import com.kalebkircher.wubbalubbadubdub.data.entity.CharacterEntity
import com.kalebkircher.wubbalubbadubdub.data.mapper.CharacterMapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

class RickAndMortyApiService {
    companion object {
        const val TAG = "RickAndMortyApiService"
        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val CHARACTER_ENDPOINT = "character"
        const val NAME_QUERY = "name"
        const val PAGE_QUERY = "page"
    }

    private val client = HttpClient(CIO) {
        install(HttpCache)
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d(TAG, message)
                }
            }
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getCharactersByName(page: Long = 0, name: String): List<CharacterEntity> {
        val charactersDto:CharactersDto = client.get(BASE_URL + CHARACTER_ENDPOINT) {
            url {
                if (page != 0L) {
                    parameters.append(PAGE_QUERY, page.toString())
                }

                parameters.append(NAME_QUERY, name)
            }
        }.body()

        return charactersDto.results.map { CharacterMapper.map(it) }
    }

    suspend fun getCharactersById(id: Long): CharacterEntity {
        return CharacterMapper.map(client.get("$BASE_URL$CHARACTER_ENDPOINT/$id").body())
    }
}