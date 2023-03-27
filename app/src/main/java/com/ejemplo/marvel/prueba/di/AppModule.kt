package com.ejemplo.marvel.prueba.di

import com.ejemplo.marvel.prueba.data.data_source.MarvelApi
import com.ejemplo.marvel.prueba.data.repository.MarvelRepositoryImplementation
import com.ejemplo.marvel.prueba.domain.repository.MarvelRepository
import com.ejemplo.marvel.prueba.utils.GlobalConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMarvelApi(): MarvelApi {
        return Retrofit.Builder()
            .baseUrl(GlobalConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }
    @Provides
    @Singleton
    fun provideMarvelRepository(api: MarvelApi):MarvelRepository{
        return MarvelRepositoryImplementation(api)
    }
}
