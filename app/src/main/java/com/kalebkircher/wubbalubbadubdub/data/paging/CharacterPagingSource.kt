package com.kalebkircher.wubbalubbadubdub.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kalebkircher.wubbalubbadubdub.data.entity.CharacterEntity
import com.kalebkircher.wubbalubbadubdub.data.restful.RickAndMortyApiService

class CharacterPagingSource(private val characterApiService: RickAndMortyApiService, private val query:String): PagingSource<Long, CharacterEntity>() {
    override fun getRefreshKey(state: PagingState<Long, CharacterEntity>): Long? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, CharacterEntity> {
        return try {
            val page = params.key ?: 1
            val response = characterApiService.getCharactersByName(page, query)

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1L) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}