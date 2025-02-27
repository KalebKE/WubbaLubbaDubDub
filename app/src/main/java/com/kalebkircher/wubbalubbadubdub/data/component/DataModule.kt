package com.kalebkircher.wubbalubbadubdub.data.component

import com.kalebkircher.wubbalubbadubdub.data.repository.CharacterRepository
import com.kalebkircher.wubbalubbadubdub.data.restful.RickAndMortyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideRickAndMortyApi(): RickAndMortyApiService{
        return RickAndMortyApiService()
    }

    @Provides
    fun provideCharacterRepository(api: RickAndMortyApiService): CharacterRepository {
        return CharacterRepository(api)
    }
}