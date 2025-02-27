package com.kalebkircher.wubbalubbadubdub.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kalebkircher.wubbalubbadubdub.data.entity.CharacterEntity
import com.kalebkircher.wubbalubbadubdub.data.paging.CharacterPagingSource
import com.kalebkircher.wubbalubbadubdub.data.restful.RickAndMortyApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class CharacterRepository(private val api: RickAndMortyApiService) {

    fun getCharactersByNameFlow(name: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            CharacterPagingSource(api,name)
        }
    ).flow.flowOn(Dispatchers.IO)

    suspend fun getCharactersById(id: Long): CharacterEntity {
        return withContext(Dispatchers.IO) {
            return@withContext api.getCharactersById(id)
        }
    }
}