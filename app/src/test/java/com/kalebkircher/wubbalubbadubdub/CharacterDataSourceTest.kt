package com.kalebkircher.wubbalubbadubdub

import androidx.paging.PagingSource
import com.kalebkircher.wubbalubbadubdub.data.entity.CharacterEntity
import com.kalebkircher.wubbalubbadubdub.data.entity.LocationEntity
import com.kalebkircher.wubbalubbadubdub.data.entity.OriginEntity
import com.kalebkircher.wubbalubbadubdub.data.paging.CharacterPagingSource
import com.kalebkircher.wubbalubbadubdub.data.restful.RickAndMortyApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class CharacterDataSourceTest {

    private val service: RickAndMortyApiService = mock()
    private val query = "Rich Sanchez"
    private val pageSize = 20
    private val firstPage = 1L

    private val dataSource = CharacterPagingSource(service, query)

    private val expectedCharacters = listOf(
        CharacterEntity(
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
    )

    @Test
    fun `load first page success`() = runTest {
        setupMockResponse(firstPage)

        val params = PagingSource.LoadParams.Refresh(
            key = firstPage,
            loadSize = pageSize,
            placeholdersEnabled = false
        )

        val expected = PagingSource.LoadResult.Page(
            data = expectedCharacters,
            prevKey = null,
            nextKey = firstPage + 1
        )

        val actual = dataSource.load(params = params)

        assertEquals(expected, actual)
    }

    @Test
    fun `load first page error`() = runTest {
        val params = PagingSource.LoadParams.Refresh(
            key = firstPage,
            loadSize = pageSize,
            placeholdersEnabled = false
        )

        val expected = PagingSource.LoadResult.Error<Long, CharacterEntity>(
            throwable = Exception("Response body is null")
        )::class.java

        val actual = dataSource.load(params = params)::class.java

        assertEquals(expected, actual)
    }

    private suspend fun setupMockResponse(page: Long) {
        `when`(
            service.getCharactersByName(page, query)
        ).thenReturn(
            expectedCharacters
        )
    }
}