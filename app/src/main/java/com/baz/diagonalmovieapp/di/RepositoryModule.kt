package com.baz.diagonalmovieapp.di

import android.content.Context
import com.baz.diagonalmovieapp.data.repository.MoviesRepositoryImpl
import com.baz.diagonalmovieapp.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        stockRepositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository


}